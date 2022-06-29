package com.example.demo.util;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.demo.entity.TaxThreshold;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TaxThresholdConfig implements CommandLineRunner {

	private List<TaxThreshold>taxThresholds = new ArrayList<>();
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
			try {
				TypeReference<List<TaxThreshold>> typeReference = new TypeReference<List<TaxThreshold>>() {};
				File file = new File("src/main/resources/taxThresholds.json"); 
				ObjectMapper mapper = new ObjectMapper();
				mapper.disable (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				taxThresholds=mapper.readValue(file, typeReference);
			}
			catch  (Exception e) {
				System.out.println("Enable to process json file. " + e.getMessage());
			}
	}

	public List<TaxThreshold> getTaxThresholds() {
		return this.taxThresholds;
	}

	
}
