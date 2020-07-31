package com.saga.challenge.phael.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService <T, I extends Serializable> implements IAbstractService<T,I> {

    @PersistenceContext
    @Autowired
    protected EntityManager entityManager;

    protected JpaRepository<T, I> repository;

    public AbstractService(JpaRepository<T, I> repository){
        this.repository = repository;
    }



    /**
     * Salva uma entidade
     *
     * @param entity - Classe de entidade a ser persistida no banco
     * @return Optional<T> - Objeto Optional retornado após realizar a operação de salvar
     * @since 03/03/2020
     */
    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    /**
     * Atualiza uma entidade
     *
     * @param entity - Classe de entidade a ser persistida no banco.
     * @return Optional<T> - Objeto Optional retornado após realizar a operação de atualizar .
     * @since 03/03/2020
     */
    @Override
    public Optional<T> update(T entity) {
        return Optional.of(repository.save(entity));
    }

    /**
     * Excui uma entidade
     *
     * @param id - ID da entidade a ser excluída
     * @since 03/03/2020
     */
    @Override
    public void remove(I id) {
        repository.deleteById(id);
    }

    /**
     * Método para retornar a lista de usuarios.
     *
     * @return Optional<List < T>> - Um Optional contendo a lista de usuários.
     * @since 03/03/2020.
     */
    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    /**
     * Busca a entidade pelo id.
     *
     * @param id - Id da entidade a ser encontrada.
     * @return um Optional da entidade buscada.
     * @since 03/03/2020.
     */
    @Override
    public Optional<T> findById(I id) {
        return repository.findById(id);
    }

    /**
     * Busca a quantidade de registros da entity.
     *
     * @return a quantidade de registros no repositorio
     * @since 27/03/2020.
     */
    public long count() {
        return repository.count();
    }
}
