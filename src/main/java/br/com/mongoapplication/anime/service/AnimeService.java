package br.com.mongoapplication.anime.service;

import br.com.mongoapplication.anime.model.Anime;
import br.com.mongoapplication.anime.persistence.AnimeDocument;
import br.com.mongoapplication.anime.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    public Flux<AnimeDocument> findAll() {
        return animeRepository.findAll();
    }

    public Mono<Anime> createAnime(final Anime anime) {

        var animeDocument = AnimeDocument.builder()
                .uuid(UUID.randomUUID())
                .name(anime.getName())
                .rate(anime.getRate())
                .seasonQntt(anime.getSeasonQntt())
                .createdAt(LocalDateTime.now())
                .build();

        return animeRepository.save(animeDocument)
                .flatMap(document -> {
                    var animeResponse = Anime.builder()
                            .name(document.getName())
                            .id(document.getUuid());
                    return Mono.just(animeResponse.build());
                });
    }

    public Mono<Anime> findById(final String id) {
        return animeRepository.findById(UUID.fromString(id))
                .flatMap(document -> {
                    var animeResponse = Anime.builder()
                            .id(document.getUuid())
                            .name(document.getName())
                            .rate(document.getRate())
                            .seasonQntt(document.getSeasonQntt());
                    return Mono.just(animeResponse.build());
                });
    }
}
