package com.saga.challenge.phael.service;

import com.saga.challenge.phael.core.AbstractService;
import com.saga.challenge.phael.dto.RespostaSalvarUsuarioDTO;
import com.saga.challenge.phael.enums.PerfilUsuario;
import com.saga.challenge.phael.model.Perfil;
import com.saga.challenge.phael.model.Usuario;
import com.saga.challenge.phael.repository.IUsuarioRepository;
import com.saga.challenge.phael.security.UserSecurityInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UsuarioService extends AbstractService<Usuario, String> {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserSecurityInformation userSecurityInfo;

    public UsuarioService(IUsuarioRepository repository) {
        super(repository);
    }

    public RespostaSalvarUsuarioDTO salvar(Usuario entity) {
        var resposta = new RespostaSalvarUsuarioDTO();
        var usuarioCadastradoOptional = this.findById(entity.getEmail());
        if (usuarioCadastradoOptional.isPresent()) {
            resposta.setSalvo(Boolean.FALSE);
            resposta.setMensagem("Usuário com email já cadastrado no sistema! Por favor, utilize outro email.");
        } else {
            entity.setSenha(passwordEncoder.encode(entity.getSenha()));
            entity.setDataDeCadastro(LocalDate.now());
            if (this.count() == 0) {
                entity.setPerfil(new Perfil(1, PerfilUsuario.ROLE_ADMINISTRADOR.name()));
            } else {
                entity.setPerfil(new Perfil(2, PerfilUsuario.ROLE_VISITANTE.name()));
            }
            super.save(entity);
            resposta.setMensagem("Salvo com sucesso!");
            resposta.setSalvo(Boolean.TRUE);
        }
        return resposta;
    }

    public Boolean validaAcessoAoRecurso(String idUsuario) throws Exception {
        var usuario = this.findById(idUsuario).orElseThrow(Exception::new);
        return userSecurityInfo.getLoggedUser().getUsername().equalsIgnoreCase(usuario.getEmail()) ||
                userSecurityInfo.getLoggedUser().getAuthorities()
                        .stream().findFirst().get()
                        .getAuthority().equalsIgnoreCase(PerfilUsuario.ROLE_ADMINISTRADOR.name());
    }
}
