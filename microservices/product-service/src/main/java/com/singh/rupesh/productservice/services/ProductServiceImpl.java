package com.singh.rupesh.productservice.services;

import com.singh.rupesh.api.core.Product;
import com.singh.rupesh.api.core.ProductService;
import com.singh.rupesh.util.customExceptions.InvalidInputException;
import com.singh.rupesh.util.customExceptions.NotFoundException;
import com.singh.rupesh.util.exceptions.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger("ProductServiceImpl.class");

    private final ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Product getProduct(Integer productId) {
        LOG.info("/product returned the found product for productId = {}", productId);
        if(productId < 1){
            throw new InvalidInputException("Invalid productId: " + productId);
        }
        if(productId == 13){
            throw new NotFoundException("No product found for productId: " + productId);
        }
        return new Product(productId, "name- " + "product-service", 123, serviceUtil.getServiceAddress());
    }
}
