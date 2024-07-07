package com.qindel.wrapper;

import java.util.ArrayList;
import java.util.List;

/** template rest. */
public abstract class RestFunction<T, S> {
  
  /** wraplist. */
  public List<S> wrapList(List<T> list) {
    List<S> resultList = new ArrayList<>();  
    for (T dataList : list) {
      resultList.add(wrapData(dataList)); 
    }  
    return resultList;
  }

  /** wrapdata. */
  public S wrapData(T data) {
    return null;
  }

}
