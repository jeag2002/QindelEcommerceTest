package com.qindel.wrapper;


import com.qindel.dto.PrizesData;
import com.qindel.jpa.dto.Prizes;
import org.springframework.stereotype.Component;

/** PrizesToPrizesDataFunction. */
@Component
public class PrizesToPrizesDataFunction extends RestFunction<Prizes, PrizesData> {

  @Override
  public PrizesData wrapData(Prizes data) {
    PrizesData output = new PrizesData();
    output.setId(data.getId());
    output.setBrand(data.getBrand().getBrandName());
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
