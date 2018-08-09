package com.example.webfluxdemo.controller;


import com.example.webfluxdemo.model.User;
import com.example.webfluxdemo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserControllerTests {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateUser() {
         User user = new User("This is a Test User");

        webTestClient.post().uri("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.text").isEqualTo("This is a Test User");
    }

    @Test
    public void testGetAllTweets() {
        webTestClient.get().uri("/user")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(User.class);
    }

    @Test
    public void testGetSingleTweet() {
        User user = userRepository.save(new User("Hello, World!")).block();

        webTestClient.get()
                .uri("/user/{id}", Collections.singletonMap("id", user.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testUpdateUser() {
       User user = userRepository.save(new User("Initial User")).block();

        User newTweetData = new User("Updated User");

        webTestClient.put()
                .uri("/user/{id}", Collections.singletonMap("id", user.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newTweetData), User.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.text").isEqualTo("Updated User");
    }

    @Test
    public void testDeleteUser() {
       User user = tweetRepository.save(new User("To be deleted")).block();

        webTestClient.delete()
                .uri("/user/{id}", Collections.singletonMap("id",  user.getId()))
                .exchange()
                .expectStatus().isOk();
    }
}
