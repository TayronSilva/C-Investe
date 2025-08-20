package br.cinveste.service;

import java.util.List;
import java.util.Optional;

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

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserEntity> listUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserType(UserType tipoUsuario) {
        return userRepository.findByTipoUsuario(tipoUsuario);
    }

    public Optional<UserEntity> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }



    public String login(AuthenticationDto data) {
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((UserEntity) auth.getPrincipal());
    }

    @Transactional
    public UserEntity createUser(RegisterDto data) {
        var emailExists = userRepository.findByEmail(data.email());

        if (emailExists != null) {
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

    return user;
    }


}
