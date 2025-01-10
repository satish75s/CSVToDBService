package com.db2csv.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="CUSTOMER_DETAILS")
public class Customer {
	@Id	
	private int id;
	private String name;
	private String contact;
	private String email;
	private Date dop;
	private boolean isCustomer;
	private double amount;
	
}
