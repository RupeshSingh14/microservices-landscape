package com.singh.rupesh.productCompositeService.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singh.rupesh.api.core.*;
import com.singh.rupesh.util.customExceptions.InvalidInputException;
import com.singh.rupesh.util.customExceptions.NotFoundException;
import com.singh.rupesh.util.exceptions.HttpErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
public class ProductCompositeIntegration implements ProductService, RecommendationService, ReviewService {
    private static final Logger LOG = LoggerFactory.getLogger("ProductCompositeIntegration.class");

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String productServiceUrl;
    private final String recommendationServiceUrl;
    private final String reviewServiceUrl;

    public ProductCompositeIntegration(
            RestTemplate restTemplate,
            ObjectMapper mapper,
            @Value("${app.product-service.host}") String productServiceHost,
            @Value("${app.product-service.port}") int productServicePort,
            @Value("${app.recommendation-service.host}") String recommendationServiceHost,
            @Value("${app.recommendation-service.port}") int recommendationServicePort,
            @Value("${app.review-service.host}") String reviewServiceHost,
            @Value("${app.review-service.port}") int reviewServicePort){
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        productServiceUrl = "http://" + productServiceHost + ':' + productServicePort + "/product/";

        recommendationServiceUrl = "http://" + recommendationServiceHost + ':' + recommendationServicePort + "/recommendation?productId=";
        reviewServiceUrl = "http://" + reviewServiceHost + ':' + reviewServicePort + "/review?productId=";
    }

    @Override
    public Product getProduct(Integer productId) {
        try {
            String url = productServiceUrl + productId;
            LOG.info("Will call product-service on URL: {}", url);
            Product product = restTemplate.getForObject(url, Product.class);
            LOG.info("Found a product with id: {}", product.getProductId());
            return product;
        }catch(HttpClientErrorException ex){
            switch (ex.getStatusCode()){

                case NOT_FOUND:
                    throw new NotFoundException(getErrorMessage(ex));
                case UNPROCESSABLE_ENTITY:
                    throw new InvalidInputException(getErrorMessage(ex));
                default:
                    LOG.error("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                    LOG.error("Error body: {}", ex.getResponseBodyAsString());
                    throw ex;
            }
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }catch(IOException ioex){
            return ex.getMessage();
        }
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        try {
            String url = recommendationServiceUrl + productId;
            LOG.info("Will call getRecommendations API on URL: {}", url);
            List<Recommendation> recommendations = restTemplate
                    .exchange(url, GET, null, new ParameterizedTypeReference<List<Recommendation>>() {
                    }).getBody();
            LOG.info("Found {} recommendations for a product with id: {}", recommendations.size(), productId);
            return recommendations;
        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting recommendations, returning zero recommendations: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Review> getReviews(int productId) {
        try{
        String url = reviewServiceUrl + productId;
        LOG.info("Will call getReviews API on URL: {}",url);
        List<Review> reviews = restTemplate
                .exchange(url, GET, null, new ParameterizedTypeReference<List<Review>>() {}).getBody();
        LOG.info("Found {} reviews for a product with id: {}", reviews.size(), productId);
        return reviews;
    }catch(Exception ex){
            LOG.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }
}