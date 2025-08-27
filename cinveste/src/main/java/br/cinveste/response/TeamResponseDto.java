package br.cinveste.response;

public record TeamResponseDto(
    Integer id,
    String nome,
    String descricao,
    String logoUrl,
    UserResponseDto user
) {

}