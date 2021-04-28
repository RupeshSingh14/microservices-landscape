package com.singh.rupesh.reviewService.services;

import com.singh.rupesh.api.core.Review;
import com.singh.rupesh.api.core.ReviewService;
import com.singh.rupesh.util.customExceptions.InvalidInputException;
import com.singh.rupesh.util.exceptions.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public ReviewServiceImpl(ServiceUtil serviceUtil){
        this.serviceUtil = serviceUtil;
    }

    @Override
    public List<Review> getReviews(int productId) {
        if(productId < 1) throw new InvalidInputException("Invalid productId: " + productId);
        if(productId == 213){
            LOG.info("No reviews found for productId: {}" + productId);
            return new ArrayList<>();
        }

        List<Review> list = new ArrayList<>();
        list.add(new Review(productId,1,"Rupesh", "Collection", "ArrayList", serviceUtil.getServiceAddress()));
        list.add(new Review(productId,2,"Ratnesh", "Functions", "lambdas", serviceUtil.getServiceAddress()));
        list.add(new Review(productId,3,"Pihu", "Call backs", "Futures", serviceUtil.getServiceAddress()));
        LOG.info("/reviews response size: {}", list.size());
        return list;
    }
}