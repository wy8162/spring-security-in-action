package y.w.security.basic.ch3_4_5_security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import y.w.security.basic.ch3_4_5_security.model.CustomerResponse;
import y.w.security.basic.ch3_4_5_security.service.CustomerService;

@RequiredArgsConstructor
@RestController
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/api/customer")
    public ResponseEntity<CustomerResponse> getAllCustomers() {
        CustomerResponse response = new CustomerResponse();

        response.setStatus(HttpStatus.OK);
        response.addAllCustomers(service.getAllCustomer());

        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }
}
