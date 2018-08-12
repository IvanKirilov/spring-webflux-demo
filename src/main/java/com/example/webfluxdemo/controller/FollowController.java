package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.model.Follow;
import com.example.webfluxdemo.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/follow")
public class FollowController extends AbstractController<FollowService, Follow, String> {

    @Autowired
    public FollowController(FollowService followService){
        super(followService);
    }

}

