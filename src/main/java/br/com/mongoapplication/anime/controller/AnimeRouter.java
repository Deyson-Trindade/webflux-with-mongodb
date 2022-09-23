package br.com.mongoapplication.anime.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class AnimeRouter {

    private final AnimeHandler handler;

    @Bean
    public RouterFunction<ServerResponse> handler() {
        return route()
                .path("/anime", builder ->
                        builder.nest(accept(APPLICATION_JSON), routerBuilder -> routerBuilder
                                .GET("", request -> handler.findAll())
                                .GET("{id}", handler::findById)
                                .POST(handler::createAnime)))
                .build();
    }
}
