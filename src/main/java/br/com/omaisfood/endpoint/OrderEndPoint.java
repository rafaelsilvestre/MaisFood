package br.com.omaisfood.endpoint;

import br.com.omaisfood.model.Order;
import br.com.omaisfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderEndPoint {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {

        return new ResponseEntity<Order>(new Order(), HttpStatus.OK);
    }
}
