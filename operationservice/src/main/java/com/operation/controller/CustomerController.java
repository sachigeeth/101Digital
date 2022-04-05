package com.operation.controller;

import com.operation.entity.Customer;
import com.operation.service.CustomerService;
import com.operation.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity createCustomer(@Valid @RequestBody Customer customer) {
        return this.customerService.createCustomer(customer);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateCustomer(@Valid @RequestBody Customer customer) {
        return this.customerService.updateCustomer(customer);
    }

    @DeleteMapping("{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        return this.customerService.deleteCustomer(customerId);
    }

    @PostMapping("/customerSearch")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Customer> customerSearch(@RequestBody CustomerVo customerVo) {
        return this.customerService.customerSearch(customerVo);
    }

}
