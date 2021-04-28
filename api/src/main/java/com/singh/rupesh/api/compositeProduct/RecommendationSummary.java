package com.singh.rupesh.api.compositeProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class RecommendationSummary {

    private final int recommendationId;
    private final String author;
    private final int rate;

    public RecommendationSummary() {
        recommendationId = 0;
        author= null;
        rate = 0;
    }
}
