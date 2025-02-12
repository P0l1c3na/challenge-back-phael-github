package com.saga.challenge.phael.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Usuario {
    @Id
    @Setter
    @Getter
    @Column(unique = true)
    private String email;

    @Setter @Getter
    private String nome;

    @Setter @Getter
    private String senha;

    @ManyToOne
    @Getter @Setter
    private Perfil perfil;

    @Getter @Setter
    private boolean ativo;

    @Getter @Setter
    private String cep;

    @Getter @Setter
    private String endereco;

    @Getter @Setter
    private String telefone;

    @Getter @Setter
    private LocalDate dataDeCadastro;
}
