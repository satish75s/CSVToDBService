package com.db2csv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db2csv.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
} 