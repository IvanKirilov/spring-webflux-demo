package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.model.Follow;
import com.example.webfluxdemo.model.Follow;
import com.example.webfluxdemo.service.FollowService;

public class FollowControler {
    @RestController
    @RequestMapping("/follow")
    public class FollowController extends AbstractController<FollowService, Follow, String> {

        @Autowired
        public FollowController(FollowService followService){
            super(followService);
        }

    }

}
