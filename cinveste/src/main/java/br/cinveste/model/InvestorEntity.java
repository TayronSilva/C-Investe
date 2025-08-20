package br.cinveste.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "investidor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_investidor")
    private Integer id_investidor;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private UserEntity user;

    @Column(nullable = false, length = 100)
    private String ocupacao;

    @Column(nullable = false, length = 150)
    private String empresa;

    @Column(name = "tempo_atuacao", nullable = false)
    private Integer tempoAtuacao;

    @Column(name = "renda_mensal", precision = 12, scale = 2)
    private BigDecimal rendaMensal;

    @Column(name = "valor_investimentos", precision = 12, scale = 2)
    private BigDecimal valorInvestimentos;

    @Column(columnDefinition = "TEXT")
    private String interesses;

    // Construtor customizado sem o id (id vem do user)
    public InvestorEntity(UserEntity user, String ocupacao, String empresa, Integer tempoAtuacao,
                          BigDecimal rendaMensal, BigDecimal valorInvestimentos, String interesses) {
        this.user = user;
        this.ocupacao = ocupacao;
        this.empresa = empresa;
        this.tempoAtuacao = tempoAtuacao;
        this.rendaMensal = rendaMensal;
        this.valorInvestimentos = valorInvestimentos;
        this.interesses = interesses;
    }
}
