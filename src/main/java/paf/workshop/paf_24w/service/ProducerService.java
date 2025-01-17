package paf.workshop.paf_24w.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import paf.workshop.paf_24w.model.Order;

@Service
public class ProducerService {
    @Autowired
    @Qualifier("order")
    private RedisTemplate<String, String> template;

    @Autowired
    private ChannelTopic topic;

    public void sendOrder(Order order) {
        template.convertAndSend(topic.getTopic(), order);
    }
}
