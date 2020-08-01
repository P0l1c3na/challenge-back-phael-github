package com.saga.challenge.phael.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data @NoArgsConstructor
public class RespostaSalvarUsuarioDTO {
    private Boolean salvo;
    private String mensagem;
}
