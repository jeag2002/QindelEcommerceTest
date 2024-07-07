package com.qindel.jpa.helper;


import static org.springframework.data.jpa.domain.Specification.where;

import com.qindel.dto.Predicate;
import com.qindel.exception.InvalidCriteriaException;
import com.qindel.exception.MalformedCriteriaException;
import com.qindel.jpa.dto.Brand;
import com.qindel.jpa.dto.Prizes;
import jakarta.persistence.criteria.Join;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


/** PrizesSpecification. */
@Component
@Slf4j
public class PrizesSpecification {
  
  /** filter. */
  public Specification<Prizes> buildFilter(List<Predicate> predicateLst) {
    Specification<Prizes> criteria = Specification.where(null);
    
    if (predicateLst == null) {
      throw new MalformedCriteriaException("[QUERY] criteria is null ");
    } else if (predicateLst.size() > 0) {
      criteria = where(criteria(predicateLst.get(0)));
      for (int i = 1; i < predicateLst.size(); i++) {
        criteria = criteria.and(criteria(predicateLst.get(i)));
      }
    }
    return criteria;
  }
  
  /**
   * Create criteria for filtering.

   * @param predicate criteria data
   * @return token of criteria
   */
  
  private Specification<Prizes> criteria(Predicate predicate) {
    return  processCriteria(predicate);
  }
  
  /**
  * process predicates for filtering.

  * @param predicate input predicate
  * @return specification definition
  */
  private Specification<Prizes> processCriteria(Predicate predicate) {
    try {
      if (predicate.getOperator().equalsIgnoreCase("EQUALS") 
          && predicate.getType().equals("Integer")) {
 
        return (root, query, criteriaBuilder) ->
           criteriaBuilder.equal(root.get(predicate.getData()), 
                                 Integer.valueOf(predicate.getValue()));
    
      } else if (predicate.getOperator().equalsIgnoreCase("EQUALS") 
           && predicate.getType().equals("String") 
           && predicate.getData().equals("brand")) {
       
        return (root, query, criteriaBuilder) -> {
          Join<Prizes, Brand> brandPrices = root.join("brand");
          return criteriaBuilder.like(criteriaBuilder.upper(brandPrices.get("brandName")), 
                  "%" + predicate.getValue().toUpperCase() + "%");   
        };
       
      } else if (predicate.getOperator().equalsIgnoreCase("BETWEEN")
           && predicate.getType().equals("Date")) {
       
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date literalDate = format.parse(predicate.getValue());
       
        return (root, query, criteriaBuilder) -> {
          jakarta.persistence.criteria.Predicate lessOrEqual = 
               criteriaBuilder.lessThanOrEqualTo(root.<Date>get("startDate"), literalDate);
          jakarta.persistence.criteria.Predicate greaterOrEqual =
               criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("endDate"), literalDate);
          return criteriaBuilder.and(greaterOrEqual, lessOrEqual);
        };
      } else {
        throw new InvalidCriteriaException("[QUERY] Criteria not yet implemented");
      }
    } catch (InvalidCriteriaException e1)  {
      throw e1;
    } catch (NumberFormatException | ParseException e2) {
      throw new MalformedCriteriaException("[QUERY] Error while building criteria " 
                + e2.getMessage());
    } catch (Exception e3) {
      throw new MalformedCriteriaException("[QUERY] Error while building criteria " 
                + e3.getMessage());
    }
  }

}
