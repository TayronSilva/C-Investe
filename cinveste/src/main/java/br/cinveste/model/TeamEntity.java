package br.cinveste.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipe")
    private Integer idEquipe;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private UserEntity user;

    @Column(length = 100)
    private String logoUrl;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;
    

}
