package com.singh.rupesh.api.core;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author RUPESH
 *
 * @apiNote API to expose products
 */
//@Service
public interface ProductService {

    /**
     * <p>Sample usage: curl $HOST:$PORT/product/1</p>
     *
     * @param productId
     * @return the product, if found, else null
     */
    @GetMapping(
            value= "/product/{productId}",
            produces = "application/json")
    Product getProduct(@PathVariable Integer productId);
}
