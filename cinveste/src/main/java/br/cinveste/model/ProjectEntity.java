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

    private String nome;
    private String area;
    private String descricao;
    private String orientador;

    private Double vpl;
    private Double roi;
    private Double tir;
    private Double payback;
    private Double paybackDescontado;

    private String periodo;
    private String mercadoAlvo;
}
