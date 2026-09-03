package br.com.alex.springAPI.application.interfaces;

import br.com.alex.springAPI.application.dtos.Pagination;
import br.com.alex.springAPI.application.dtos.PageApplication;

import java.util.Optional;

public interface IRepositoryDomain<T, K> {
  T save(T entity);

  Pagination<T> findAll(PageApplication requestPage);

  Optional<T> findById(K id);

  void delete(K id);
}
