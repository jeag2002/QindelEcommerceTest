package com.qindel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** PrizesFilterData. */
@Data
public class PrizesFilterData implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -2657431073310513826L;
  
  /** brand. */
  private String brand;
  
  /** date. */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date appDate;
  
  /** productId. */
  private Integer productId;

}
