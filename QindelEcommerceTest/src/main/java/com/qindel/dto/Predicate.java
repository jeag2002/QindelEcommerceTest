package com.qindel.dto;

import java.io.Serializable;
import lombok.Data;

/** Predicate obj. */
@Data
public class Predicate implements Serializable {
  /** serializable. */
  private static final long serialVersionUID = 1L;
  
  /** data. */
  private String data;
  
  /** type. */
  private String type;
  
  /** value. */
  private String value;
  
  /** equals. */
  private String operator;

}
