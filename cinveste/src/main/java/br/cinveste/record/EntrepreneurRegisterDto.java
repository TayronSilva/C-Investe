package br.cinveste.record;
import java.time.LocalDate;

public record EntrepreneurRegisterDto(
    Integer user_id,
    String instituicao_ensino,
    String curso,
    String nivel_ensino,
    LocalDate ano_expedicao,
    LocalDate ano_conclusao
) {}
