package br.com.omaisfood.endpoint;

import br.com.omaisfood.dto.OrderForm;
import br.com.omaisfood.model.Order;
import br.com.omaisfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderEndPoint {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody OrderForm orderForm) {
        Order order = this.orderService.saveOrder(orderForm);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = this.orderService.getOrdersByUserLogged();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getOrdersByCompanyLogged() {
        List<Order> orders = this.orderService.getOrdersByCompanyLogged();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
}
