package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.model.Tweet;
import com.example.webfluxdemo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jjcosare on 7/5/18.
 */
@RestController
@RequestMapping("/tweet")
public class TweetController extends AbstractController<TweetService, Tweet, String> {

    @Autowired
    public TweetController(TweetService tweetService){
        super(tweetService);
    }

}
