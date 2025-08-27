package br.cinveste.response;

public record ProjectResponseDto(
    Integer idProjeto,
    String nome,
    String area,
    String descricao,
    String orientador,
    Double vpl,
    Double roi,
    Double tir,
    Double payback,
    Double paybackDescontado,
    String periodo,
    String mercadoAlvo,
    String teamName
) {}
