package vn.com.fobelife.component.cart.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import vn.com.fobelife.component.cart.dto.OrderDto;
import vn.com.fobelife.component.cart.dto.OrderItemDto;
import vn.com.fobelife.component.cart.dto.OrderReturnDto;
import vn.com.fobelife.component.cart.entity.Order;
import vn.com.fobelife.component.cart.entity.OrderItem;
import vn.com.fobelife.component.cart.repository.OrderItemRepository;
import vn.com.fobelife.component.cart.repository.OrderRepository;
import vn.com.fobelife.component.cart.service.CartService;
import vn.com.fobelife.component.product.entity.Product;
import vn.com.fobelife.component.product.repository.ProductRepository;
import vn.com.fobelife.component.user.entity.User;
import vn.com.fobelife.component.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private ProductRepository productRepo;

    @Value("${fobelife.email}")
    private String receiver;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Transactional(readOnly = false)
    public OrderDto createOrder(OrderDto dto) throws Exception {
        Order order = new Order();
        order.setUsername(dto.getUsername());
        order.setStatus(dto.getStatus());
        order.setTotal(dto.getTotal());
        order.setTransactionInfo(UUID.randomUUID().toString());
        order.setType(dto.getType());
        order = orderRepo.save(order);
        order.setCode(String.format("%s_%s", order.getUsername(), order.getId()));
        order = orderRepo.save(order);

        String emailMessage = "Chi tiết đơn hàng của quý khách như sau:\r\n\r\n";
        int total = 0;
        for (OrderItemDto itemDto : dto.getItems()) {
            Product product = productRepo.findByCode(itemDto.getProductCode());
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductCode(product.getCode());
            orderItem.setProductName(product.getName());
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setTotal(
                    itemDto.getQuantity() * product.getPrice());
            orderItem = orderItemRepo.save(orderItem);
            emailMessage += orderItem.getQuantity() + " x " + product.getCode() + ": " + orderItem.getProductName()
                    + "\r\n";
            total += product.getPrice();
        }
        if ("GIFT".equalsIgnoreCase(dto.getType())) {
            User user = userRepo.findByUsername(dto.getUsername());
            if (total > user.getPoint()) {
                throw new Exception("Số điểm của bạn không đủ để nhận quà!");
            }
            user.setPoint(user.getPoint() - total);
            userRepo.save(user);
        }
        emailMessage += "\r\nTổng cộng: " + order.getTotal() + " vnd";
        sendEmail(order.getCode(), emailMessage);
        return applyOrderDto(order);
    }

    private void sendEmail(String code, String message) {
        try {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiver, user.getEmail());
        mailMessage.setSubject("Đơn hàng " + code + " của khách hàng " + username);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
        } catch (Exception e) {
            log.error("***** Cant send email: ", e);
        }
    }

    private OrderDto applyOrderDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setUsername(entity.getUsername());
        dto.setStatus(buildStatus(entity.getStatus()));
        dto.setTotal(entity.getTotal());
        dto.setType(entity.getType());
        dto.setTransactionInfo(entity.getTransactionInfo());
        if (entity.getItems() != null) {
            String itemsAsString = StringUtils.EMPTY;
            for (OrderItem item : entity.getItems()) {
                itemsAsString += item.getQuantity().toString() + " x " + item.getProductName() + "<br>";
            }
            dto.setItemsAsString(itemsAsString);
        }
        dto.setCreatedDate(new SimpleDateFormat("EEE, dd-MMM-yyyy").format(entity.getCreatedDate()));
        return dto;
    }

    private String buildStatus(String status) {
        switch (status) {
        case "ORDERED":
            return "Đặt hàng";
        case "DELIVERED":
            return "Giao hàng";
        case "PAIED":
            return "Thanh toán";
        default:
            return StringUtils.EMPTY;
        }
    }

    @Override
    public List<OrderDto> getHistory(String username) throws Exception {
        List<OrderDto> orderDtos = new ArrayList<>();
        if (username.startsWith("admin")) {
            Iterable<Order> orders = orderRepo.findAll(Sort.by(Direction.DESC, "createdDate"));
            orders.forEach(o -> orderDtos.add(applyOrderDto(o)));
        } else {
            List<Order> orders = orderRepo.findByUsername(username, Sort.by(Direction.DESC, "createdDate"));
            orders.forEach(o -> orderDtos.add(applyOrderDto(o)));
        }
        return orderDtos;
    }

    @Override
    @Transactional(readOnly = false)
    public OrderDto deliverOrder(Long id) throws Exception {
        Order order = orderRepo.findById(id).get();
        order.setStatus("DELIVERED");
        order = orderRepo.save(order);
        return applyOrderDto(order);
    }

    @Override
    @Transactional(readOnly = false)
    public OrderDto updateOrder(OrderReturnDto dto) throws Exception {
        Order order = orderRepo.findByCode(dto.getOrderCode());

        order.setPrice(dto.getPrice());
        order.setPaymentId(dto.getPaymentId());
        order.setPaymentType(dto.getPaymentType());
        order.setErrorText(dto.getErrorText());
        order.setSecureCode(dto.getSecureCode());
        order.setTokenNl(dto.getTokenNl());
        if (StringUtils.isBlank(dto.getErrorText())) {
            order.setStatus("PAIED");
        }
        order = orderRepo.save(order);
        return applyOrderDto(order);
    }

    @Override
    @Transactional(readOnly = false)
    public OrderDto submitGift(String productCode) throws Exception {
        OrderDto dto = new OrderDto();
        dto.setTotal("0");
        dto.setItems(getItems(productCode));
        dto.setType("GIFT");
        dto.setStatus("ORDERED");
        dto.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        dto = createOrder(dto);
        return dto;
    }

    private List<OrderItemDto> getItems(String code) {
        List<OrderItemDto> itemDtos = new ArrayList<>();

        OrderItemDto item = new OrderItemDto();
        item.setProductCode(code);
        item.setQuantity(1);
        itemDtos.add(item);

        return itemDtos;
    }
}
