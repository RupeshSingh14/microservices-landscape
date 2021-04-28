package com.singh.rupesh.recommendationService.services;

import com.singh.rupesh.api.core.Recommendation;
import com.singh.rupesh.api.core.RecommendationService;
import com.singh.rupesh.util.customExceptions.InvalidInputException;
import com.singh.rupesh.util.exceptions.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecommendationServiceImpl implements RecommendationService {
    private static final Logger LOG = LoggerFactory.getLogger(RecommendationServiceImpl.class);
    private final ServiceUtil serviceUtil;

    @Autowired
    public RecommendationServiceImpl(ServiceUtil serviceUtil){
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        if(productId < 1) throw new InvalidInputException("Invalid productId: " + productId);
        if(productId == 113){
            LOG.info("No recommendations found for the productId: {}", productId);
            return new ArrayList<>();
        }

        List<Recommendation> list = new ArrayList<>();
        list.add(new Recommendation(productId,1, "Rupesh", 4, "java", serviceUtil.getServiceAddress()));
        list.add(new Recommendation(productId,2, "Raunak", 3, "C++", serviceUtil.getServiceAddress()));
        list.add(new Recommendation(productId,3, "Pihu", 5, "Python", serviceUtil.getServiceAddress()));
        LOG.info("/recommendation response size: {}", list.size());
        return list;
    }
}