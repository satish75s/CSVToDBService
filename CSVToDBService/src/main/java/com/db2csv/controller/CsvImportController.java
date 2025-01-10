package com.db2csv.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.db2csv.service.CsvImportService;
import java.nio.file.Path;
import java.nio.file.Paths;





@RestController
@RequestMapping("/import")
public class CsvImportController {

    @Autowired
    private CsvImportService csvImportService;
    
    @Value("${csv.upload.filePath}")
	String uploadDirectory;
    
  
    
    @PostMapping(value = "/importCsv", consumes = "multipart/form-data")
    public ResponseEntity<String> importCsvFile(@RequestParam("file") MultipartFile file) {
    	if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        // Use an absolute path or specify the working directory
       // String uploadDirectory = "C:/Users/Jitesh/uploads/";

        // Create the directory if it doesn't exist
        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            boolean dirCreated = directory.mkdirs();
            if (!dirCreated) {
                return ResponseEntity.status(500).body("Failed to create upload directory.");
            }
        }

        // Define the full file path
        Path path = Paths.get(uploadDirectory, file.getOriginalFilename());
        File destinationFile = path.toFile();

        // Transfer the file
        try {
            file.transferTo(destinationFile);
            csvImportService.importCsvToDatabase(uploadDirectory+file.getOriginalFilename());
            return ResponseEntity.ok("File uploaded successfully: " + destinationFile.getName());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to import CSV: " + e.getMessage());
        }
    }
}