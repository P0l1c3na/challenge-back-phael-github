package com.saga.challenge.phael.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Perfil {
    @Id
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    @Column(unique = true)
    @NotNull
    private String nome;

    public Perfil(Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }
}