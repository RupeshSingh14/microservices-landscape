package com.singh.rupesh.productservice;

import com.singh.rupesh.api.core.Product;
import com.singh.rupesh.api.core.ProductService;
import com.singh.rupesh.productservice.services.ProductServiceImpl;
import com.singh.rupesh.util.exceptions.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.singh.rupesh")
public class ProductServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceApplication.class);
	public static void main(String[] args) {
		LOG.info("Application booted up!");
		SpringApplication.run(ProductServiceApplication.class, args);
	}
}
