package br.com.alex.springAPI.domain.valueObjects;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public final class Pagination<T> {
  private int pageSize;
  private int pageCurrent;
  private int totalPages;
  private int totalElements;
  private List<T> contents;

  public List<T> content() {
    return contents;
  }

  public int totalPages() {
    //  (int) Math.ceil((double) this.totalElements / this.pageSize)
    return this.totalPages;
  }

  public int totalElements() {
    return this.totalElements;
  }

  public int pageSize() {
    return this.pageSize;
  }

  public int pageCurrent() {
    return this.pageCurrent;
  }

  public boolean isMore() {
    return pageCurrent < totalPages;
  }
}
