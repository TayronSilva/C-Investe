package br.cinveste.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto")
    private Integer idProjeto;

    @ManyToOne
    @JoinColumn(name = "id_equipe", nullable = false)
    private TeamEntity team;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String area;

    @Column(length = 255)
    private String descricao;

    @Column(length = 100)
    private String orientador;

    @Column(name = "vpl")
    private Double vpl; // Valor Presente Líquido

    @Column(name = "roi")
    private Double roi; // Retorno sobre o Investimento

    @Column(name = "tir")
    private Double tir; // Taxa Interna de Retorno

    @Column(name = "payback")
    private Double payback;

    @Column(name = "payback_descontado")
    private Double paybackDescontado;

    @Column(length = 100)
    private String periodo;

    @Column(name = "mercado_alvo", length = 255)
    private String mercadoAlvo;
}
