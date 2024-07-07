package com.qindel.jpa.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/** Prizes. */
@Entity
@Data
@Table(name = "prizes")
public class Prizes implements Serializable {
 
  /** serializable. */
  private static final long serialVersionUID = 1L;

  /** id. */
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  /** start_date. */
  @Column(name = "start_date")
  private Date startDate;
  
  /** end_date. */
  @Column(name = "end_date")
  private Date endDate;
  
  /** price_list. */
  @Column(name = "price_list")
  private Integer priceList;
  
  /** product_id. */
  @Column(name = "product_id")
  private Integer productId;
  
  /** priority. */
  @Column(name = "priority")
  private Integer priority;
  
  /** price. */
  @Column(name = "price")
  private Float price;
  
  /** currency. */
  @Column(name = "currency")
  private String currency;
  
  /** brand_id. */
  @ManyToOne
  @JoinColumn(name = "brand_id", referencedColumnName = "id")
  private Brand brand;
  
}
