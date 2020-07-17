package vn.com.fobelife.component.cart.service.impl;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.cart.dto.OrderDto;
import vn.com.fobelife.component.cart.service.NganLuongService;

@Service
@Transactional(readOnly = false)
@Slf4j
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
        String encryptData = publicEncrypt(data.toString(), alepayEncrypt);
        reqData.put("data", encryptData);
        reqData.put("checksum", DigestUtils.md5DigestAsHex((encryptData + alepayChecksum).getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = 
                new HttpEntity<String>(reqData.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(alepayUrl + "/checkout/v1/request-order", request, String.class);
        JSONObject result = new JSONObject(response);
        log.error("********* data: {}", result.get("data"));
        String decryptData = publicDecrypt(result.get("data"), alepayEncrypt);
        JSONObject repData = new JSONObject(decryptData);
        return String.valueOf(repData.get("checkoutUrl"));

    }

    private JSONObject buildData(OrderDto order) {
        JSONObject data = new JSONObject();
        data.put("orderCode", order.getCode());
        data.put("amount", order.getTotal());
        data.put("currency", "VND");
        data.put("orderDescription", "order description");
        data.put("totalItem", order.getTotalItem());
        data.put("returnUrl", returnUrl);
        data.put("cancelUrl", cancelUrl);
        data.put("buyerName", "Fobelife");
        data.put("buyerEmail", receiver);
        data.put("buyerPhone", "0943686963");
        data.put("buyerAddress", "buyerAddress");
        data.put("buyerCity", "HCM");
        data.put("buyerCountry", "VN");
        data.put("checkoutType", 2);
        data.put("month", 12);
        return data;
    }

    private static String publicEncrypt(String plainText, String publicKey) {
        try {
            if (plainText == null) {
                return null;
            }

            byte[] keyBytes = Base64.getDecoder().decode(publicKey);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            RSAPublicKey key = (RSAPublicKey) factory.generatePublic(new X509EncodedKeySpec(keyBytes));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = plainText.getBytes("UTF-8");
            byte[] cipherBytes = new byte[0];

            int length = plainTextBytes.length;
            int offset = 0;
            while (offset < length) {

                int block_size;
                if (length - offset < 117) {
                    block_size = length - offset;
                } else {
                    block_size = 117;
                }

                byte[] block = new byte[block_size];
                for (int i = 0; i < block_size; ++i) {
                    block[i] = plainTextBytes[offset + i];
                }

                byte[] cipherBlock = cipher.doFinal(block);
                cipherBytes = append(cipherBytes, cipherBlock);

                offset += block_size;
            }

            String cipherText = Base64.getEncoder().encodeToString(cipherBytes);

            return cipherText;
        } catch (Exception ex) {
            return null;
        }
    }

 
    private static String publicDecrypt(Object cipherText, String publicKey) {
        try {
            if (cipherText == null) {
                return null;
            }

            byte[] keyBytes = Base64.getDecoder().decode(publicKey);

            KeyFactory factory = KeyFactory.getInstance("RSA");
            RSAPublicKey key = (RSAPublicKey) factory.generatePublic(new X509EncodedKeySpec(keyBytes));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] cipherBytes = Base64.getDecoder().decode(String.valueOf(cipherText));
            String abc = new String(cipherBytes);
            log.info(abc);
            byte[] plainTextBytes = new byte[0];

            int length = cipherBytes.length;
            int offset = 0;
            while (offset < length) {

                int block_size;
                if (length - offset < 128) {
                    block_size = length - offset;
                } else {
                    block_size = 128;
                }

                byte[] block = new byte[block_size];
                for (int i = 0; i < block_size; ++i) {
                    block[i] = cipherBytes[offset + i];
                }

                byte[] plainTextBlock = cipher.doFinal(block);
                plainTextBytes = append(plainTextBytes, plainTextBlock);

                offset += block_size;
            }

            return new String(plainTextBytes, "UTF-8");
        } catch (Exception ex) {
            log.error("publicDecrypt: ", ex);
            return null;
        }
    }

    private static byte[] append(byte[] array1, byte[] array2) {

        int len1 = array1.length;
        int len2 = array2.length;
        byte[] result = new byte[len1 + len2];

        for (int i = 0; i < len1; ++i) {
            result[i] = array1[i];
        }

        for (int i = 0; i < len2; ++i) {
            result[i + len1] = array2[i];
        }

        return result;
    }

    @Override
    public String decrypt(String encryptText) throws Exception {
        return publicDecrypt(encryptText, alepayEncrypt);
    }
}
