package br.com.alex.springAPI.domain.interfaces;

import java.util.List;
import java.util.Optional;

public interface IRepositoryDomain<T, K> {
  T save(T entity);

  List<T> findAll();

  Optional<T> findById(K id);

  void delete(K id);
}
