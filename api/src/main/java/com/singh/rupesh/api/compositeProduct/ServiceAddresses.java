package com.singh.rupesh.api.compositeProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServiceAddresses {
    private final String compositeAddress;
    private final String productAddress;
    private final String reviewAddress;
    private final String recommendationAddress;

    public ServiceAddresses(){
        compositeAddress = null;
        productAddress = null;
        reviewAddress = null;
        recommendationAddress = null;
    }
}
