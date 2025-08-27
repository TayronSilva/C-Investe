package br.cinveste.record;

public record TeamDto(
    Integer user_id,
    String logoUrl,
    String nome,
    String descricao
    ) {}