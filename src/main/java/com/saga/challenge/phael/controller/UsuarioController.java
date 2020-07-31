package com.saga.challenge.phael.controller;

import com.saga.challenge.phael.model.Usuario;
import com.saga.challenge.phael.service.UsuarioService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca o usuário pelo ID."),
            @ApiResponse(code = 403, message = "Somente o usuário cadastrado pode buscar seu cadastro ou o Administrador.")
    })
    public ResponseEntity<?> findById(@PathVariable("id") String email) {
        try {
            if (usuarioService.validaAcessoAoRecurso(email)) {
                return new ResponseEntity<>(usuarioService.findById(email).orElse(null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Você não tem permissão para acessar os dados!", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao buscar Usuario", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os usuarios cadastrados."),
            @ApiResponse(code = 403, message = "Somente o usuário com perfil de administrador tem acesso a este recurso.")
    })
    @GetMapping
    @PreAuthorize(value = "hasRole('ROLE_ADMINISTRADOR')")
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cadastra o usuario no sistema. " +
                    "Obs: O Perfil do Usuário deve ser id: 1 , nome: ROLE_ADMINISTRADOR ou id: 2, nome: ROLE_VISITANTE"),
            @ApiResponse(code = 400, message = "As informações enviadas estão incorretas")
    })
    @PostMapping
    @RequestMapping(path = "/salvar", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar Usuario", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca o usuário pelo ID."),
            @ApiResponse(code = 403, message = "Somente o usuário cadastrado pode alterar seu cadastro ou o Administrador.")
    })
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Usuario usuario) {
        try {
            if (usuarioService.validaAcessoAoRecurso(usuario.getEmail())) {
                return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Você não tem permissão para alterar a estes dados!", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar usuario...", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclui um usuário."),
            @ApiResponse(code = 403, message = "Somente o usuário com perfil de administrador tem acesso a este recurso.")
    })
    @DeleteMapping(value = "/{email}")
    @PreAuthorize(value = "hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> delete(@PathVariable String email) {
        try {
            usuarioService.remove(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao remover Usuario...", HttpStatus.BAD_REQUEST);
        }
    }

}