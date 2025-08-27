package br.cinveste.response;
public record EntrepreneurResponseDto(
    Integer id,
    UserResponseDto user,
    String instituicaoEnsino,
    String curso,
    String nivelEnsino,
    String anoExpedicao,
    String anoConclusao
) {}
