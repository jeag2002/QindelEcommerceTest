package com.qindel.service;


import com.qindel.dto.PrizesData;
import com.qindel.dto.PrizesFilterData;
import java.util.List;


/** QindelService. */
public interface QindelService {

  /**
  * Return all the prizes.

  * @return List of prizes
  */
  List<PrizesData> getAllPrizes();
  
  /**
  * Return list of prizes filtered. 

  * @param filter filter
  * @return List of prizes filter
  */
  List<PrizesData> getPrizesFiltered(PrizesFilterData filter);

}
