package com.qindel.wrapper;


import com.qindel.controller.dto.PrizesResponseDto;
import com.qindel.dto.PrizesData;
import org.springframework.stereotype.Component;

/** PrizesToPrizesDataFunction. */
@Component
public class PrizesDataToPrizesResponseFunction 
    extends RestFunction<PrizesData, PrizesResponseDto> {

  @Override
  public PrizesResponseDto wrapData(PrizesData data) {
    PrizesResponseDto output = new PrizesResponseDto();
    output.setId(data.getId());
    output.setBrand(data.getBrand());
    output.setCurrency(data.getCurrency());
    output.setPrice(data.getPrice());
    output.setPriceList(data.getPriceList());
    output.setEndDate(data.getEndDate());
    output.setStartDate(data.getStartDate());
    output.setPriority(data.getPriority());
    output.setProductId(data.getProductId());
    return output;
  }

}
