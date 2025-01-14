package paf.workshop.paf_24w.db;

public class Query {

    public static final String INSERT_ORDER = """
            insert into orders (order_date, customer_name, ship_address, notes, tax)
                values(?, ?, ?, ?, ?);
            """;

    public static final String INSERT_ORDERDETAILS = """
            insert into order_details (product, unit_price, discount, quantity, order_id)
                values (?, ?, ?, ?, ?);
            """;
    
}
