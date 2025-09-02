package br.cinveste.response;

public record UserResponseDto(
    Integer id,
    String nome,
    String email,
    String tipoUsuario
) {}
