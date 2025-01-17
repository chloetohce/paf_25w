package paf.workshop.paf_24w.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import paf.workshop.paf_24w.db.OrderDetailsRepository;
import paf.workshop.paf_24w.db.OrderRepository;
import paf.workshop.paf_24w.model.Order;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository detailsRepository;

    @Transactional
    public boolean saveOrder(Order o) {
        int orderId = orderRepository.addOrder(o);
        detailsRepository.saveOrderDetails(o.getItems(), orderId);
        return true;
    }
}
