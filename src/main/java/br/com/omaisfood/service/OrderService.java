package br.com.omaisfood.service;

import br.com.omaisfood.dto.OrderForm;
import br.com.omaisfood.dto.ProductItem;
import br.com.omaisfood.model.*;
import br.com.omaisfood.model.enumerators.OrderStatus;
import br.com.omaisfood.repository.OrderRepository;
import br.com.omaisfood.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Transactional
    public Order saveOrder(OrderForm orderForm) {
        UserSecurity userLogged = UserService.getUserAuthenticated();
        User user = this.userService.findById(userLogged.getId());

        // get actual company and address
        Company company = this.companyService.findById(orderForm.getCompany_id());
        Address address = this.addressService.getAddressById(orderForm.getAddress_id());

        System.out.println("EMPRESA " + company.getName());
        System.out.println("ENDEREÇO " + address.getCity());

        Order order = new Order();
        order.setDate(new Date().toString());
        order.setStatus(OrderStatus.PENDING);
        order.setCompany(company);
        order.setAddress(address);

        Float totalPrice = new Float(0);
        for (int i = 0; i < orderForm.getProducts().size(); i++){
            ProductItem productItem = orderForm.getProducts().get(i);
            totalPrice += (productItem.getQuantity() * productItem.getPrice());
        }

        order.setTotalPrice(totalPrice);
        order.setUser(user);

        // Save this order
        Order o = this.orderRepository.save(order);

        for (int i = 0; i < orderForm.getProducts().size(); i++){
            ProductItem productItem = orderForm.getProducts().get(i);
            Product product = this.productService.getProductById(productItem.getProduct_id());

            OrderItem orderItem = new OrderItem(productItem.getPrice(), productItem.getQuantity(), order, product);
            this.orderItemService.save(orderItem);
        }

        return o;
    }

    public List<Order> getOrdersByUserLogged() {
        UserSecurity userLogged = UserService.getUserAuthenticated();
        User user = this.userService.findById(userLogged.getId());

        return this.orderRepository.findAllByUserId(user.getId());
    }

    public List<Order> getOrdersByCompanyLogged() {
        UserSecurity userLogged = UserService.getUserAuthenticated();
        User user = this.userService.findById(userLogged.getId());

        return this.orderRepository.findAllByCompanyId(user.getCompany().getId());
    }
}
