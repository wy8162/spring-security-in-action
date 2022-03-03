package y.w.security.basic.ch3_4_5_security.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import y.w.security.basic.ch3_4_5_security.model.Customer;

@Component
public class CustomerService {
    private final static Map<String, Customer> customerStore = new HashMap<>();

    static {
        customerStore.put("001", new Customer("001", "Jack"));
        customerStore.put("002", new Customer("002", "Yang"));
        customerStore.put("003", new Customer("003", "Jane"));
    }

    public List<Customer> getAllCustomer() {
        return new ArrayList<>(customerStore.values());
    }

    public Customer getCustomerById(String id) {
        return customerStore.getOrDefault(id, new Customer("001", "Jack"));
    }
}
