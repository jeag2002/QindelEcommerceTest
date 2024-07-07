package com.qindel.controller.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;



/** PrizesFilterRequest. */
@Data
public class PrizesFilterRequest implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -2657431073310513826L;
  
  /** brand. */
  private String brand;
  
  /** date. */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date appDate;
  
  /** productId. */
  private Integer productId;


}
