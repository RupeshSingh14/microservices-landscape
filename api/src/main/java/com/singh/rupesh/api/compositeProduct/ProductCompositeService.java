package com.singh.rupesh.api.compositeProduct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author RUPESH
 * @apiNote API to expose products info
 */
public interface ProductCompositeService {

    /**
     * Sample usage: curl $HOST:$PORT/product-composite/1
     * @param productId
     * @return the composite product info, if found, else null
     */
    @GetMapping(
            value = "/product-composite/{productId}",
            produces = "application/json")
    ProductAggregate getProduct(@PathVariable int productId);
}
