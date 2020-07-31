package com.saga.challenge.phael.core;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IAbstractService<T, I extends Serializable> {
    /**
     * Salva uma entidade
     *
     * @param entity - Classe de entidade a ser persistida no banco
     * @return Optional<T> - Objeto Optional retornado após realizar a operação de salvar
     * @since 19/05/2020
     */
    T save(T entity);

    /**
     * Atualiza uma entidade
     *
     * @param entity - Classe de entidade a ser persistida no banco.
     * @return Optional<T> - Objeto Optional retornado após realizar a operação de atualizar .
     * @since 19/05/2020
     */
    Optional<T> update(T entity);

    /**
     * Excui uma entidade
     *
     * @param id - ID da entidade a ser excluída
     * @since 19/05/2020
     */
    void remove(I id);

    /**
     * Método para retornar a lista de usuarios.
     *
     * @return Optional<List < T>> - Um Optional contendo a lista de usuários.
     * @since 19/05/2020.
     */
    List<T> getAll();

    /**
     * Busca a entidade pelo id.
     *
     * @param id - Id da entidade a ser encontrada.
     * @return um Optional da entidade buscada.
     * @since 19/05/2020.
     */
    Optional<T> findById(I id);

    /**
     * Retorna a quantidade de intens.
     *
     * @return quantidade de itens.
     * @since 19/05/2020.
     */
    long count();
}
