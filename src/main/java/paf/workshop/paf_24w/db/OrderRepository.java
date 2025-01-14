package paf.workshop.paf_24w.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import paf.workshop.paf_24w.model.Order;

@Repository
public class OrderRepository {
    @Autowired
    private JdbcTemplate template;

    public int addOrder(Order o) {
        KeyHolder key = new GeneratedKeyHolder();
        template.update(Connection conn -> {
            PreparedStatement ps = conn.prepareStatement(Query.INSERT_ORDER);

            return null;
        });

        template.update(Query.INSERT_ORDER, o.getOrderDate(), o.getCustomerName(), o.getShipAddress(), o.getNotes(), o.getTax());
    }
}
