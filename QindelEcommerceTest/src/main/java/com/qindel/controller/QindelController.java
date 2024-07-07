package com.qindel.controller;

import com.qindel.controller.dto.PrizesFilterRequest;
import com.qindel.controller.dto.PrizesResponse;
import com.qindel.service.QindelService;
import com.qindel.wrapper.PrizesDataToPrizesResponseFunction;
import com.qindel.wrapper.PrizesFilterRequestToPrizesFilterDataFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** QindelController. */
@RestController
@RequestMapping("rest")
public class QindelController {
  /** service. */
  @Autowired
  private QindelService service;
  
  /**wrapper. */
  @Autowired
  private PrizesDataToPrizesResponseFunction function;
  
  /**wrapper. */
  @Autowired
  private PrizesFilterRequestToPrizesFilterDataFunction filterFunction;
  
  /**
   * list all prizes.

   * @return list prizes
   */
  @GetMapping("/listPrizes")
  public PrizesResponse getPrizes()  {
    PrizesResponse response =  new PrizesResponse();
    response.setPrizesList(function.wrapList(service.getAllPrizes()));
    return response;
  }
  
  /**
   * list prizes filtered.

   * @param filter filter
   * @return list prizes 
   */
  @GetMapping("/listPrizesFiltered")
  public PrizesResponse getPrizes(PrizesFilterRequest filter) {
    PrizesResponse response = new PrizesResponse();
    response.setPrizesList(function
            .wrapList(service.getPrizesFiltered(filterFunction.wrapData(filter))));
    return response;
  }
  

}
