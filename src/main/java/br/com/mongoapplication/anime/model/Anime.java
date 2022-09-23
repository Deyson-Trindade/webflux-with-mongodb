package br.com.mongoapplication.anime.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class Anime {

    UUID id;
    String name;
    int rate;
    int seasonQntt;
}
