package br.com.mongoapplication.anime.controller.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;


@Builder
@Value
@Jacksonized
public class CreateAnimeRequest {
    String name;
    int rate;
    int seasonQntt;
}
