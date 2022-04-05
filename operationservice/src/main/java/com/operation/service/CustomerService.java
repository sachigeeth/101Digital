package com.operation.service;

import com.operation.entity.Customer;
import com.operation.entity.Item;
import com.operation.vo.CustomerVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {
    ResponseEntity createCustomer(Customer customer);

    ResponseEntity updateCustomer(Customer customer);

    ResponseEntity<Customer> deleteCustomer(Integer customerId);

    List<Customer> customerSearch(CustomerVo customerVo);
}
