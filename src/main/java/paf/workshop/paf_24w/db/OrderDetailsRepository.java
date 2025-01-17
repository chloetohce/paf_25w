package paf.workshop.paf_24w.db;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paf.workshop.paf_24w.model.OrderDetails;

@Repository
public class OrderDetailsRepository {
    @Autowired
    private JdbcTemplate template;

    public boolean saveOrderDetails(List<OrderDetails> details, int orderId) {
        List<Object[]> params = details.stream()
            .map(od -> new Object[]{od.getProduct(), od.getUnitPrice(), od.getDiscount(), od.getQuantity(), orderId})
            .collect(Collectors.toList());
        
        template.batchUpdate(Query.INSERT_ORDERDETAILS, params);
        return true;
    }
}
