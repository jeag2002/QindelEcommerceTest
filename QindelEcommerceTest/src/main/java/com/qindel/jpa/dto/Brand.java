package com.qindel.jpa.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;


/** Brand. */
@Entity
@Data
@Table(name = "brand")
public class Brand implements Serializable {

  /** serializable. */
  private static final long serialVersionUID = 1L;
  /** id. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  
  /** brandname. */
  @Column(name = "brandname")
  private String brandName;
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "brand")
  private List<Prizes> prizesList = new ArrayList<>();
  
}
