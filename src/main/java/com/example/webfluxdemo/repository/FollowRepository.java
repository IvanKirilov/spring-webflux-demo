package com.example.webfluxdemo.repository;

import com.example.webfluxdemo.model.Follow;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface FollowRepository extends ReactiveMongoRepository<Follow, String> {


    }
