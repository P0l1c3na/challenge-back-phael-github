package com.saga.challenge.phael.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @Setter
    @Getter
    @Column(unique = true)
    private String email;

    @Setter @Getter
    private String senha;

    @ManyToOne
    @Getter @Setter
    private Funcao funcao;

    @Getter @Setter
    private boolean ativo;

    @Getter @Setter
    private LocalDate dataDeCadastro;
}
