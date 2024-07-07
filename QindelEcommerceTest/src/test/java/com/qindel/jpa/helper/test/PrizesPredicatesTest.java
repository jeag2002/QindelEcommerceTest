package com.qindel.jpa.helper.test;

import static org.assertj.core.api.Assertions.assertThat;

import com.qindel.dto.Predicate;
import com.qindel.dto.PrizesFilterData;
import com.qindel.jpa.helper.PrizesPredicates;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** PrizesPredicatesTest. */
public class PrizesPredicatesTest {

  /** predicates. */
  PrizesPredicates predicates;
  
  /** filter dummy. */
  PrizesFilterData dummy;
  
  /** filter empty. */
  PrizesFilterData empty;
  
  /** filter one criteria. */
  PrizesFilterData onecriteria;
  
  /** filter one criteria alt. */
  PrizesFilterData onecriterialt;
  
  /** filter dummy. */
  PrizesFilterData twocriteria;
  
  /** filter dummy. */
  PrizesFilterData twocriterialt;
  
  /** filter dummy. */
  PrizesFilterData threecriteria;
  
  /** format dates. */
  SimpleDateFormat format;
  
  /** filter 
  
  /** load information. */
  @BeforeEach
  public void setUp() throws Exception {
    format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    predicates = new PrizesPredicates();
    empty = new PrizesFilterData();

    onecriterialt = new PrizesFilterData();
    onecriterialt.setBrand("zara");
    
    onecriteria = new PrizesFilterData();
    onecriteria.setProductId(0);

    twocriteria = new PrizesFilterData();
    twocriteria.setProductId(0);
    twocriteria.setBrand("zara");
    
    twocriterialt = new PrizesFilterData();
    twocriterialt.setBrand("zara");
    twocriterialt.setAppDate(format.parse("2020-06-14 00:00:00"));

    threecriteria = new PrizesFilterData();
    threecriteria.setProductId(0);
    threecriteria.setBrand("zara");
    threecriteria.setAppDate(format.parse("2020-06-14 00:00:00"));
    
  }

  @DisplayName("criteria if filter is null")
  @Test
  public void criteriaIfNull() {
    List<Predicate> predicateLst = predicates.processPredicates(dummy); 
    assertThat(predicateLst).isNotNull();
    assertThat(predicateLst).isEmpty();
  }
  
  @DisplayName("criteria if filter is empty")
  @Test
  public void criteriaIfEmpty() {
    List<Predicate> predicateLst = predicates.processPredicates(empty); 
    assertThat(predicateLst).isNotNull();
    assertThat(predicateLst).isEmpty();
  }
  
  @DisplayName("criteria if filter is one, idProduct")
  @Test
  public void criteriaIfOne() {
    List<Predicate> predicateLst = predicates.processPredicates(onecriteria); 
    assertThat(predicateLst).isNotNull();
    assertThat(predicateLst).isNotEmpty();
    assertThat(predicateLst.size()).isEqualTo(1);
    assertThat(predicateLst.get(0).getData()).isEqualTo("productId");
    assertThat(predicateLst.get(0).getType()).isEqualTo("Integer");
    assertThat(predicateLst.get(0).getValue()).isEqualTo("0");
    assertThat(predicateLst.get(0).getOperator()).isEqualTo("EQUALS");
  }
  
  @DisplayName("criteria if filter is one, brand")
  @Test
  public void criteriaIfOneAlt() {
    List<Predicate> predicateLst = predicates.processPredicates(onecriterialt); 
    assertThat(predicateLst).isNotNull();
    assertThat(predicateLst).isNotEmpty();
    assertThat(predicateLst.size()).isEqualTo(1);
    assertThat(predicateLst.get(0).getData()).isEqualTo("brand");
    assertThat(predicateLst.get(0).getType()).isEqualTo("String");
    assertThat(predicateLst.get(0).getValue()).isEqualTo("zara");
    assertThat(predicateLst.get(0).getOperator()).isEqualTo("EQUALS");
  }
  
  
  @DisplayName("criteria if filter is two, idProduct, brand")
  @Test
  public void criteriaIfTwo() {
    List<Predicate> predicateLst = predicates.processPredicates(twocriteria); 
    assertThat(predicateLst).isNotNull();
    assertThat(predicateLst).isNotEmpty();
    assertThat(predicateLst.size()).isEqualTo(2);
    assertThat(predicateLst.get(0).getData()).isEqualTo("productId");
    assertThat(predicateLst.get(0).getType()).isEqualTo("Integer");
    assertThat(predicateLst.get(0).getValue()).isEqualTo("0");
    assertThat(predicateLst.get(0).getOperator()).isEqualTo("EQUALS");
    assertThat(predicateLst.get(1).getData()).isEqualTo("brand");
    assertThat(predicateLst.get(1).getType()).isEqualTo("String");
    assertThat(predicateLst.get(1).getValue()).isEqualTo("zara");
    assertThat(predicateLst.get(1).getOperator()).isEqualTo("EQUALS");
  }
  
  
  @DisplayName("criteria if filter is two, brand, date")
  @Test
  public void criteriaIfTwoAlt() {
    List<Predicate> predicateLst = predicates.processPredicates(twocriterialt); 
    assertThat(predicateLst).isNotNull();
    assertThat(predicateLst).isNotEmpty();
    assertThat(predicateLst.size()).isEqualTo(2);
    assertThat(predicateLst.get(0).getData()).isEqualTo("brand");
    assertThat(predicateLst.get(0).getType()).isEqualTo("String");
    assertThat(predicateLst.get(0).getValue()).isEqualTo("zara");
    assertThat(predicateLst.get(0).getOperator()).isEqualTo("EQUALS");
    assertThat(predicateLst.get(1).getData()).isEqualTo("appDate");
    assertThat(predicateLst.get(1).getType()).isEqualTo("Date");
    assertThat(predicateLst.get(1).getValue()).isEqualTo("2020-06-14 00:00:00");
    assertThat(predicateLst.get(1).getOperator()).isEqualTo("BETWEEN");
  }
  
  
  @DisplayName("criteria if filter is three, idProduct, brand, date")
  @Test
  public void criteriaIfThree() {
    List<Predicate> predicateLst = predicates.processPredicates(threecriteria); 
    assertThat(predicateLst).isNotNull();
    assertThat(predicateLst).isNotEmpty();
    assertThat(predicateLst.size()).isEqualTo(3);
    assertThat(predicateLst.get(0).getData()).isEqualTo("productId");
    assertThat(predicateLst.get(0).getType()).isEqualTo("Integer");
    assertThat(predicateLst.get(0).getValue()).isEqualTo("0");
    assertThat(predicateLst.get(0).getOperator()).isEqualTo("EQUALS");
    assertThat(predicateLst.get(1).getData()).isEqualTo("brand");
    assertThat(predicateLst.get(1).getType()).isEqualTo("String");
    assertThat(predicateLst.get(1).getValue()).isEqualTo("zara");
    assertThat(predicateLst.get(1).getOperator()).isEqualTo("EQUALS");
    assertThat(predicateLst.get(2).getData()).isEqualTo("appDate");
    assertThat(predicateLst.get(2).getType()).isEqualTo("Date");
    assertThat(predicateLst.get(2).getValue()).isEqualTo("2020-06-14 00:00:00");
    assertThat(predicateLst.get(2).getOperator()).isEqualTo("BETWEEN");
  }
  

}
