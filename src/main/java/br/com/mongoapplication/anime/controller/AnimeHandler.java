package br.com.mongoapplication.anime.controller;

import br.com.mongoapplication.anime.controller.request.CreateAnimeRequest;
import br.com.mongoapplication.anime.model.Anime;
import br.com.mongoapplication.anime.persistence.AnimeDocument;
import br.com.mongoapplication.anime.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class AnimeHandler {

    @Autowired
    private AnimeService animeService;

    public Mono<ServerResponse> findAll() {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(animeService.findAll(), AnimeDocument.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        var animeId = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(animeService.findById(animeId), Anime.class);
    }

    public Mono<ServerResponse> createAnime(ServerRequest request) {
        return request.bodyToMono(CreateAnimeRequest.class)
                .map(animeRequest -> Anime.builder()
                            .name(animeRequest.getName())
                            .seasonQntt(animeRequest.getSeasonQntt())
                            .rate(animeRequest.getRate())
                            .build())
                .flatMap(animeService::createAnime)
                .flatMap(this::getBodyBuilder);
    }

    private Mono<ServerResponse> getBodyBuilder(Anime anime) {
        try {
            var uri = new URI(String.format("/anime/%s", anime.getId()));
            return ServerResponse.created(uri).bodyValue(anime);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
