package de.chris.VaadinCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findByLastNameStartsWithIgnoreCase(String lastName)
    {
        return customerRepository.findByLastNameStartsWithIgnoreCase(lastName);
    }

    public List<Customer> findAll()
    {
        return customerRepository.findAll();
    }
}
