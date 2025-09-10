package br.cinveste.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "empreendedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntrepreneurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empreendedor")
    private Integer id_empreendedor;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private UserEntity user;


    @Column(name = "instituicao_ensino", nullable = false)
    private String instituicaoEnsino;

    @Column(nullable = false)
    private String curso;

    @Column(name = "nivel_ensino", nullable = false)
    private String nivelEnsino;

    @Column(name = "ano_expedicao", nullable = false)
    private LocalDate anoExpedicao;

    @Column(name = "ano_conclusao", nullable = false)
    private LocalDate anoConclusao;

    public EntrepreneurEntity(UserEntity user, String instituicaoEnsino, String curso, String nivelEnsino,
                              LocalDate anoExpedicao, LocalDate anoConclusao) {
        this.user = user;
        this.instituicaoEnsino = instituicaoEnsino;
        this.curso = curso;
        this.nivelEnsino = nivelEnsino;
        this.anoExpedicao = anoExpedicao;
        this.anoConclusao = anoConclusao;
    }
}
