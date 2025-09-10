package br.cinveste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.cinveste.model.UserEntity;
import br.cinveste.enums.UserType;
import br.cinveste.model.EntrepreneurEntity;
import br.cinveste.model.InvestorEntity;
import br.cinveste.record.AuthenticationDto;
import br.cinveste.record.RegisterDto;
import br.cinveste.repository.UserRepository;
import br.cinveste.repository.EntrepreneurRepository;
import br.cinveste.repository.InvestorRepository;
import br.cinveste.response.UserResponseDto;
import br.cinveste.webconfig.TokenService;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntrepreneurRepository entrepreneurRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Transactional
    public UserResponseDto createUser(RegisterDto data) {
        var emailExists = userRepository.findByEmail(data.email());
        if (emailExists.isPresent()) {
            throw new IllegalArgumentException("Email already registered.");
        }

        UserEntity user = new UserEntity(
            null,
            data.nome(),
            data.email(),
            data.telefone(),
            data.data_nascimento(),
            data.cep(),
            data.estado(),
            data.municipio(),
            data.rua(),
            data.numero(),
            data.complemento(),
            passwordEncoder.encode(data.senha()),
            data.typeUsuario()
        );

        user = userRepository.save(user);

        if (data.typeUsuario() == UserType.Investidor) {
            InvestorEntity investor = new InvestorEntity(
                null,
                user,
                data.investorRegisterDto().ocupacao(),
                data.investorRegisterDto().empresa(),
                data.investorRegisterDto().tempo_atuacao(),
                data.investorRegisterDto().renda_mensal(),
                data.investorRegisterDto().valor_investimentos(),
                data.investorRegisterDto().interesses()
            );
            investorRepository.save(investor);
        }

        if (data.typeUsuario() == UserType.Empreendedor) {
            EntrepreneurEntity entrepreneur = new EntrepreneurEntity(
                null,
                user,
                data.entrepreneurRegisterDto().instituicao_ensino(),
                data.entrepreneurRegisterDto().curso(),
                data.entrepreneurRegisterDto().nivel_ensino(),
                data.entrepreneurRegisterDto().ano_expedicao(),
                data.entrepreneurRegisterDto().ano_conclusao()
            );
            entrepreneurRepository.save(entrepreneur);
        }

        return new UserResponseDto(
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getTipoUsuario().name()
        );
    }

    public List<UserResponseDto> listUsers() {
        return userRepository.findAll().stream()
            .map(user -> new UserResponseDto(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getTipoUsuario().name()
            ))
            .toList();
    }

    public UserResponseDto getCurrentUser(UserEntity currentUser) {
        return new UserResponseDto(
            currentUser.getId(),
            currentUser.getNome(),
            currentUser.getEmail(),
            currentUser.getTipoUsuario().name()
        );
    }

    public String login(AuthenticationDto data) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((UserEntity) auth.getPrincipal());
    }
}
