package com.qindel.jpa.helper;

import com.qindel.dto.Predicate;
import com.qindel.dto.PrizesFilterData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/** PrizesPredicates. */
@Component
public class PrizesPredicates {
  /**
   * Process Predicates.

   * @param data filterdata
   * @return list of predicates
   */
  public List<Predicate> processPredicates(PrizesFilterData data) {
    List<Predicate> predicates = new ArrayList<Predicate>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    if (data != null) {
      if (data.getProductId() != null) {
        Predicate predicate = new Predicate();
        predicate.setData("productId");
        predicate.setType("Integer");
        predicate.setValue(data.getProductId().toString());
        predicate.setOperator("EQUALS");
        predicates.add(predicate);
      }
    
      if (data.getBrand() != null) {
        Predicate predicate = new Predicate();
        predicate.setData("brand");
        predicate.setType("String");
        predicate.setValue(data.getBrand());
        predicate.setOperator("EQUALS");
        predicates.add(predicate);
      }
    
      if (data.getAppDate() != null) {
        Predicate predicate = new Predicate();
        predicate.setData("appDate");
        predicate.setType("Date");
        predicate.setValue(format.format(data.getAppDate()));
        predicate.setOperator("BETWEEN");
        predicates.add(predicate);
      }
    }
    
    return predicates;
  }

}
