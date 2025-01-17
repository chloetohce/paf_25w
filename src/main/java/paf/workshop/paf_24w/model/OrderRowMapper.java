package paf.workshop.paf_24w.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order o = new Order();
        o.setOrderId(rs.getInt("orderId"));
        o.setOrderDate(rs.getObject("order_date", LocalDate.class));
        o.setCustomerName(rs.getString("customer_name"));
        o.setShipAddress(rs.getString("ship_address"));
        o.setNotes(rs.getString("text"));
        o.setTax(rs.getDouble("tax"));
        return o;
    }
    
}
