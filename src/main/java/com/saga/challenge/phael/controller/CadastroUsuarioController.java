package com.saga.challenge.phael.controller;

import com.saga.challenge.phael.model.Usuario;
import com.saga.challenge.phael.service.UsuarioService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/cadastroUsuario")
public class CadastroUsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cadastra o usuario no sistema. " +
                    "Obs: A Função do Usuário deve ser id: 1 , nome: ROLE_ADMINISTRADOR ou id: 2, nome: ROLE_VISITANTE"),
            @ApiResponse(code = 400, message = "As informações enviadas estão incorretas")
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Erro ao salvar Usuario", e);
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
