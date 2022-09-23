package br.com.mongoapplication.anime.repository;

import br.com.mongoapplication.anime.persistence.AnimeDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnimeRepository extends ReactiveMongoRepository<AnimeDocument, UUID> {
}
