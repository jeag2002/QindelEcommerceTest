package com.qindel.controller.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;


/** PrizesResponse. */
@Data
public class PrizesResponse implements Serializable {
  /** serializable. */
  private static final long serialVersionUID = 1L;
  
  /** prizesList. */
  private List<PrizesResponseDto> prizesList;
}

