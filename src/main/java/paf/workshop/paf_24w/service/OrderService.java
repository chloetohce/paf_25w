package paf.workshop.paf_24w.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import paf.workshop.paf_24w.db.OrderDetailsRepository;
import paf.workshop.paf_24w.db.OrderRepository;
import paf.workshop.paf_24w.model.Order;

@Service
public class OrderService implements MessageListener {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailsRepository detailsRepository;

    @Autowired
    @Qualifier("order")
    private RedisTemplate<String, String> template;

    @Transactional
    public boolean saveOrder(Order o) {
        int orderId = orderRepository.addOrder(o);
        detailsRepository.saveOrderDetails(o.getItems(), orderId);
        return true;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String orderDate = new String(message.getBody());
        JsonReader reader = Json.createReader(new StringReader(orderDate));
        JsonObject obj = reader.readObject();
        
    }
}
