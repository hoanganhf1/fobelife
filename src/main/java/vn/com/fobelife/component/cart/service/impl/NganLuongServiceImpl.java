package vn.com.fobelife.component.cart.service.impl;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import vn.com.fobelife.component.cart.dto.OrderDto;
import vn.com.fobelife.component.cart.service.NganLuongService;

@Service
@Transactional(readOnly = false)
public class NganLuongServiceImpl implements NganLuongService {

    @Value("${nganluong.url}")
    private String nganluongUrl;

    @Value("${nganluong.merchant.sitecode}")
    private String merchantSiteCode;

    @Value("${fobelife.return.url}")
    private String returnUrl;

    @Value("${fobelife.cancel.url}")
    private String cancelUrl;

    @Value("${fobelife.email}")
    private String receiver;

    @Value("${nganluong.secure.pass}")
    private String securePass;

    @Value("${nganluong.alepay.url}")
    private String alepayUrl;

    @Value("${nganluong.alepay.token}")
    private String alepayToken;

    @Value("${nganluong.alepay.encrypt}")
    private String alepayEncrypt;

    @Value("${nganluong.alepay.checksum}")
    private String alepayChecksum;

    @Override
    public String checkoutVisa(OrderDto dto) throws Exception {
        String redirectUrl = nganluongUrl 
                + "?merchant_site_code=" + merchantSiteCode 
                + "&return_url=" + returnUrl 
                + "&receiver=" + receiver
                + "&transaction_info=" + dto.getTransactionInfo()
                + "&order_code=" + dto.getCode()
                + "&price=" + dto.getTotal()
                + "&currency=vnd"
                + "&quantity=1"
                + "&tax=0"
                + "&discount=0"
                + "&fee_cal=0"
                + "&fee_shipping=0"
                + "&order_description=order description"
                + "&buyer_info=" + dto.getUsername()
                + "&affiliate_code=affiliate_code"
                + "&lang=vi"
                + "&secure_code=" + DigestUtils.md5DigestAsHex(
                                        String.join(StringUtils.SPACE, 
                                                merchantSiteCode,
                                                returnUrl,
                                                receiver,
                                                dto.getTransactionInfo(),
                                                dto.getCode(),
                                                dto.getTotal(),
                                                "vnd", // currency
                                                "1", //quantity
                                                "0", //tax
                                                "0", //discount
                                                "0", //fee_cal
                                                "0", // fee_shipping
                                                "order description", //order description
                                                dto.getUsername(), // buyer info
                                                "affiliate_code", //affiliate_code
                                                securePass
                                                ).getBytes())
                + "&cancel_url=" + cancelUrl;
        return redirectUrl;
    }

    @Override
    public String checkoutInstallment(OrderDto order) throws Exception {

        JSONObject reqData = new JSONObject();
        reqData.put("token", alepayToken);

        JSONObject data = buildData(order);
        String encryptData = new String(encrypt(data));
        reqData.put("data", encryptData);
        reqData.put("checksum", DigestUtils.md5DigestAsHex((encryptData + alepayChecksum).getBytes()));


        RestTemplate restTemplate = new RestTemplate();
        JSONObject result = restTemplate.postForObject(alepayUrl + "/checkout/v1/request-order", data, JSONObject.class);
        return String.valueOf(result.get("checkoutUrl"));
    }

    private JSONObject buildData(OrderDto order) {
        JSONObject data = new JSONObject();
        data.put("orderCode", order.getCode());
        data.put("amount", order.getTotal());
        data.put("currency", "VND");
        data.put("orderDescription", "order description");
        data.put("totalItem", order.getItems().size());
        data.put("returnUrl", returnUrl);
        return data;
    }

    private byte[] encrypt(JSONObject data) throws Exception {
        PublicKey publicKey = KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(alepayEncrypt)));
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.toString().getBytes());
    }
}
