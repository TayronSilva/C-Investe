package br.cinveste.record;
import br.cinveste.enums.UserType;
import java.time.LocalDate;
import br.cinveste.record.EntrepreneurRegisterDto;
import br.cinveste.record.InvestorRegisterDto;

public record RegisterDto(
    String nome,
    String email,
    String telefone,
    String senha,
    LocalDate data_nascimento,
    String cep,
    String estado,
    String rua,
    String numero,
    String municipio,
    String complemento,
    UserType typeUsuario,
    InvestorRegisterDto investorRegisterDto,
    EntrepreneurRegisterDto entrepreneurRegisterDto
) {}
