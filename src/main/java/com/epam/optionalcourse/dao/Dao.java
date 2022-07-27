package com.epam.optionalcourse.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    boolean delete(K id);

    T save(T entity);

    T update(T entity);

    List<T> findAll();

    Optional<T> findById(K id);
}
