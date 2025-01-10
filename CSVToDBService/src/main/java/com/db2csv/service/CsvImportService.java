package com.db2csv.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db2csv.entity.Customer;
import com.db2csv.repository.CustomerRepository;

@Service
public class CsvImportService {

    @Autowired
    private CustomerRepository customerRepository;

    public void importCsvToDatabase(String filePath) throws IOException {
        // Open the CSV file for reading
        Reader reader = new FileReader(filePath);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());

        // Iterate over the CSV records
        for (CSVRecord csvRecord : csvParser) {
            // Parse each row into an Customer object
            Customer customer = new Customer();
            customer.setId(Integer.parseInt(csvRecord.get("ID")));
            customer.setName(csvRecord.get("Name"));
            customer.setContact(csvRecord.get("Contact"));
            customer.setEmail(csvRecord.get("Email"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Define the format
            Date date;
			try {
				System.out.println("dop="+csvRecord.get("Date of Purchase"));
				date = formatter.parse(csvRecord.get("Date of Purchase"));
			
            
            customer.setDop(date);
            customer.setCustomer(Boolean.parseBoolean(csvRecord.get("Customer")));
            customer.setAmount(Double.parseDouble(csvRecord.get("Amount")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
            // Save the Customer object to the database
            customerRepository.save(customer);
        }

        // Close the CSV parser and reader
        csvParser.close();
        reader.close();
    }
}