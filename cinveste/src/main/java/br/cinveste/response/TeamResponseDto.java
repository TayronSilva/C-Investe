package br.cinveste.response;

public record TeamResponseDto(
    Integer idEquipe,
    String nome,
    String descricao,
    String logoUrl,
    String usuarioNome
) {}
