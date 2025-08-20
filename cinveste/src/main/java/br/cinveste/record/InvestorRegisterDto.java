package br.cinveste.record;

import java.math.BigDecimal;

public record InvestorRegisterDto(
    Integer user_id,
    String ocupacao,
    String empresa,
    Integer tempo_atuacao,
    BigDecimal renda_mensal,
    BigDecimal valor_investimentos,
    String interesses
) {}
