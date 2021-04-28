package com.singh.rupesh.api.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author RUPESH
 * @apiNote API to expose reviews of products
 */
public interface ReviewService {

    /**
     * Sample usage: curl $HOST:$PORT/review?productId=1
     * @param productId
     * @return reviews for a product id
     */
    @GetMapping(
            value = "/review",
            produces = "application/json")
    List<Review> getReviews(@RequestParam(value = "productId", required = true) int productId);
}
