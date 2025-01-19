package paf.workshop.paf_24w.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import paf.workshop.paf_24w.db.RegistrationRepository;
import paf.workshop.paf_24w.model.Order;
import paf.workshop.paf_24w.model.OrderDetails;

@Service
public class ProducerService {
    @Autowired
    @Qualifier("order")
    private RedisTemplate<String, String> template;

    @Autowired
    private ChannelTopic topic;

    @Autowired
    private RegistrationRepository registrationRepository;

    public void sendOrder(Order order) {
        JsonArrayBuilder items = Json.createArrayBuilder();
        for (OrderDetails d : order.getItems()) {
            JsonObject detailsObj = Json.createObjectBuilder()
                .add("product", d.getProduct())
                .add("unit_price", d.getUnitPrice())
                .add("discount", d.getDiscount())
                .add("quantity", d.getQuantity())
                .build();
            items.add(detailsObj);
        }

        JsonObject orderObj = Json.createObjectBuilder()
            .add("order_date", order.getOrderDate().toString())
            .add("customer_name", order.getCustomerName())
            .add("ship_address", order.getShipAddress())
            .add("notes", order.getNotes())
            .add("tax", order.getTax())
            .add("line_items", items)
            .build();

        template.convertAndSend(topic.getTopic(), orderObj.toString());
    }

    public List<String> getAllRegistrations() {
        return registrationRepository.getAllRegistrations();
    }
}
