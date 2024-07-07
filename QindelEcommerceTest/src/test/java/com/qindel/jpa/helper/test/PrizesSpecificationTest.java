package com.qindel.jpa.helper.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.qindel.dto.Predicate;
import com.qindel.exception.InvalidCriteriaException;
import com.qindel.exception.MalformedCriteriaException;
import com.qindel.jpa.helper.PrizesSpecification;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



/** PrizesSpecificationTests. */
public class PrizesSpecificationTest {
  
  /** filter prizesspecification. */
  PrizesSpecification filter;
  
  /** list predicates. */
  List<Predicate> predicateLst;
  
  
  /** list predicates. */
  List<Predicate> predicateEmpty;
  
  
  /** predicate null. */
  Predicate dummy;
  
  /** predicate dummy. */
  Predicate empty;
  
  /** predicate valid 1. */
  Predicate validOne;
  
  /** predicate valid 2. */
  Predicate validTwo;
  
  /** predicate valid 3. */
  Predicate validThree;
  
  /** predicate invalid. */
  Predicate invalid;
  
  /** load configuration. */
  @BeforeEach
  public void setUp() throws Exception {
    filter = new PrizesSpecification();
    empty = new Predicate();
    
    validOne = new Predicate();
    validOne.setData("productId");
    validOne.setOperator("EQUALS");
    validOne.setType("Integer");
    validOne.setValue("0");
    
    validTwo = new Predicate();
    validTwo.setData("brand");
    validTwo.setOperator("EQUALS");
    validTwo.setType("String");
    validTwo.setValue("ZARA");
    
    validThree = new Predicate();
    validThree.setData("appDate");
    validThree.setOperator("BETWEEN");
    validThree.setType("Date");
    validThree.setValue("2020-06-14 00:00:00");
    
    invalid = new Predicate();
    invalid.setData("price");
    invalid.setOperator("GREATER THAN");
    invalid.setType("Float");
    invalid.setValue("20.50");
  }
  
  /** list null. */
  @DisplayName("filter if criteria list null")
  @Test
  public void criteriaIfNull() {
    assertThrows(MalformedCriteriaException.class,
                 () -> { 
        filter.buildFilter(predicateLst); 
        });
  }
  
  /** list empty. */
  @DisplayName("filter if criteria list empty")
  @Test
  public void criteriaIfEmpty() {
    predicateEmpty = new ArrayList<Predicate>();
    filter.buildFilter(predicateEmpty);
  }
  
  /** list no empty with null element. */
  @DisplayName("filter if criteria list no empty but null element")
  @Test
  public void criteriaIfNoEmptyButNullElement() {
    predicateLst = new ArrayList<Predicate>();
    predicateLst.add(dummy);
    assertThrows(MalformedCriteriaException.class,
             () -> { 
        filter.buildFilter(predicateLst); 
        });
  }
  
  /** list no empty with empty element. */
  @DisplayName("filter if criteria list no empty but empty element")
  @Test
  public void criteriaIfNoEmptyButEmptyElement() {
    predicateLst = new ArrayList<Predicate>();
    predicateLst.add(empty);
    assertThrows(MalformedCriteriaException.class,
             () -> { 
        filter.buildFilter(predicateLst); 
        });
  }
  
  /** list no empty with invalid element. */
  @DisplayName("filter if criteria list no empty but invalid element")
  @Test
  public void criteriaIfNoEmptyButInvalidElement() {
    predicateLst = new ArrayList<Predicate>();
    predicateLst.add(invalid);
    assertThrows(InvalidCriteriaException.class,
             () -> { 
        filter.buildFilter(predicateLst); 
        });
  }
  
  /** list no empty with valid element. */
  @DisplayName("filter if criteria list with expected element (productId)")
  @Test
  public void criteriaValidOneExpectedElement() {
    predicateLst = new ArrayList<Predicate>();
    predicateLst.add(validOne);
    filter.buildFilter(predicateLst);
  }
  
  /** list no empty with valid element. */
  @DisplayName("filter if criteria list with expected element (Branch)")
  @Test
  public void criteriaValidOneExpectedElementTwo() {
    predicateLst = new ArrayList<Predicate>();
    predicateLst.add(validTwo);
    filter.buildFilter(predicateLst);
  }
  
  /** list no empty with valid element. */
  @DisplayName("filter if criteria list with expected element (appDate)")
  @Test
  public void criteriaValidOneExpectedElementThree() {
    predicateLst = new ArrayList<Predicate>();
    predicateLst.add(validThree);
    filter.buildFilter(predicateLst);
  }
  
  /** list no empty with two valid element. */
  @DisplayName("filter if criteria list with expected element (productId, Branch)")
  @Test
  public void criteriaValidTwo() {
    predicateLst = new ArrayList<Predicate>();
    predicateLst.add(validOne);
    predicateLst.add(validTwo);
    filter.buildFilter(predicateLst);
  }
  
  /** list no empty with three valid element. */
  @DisplayName("filter if criteria list with expected element (productId, Branch, appDate)")
  @Test
  public void criteriaValidThree() {
    predicateLst = new ArrayList<Predicate>();
    predicateLst.add(validOne);
    predicateLst.add(validTwo);
    predicateLst.add(validThree);
    filter.buildFilter(predicateLst);
  }

}
