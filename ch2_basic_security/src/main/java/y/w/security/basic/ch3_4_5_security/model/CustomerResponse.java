package y.w.security.basic.ch3_4_5_security.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private HttpStatus status;

    private List<Customer> entries = new ArrayList<>();

    public void addCustomer(Customer customer) { entries.add(customer); }
    public void addAllCustomers(List<Customer> customers) {
        entries.addAll(customers);
    }
}
