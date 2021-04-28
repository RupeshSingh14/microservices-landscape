package com.singh.rupesh.api.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author RUPESH
 * @apiNote API to expose recommendations for products
 */
public interface RecommendationService {

    /**
     * <p>Sample usage: curl $HOST:$PORT/recommendation/1</p>
     * @param productId
     * @return recommendations for a product id
     */
    @GetMapping(
            value = "/recommendation",
            produces = "application/json")
    List<Recommendation> getRecommendations(@RequestParam(value = "productId", required = true) int productId);
}
