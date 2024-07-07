package com.qindel.wrapper;


import com.qindel.controller.dto.PrizesFilterRequest;
import com.qindel.dto.PrizesFilterData;
import org.springframework.stereotype.Component;

/** PrizesToPrizesDataFunction. */
@Component
public class PrizesFilterRequestToPrizesFilterDataFunction 
    extends RestFunction<PrizesFilterRequest, PrizesFilterData> {

  @Override
  public PrizesFilterData wrapData(PrizesFilterRequest data) {
    PrizesFilterData output = new PrizesFilterData();
    output.setAppDate(data.getAppDate());
    output.setBrand(data.getBrand());
    output.setProductId(data.getProductId());
    return output;
  }

}
