package paf.workshop.paf_24w.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import paf.workshop.paf_24w.db.OrderDetailsRepository;
import paf.workshop.paf_24w.db.OrderRepository;
import paf.workshop.paf_24w.model.Order;
import paf.workshop.paf_24w.model.OrderDetails;

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
        
        Order o = new Order();
        o.setCustomerName(obj.getString("customer_name"));
        o.setShipAddress(obj.getString("ship_address"));
        o.setNotes(obj.getString("notes"));
        o.setTax(obj.getJsonNumber("tax").doubleValue());
        JsonArray items = obj.getJsonArray("line_items");
        List<OrderDetails> itemsList = new ArrayList<>();
        for (JsonValue i : items) {
            JsonObject item = i.asJsonObject();
            OrderDetails details = new OrderDetails();
            details.setProduct(item.getString("product"));
            details.setUnitPrice(item.getJsonNumber("unit_price").doubleValue());
            details.setDiscount(item.getJsonNumber("discount").doubleValue());
            details.setQuantity(item.getInt("quantity"));
            itemsList.add(details);
        }
        o.setItems(itemsList);

        saveOrder(o);

    }
}
