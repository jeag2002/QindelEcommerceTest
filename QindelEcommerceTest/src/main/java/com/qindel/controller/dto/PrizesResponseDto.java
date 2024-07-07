package com.qindel.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qindel.configuration.CustomFloatSerializer;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** PrizesData. */
@Data
public class PrizesResponseDto implements Serializable {

  /** serializable. */
  private static final long serialVersionUID = 1L;

  /** id. */
  private Long id;
  
  /** start_date. */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date startDate;
  
  /** end_date. */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endDate;
  
  /** price_list. */
  private Integer priceList;

  /** product_id. */
  private Integer productId;
  
  /** priority. */
  private Integer priority;
  
  /** price. */
  @JsonSerialize(using = CustomFloatSerializer.class)
  private Float price;
  
  /** currency. */
  private String currency;

  /** brand. */
  private String brand;
}
