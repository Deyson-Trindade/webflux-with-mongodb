package br.com.mongoapplication.anime.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(value = "animes")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimeDocument {
    @Id
    private UUID uuid;
    private String name;
    private int rate;
    private int seasonQntt;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
