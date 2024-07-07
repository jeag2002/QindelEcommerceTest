package com.qindel.service.impl;


import com.qindel.dto.Predicate;
import com.qindel.dto.PrizesData;
import com.qindel.dto.PrizesFilterData;
import com.qindel.jpa.PrizesRepository;
import com.qindel.jpa.dto.Prizes;
import com.qindel.jpa.helper.PrizesPredicates;
import com.qindel.jpa.helper.PrizesSpecification;
import com.qindel.service.QindelService;
import com.qindel.validation.InputFieldValidation;
import com.qindel.wrapper.PrizesToPrizesDataFunction;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/** QindelServiceImpl. */
@Service
@Slf4j
public class QindelServiceImpl implements QindelService {
  
  /** repository. */
  @Autowired 
  private PrizesRepository repository;
  
  /** predicates. */
  @Autowired
  private PrizesPredicates predicates;
  
  /** filters. */
  @Autowired
  private PrizesSpecification filter;
  
  /** conversion function. */
  @Autowired
  private PrizesToPrizesDataFunction function;
  
  /** validation. */
  @Autowired
  private InputFieldValidation validation;
  

  @Override
  public List<PrizesData> getAllPrizes() {
    log.info("[MATRIX-SERVICE] getAllPrizes INI");
    List<Prizes> prizesList = repository.findAll(Sort.by(Sort.Direction.DESC, "priority"));
    List<PrizesData> responseList = function.wrapList(prizesList);
    log.info("[MATRIX-SERVICE] getAllPrizes data " + responseList);
    return responseList;
  }

  @Override
  public List<PrizesData> getPrizesFiltered(PrizesFilterData filterData) {
    log.info("[MATRIX-SERVICE] getPrizesFiltered INI");
    // TODO Auto-generated method stub
    validation.validateFilterFields(filterData);
    List<Predicate> predicateLst = predicates.processPredicates(filterData);
    List<Prizes> prizesList = new ArrayList<Prizes>();
    if (predicateLst.size() != 0) {
      prizesList = repository
                  .findAll(filter.buildFilter(predicateLst), 
                           Sort.by(Sort.Direction.DESC, "priority"));
    } else {
      prizesList = repository.findAll();
    }
    List<PrizesData> responseList = function.wrapList(prizesList);
    log.info("[MATRIX-SERVICE] getPrizesFiltered data " + responseList);
    return responseList;
  }

}
