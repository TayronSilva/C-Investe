package br.cinveste.record;

public record ProjectDto(
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
    String mercadoAlvo
) {}
