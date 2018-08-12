package com.example.webfluxdemo.service;

import com.example.webfluxdemo.model.Follow;
import com.example.webfluxdemo.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService extends AbstractService<FollowRepository, Follow, String> {

    @Autowired
    public FollowService(FollowRepository followRepository){
        super(followRepository);
    }
}
