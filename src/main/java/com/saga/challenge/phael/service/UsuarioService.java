package com.saga.challenge.phael.service;

import com.saga.challenge.phael.core.AbstractService;
import com.saga.challenge.phael.enums.FuncaoUsuario;
import com.saga.challenge.phael.model.Usuario;
import com.saga.challenge.phael.repository.IUsuarioRepository;
import com.saga.challenge.phael.security.UserSecurityInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService extends AbstractService<Usuario, String> {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserSecurityInformation userSecurityInfo;

    public UsuarioService(IUsuarioRepository repository) {
        super(repository);
    }

    @Override
    public Usuario save(Usuario entity) {
        entity.setSenha(passwordEncoder.encode(entity.getSenha()));
        entity.setDataDeCadastro(LocalDate.now());
        return super.save(entity);
    }

    public Boolean validaAcessoAoRecurso(String idUsuario) throws Exception {
        var usuario = this.findById(idUsuario).orElseThrow(Exception::new);
        return userSecurityInfo.getLoggedUser().getUsername().equalsIgnoreCase(usuario.getEmail()) ||
                userSecurityInfo.getLoggedUser().getAuthorities()
                        .stream().findFirst().get()
                        .getAuthority().equalsIgnoreCase(FuncaoUsuario.ROLE_ADMINISTRADOR.name());
    }
}
