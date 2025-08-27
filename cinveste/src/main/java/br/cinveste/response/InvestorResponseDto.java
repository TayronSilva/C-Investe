package br.cinveste.response;

import java.math.BigDecimal;

public record InvestorResponseDto(
    Integer id,
    UserResponseDto user,
    String ocupacao,
    String empresa,
    Integer tempoAtuacao,
    BigDecimal rendaMensal,
    BigDecimal valorInvestimentos,
    String interesses
) {}
