package br.com.omaisfood.service;

import br.com.omaisfood.model.OrderItem;
import br.com.omaisfood.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem) {
        return this.orderItemRepository.save(orderItem);
    }
}
