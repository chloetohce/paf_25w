package paf.workshop.paf_24w.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(Query.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(o.getOrderDate()));
            ps.setString(2, o.getCustomerName());
            ps.setString(3, o.getShipAddress());
            ps.setString(4, o.getNotes());
            ps.setDouble(5, o.getTax());
            return ps;
        }, key);

        return key.getKey().intValue();
    }
}
