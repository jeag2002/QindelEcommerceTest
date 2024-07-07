package com.qindel.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** PrizesData. */
@Data
public class PrizesData implements Serializable {

  /** serializable. */
  private static final long serialVersionUID = 1L;
  
  /** id. */
  private Long id;

  /** start_date. */
  private Date startDate;
 
  /** end_date. */
  private Date endDate;

  /** price_list. */
  private Integer priceList;

  /** product_id. */
  private Integer productId;
  
  /** priority. */
  private Integer priority;
 
  /** price. */
  private Float price;
 
  /** currency. */
  private String currency;

  /** brand. */
  private String brand;

}
