package com.example.webfluxdemo.controller;
import com.example.webfluxdemo.model.Follow;
import com.example.webfluxdemo.repository.FollowRepository;
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

public class FollowControllerTests {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    FollowRepository followRepository;

    @Test
    public void testCreateFollow() {
        Follow follow = new Follow("This is a Test Follow");

        webTestClient.post().uri("/follow")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(follow), Follow.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.text").isEqualTo("This is a Test Follow");
    }

    @Test
    public void testGetAllFollows() {
        webTestClient.get().uri("/follow")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Follow.class);
    }

    @Test
    public void testGetSingleFollow() {
        Follow follow = followRepository.save(new Follow("Hello, World!")).block();

        webTestClient.get()
                .uri("/follow/{id}", Collections.singletonMap("id", follow.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testUpdateFollow() {
       Follow follow = followRepository.save(new Follow("Initial Follow")).block();

        Follow newFollowData = new Follow("Updated Follow");

        webTestClient.put()
                .uri("/follow/{id}", Collections.singletonMap("id", follow.getId()))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(newFollowData), Follow.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.text").isEqualTo("Updated Follow");
    }

    @Test
    public void testDeleteFollow() {
       Follow follow = followRepository.save(new Follow("To be deleted")).block();

        webTestClient.delete()
                .uri("/follow/{id}", Collections.singletonMap("id", follow.getId()))
                .exchange()
                .expectStatus().isOk();
    }
}
