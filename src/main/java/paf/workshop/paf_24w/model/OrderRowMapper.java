package paf.workshop.paf_24w.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order o = new Order(rs.getInt("orderId"), 
                rs.getObject("order_date", LocalDate.class),
                rs.getString("customer_name"),
                rs.getString("ship_address"),
                rs.getString("text"),
                rs.getDouble("tax"));
        return o;
    }
    
}
