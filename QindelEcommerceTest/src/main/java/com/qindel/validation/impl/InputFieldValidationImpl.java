package com.qindel.validation.impl;


import com.qindel.dto.PrizesFilterData;
import com.qindel.exception.InputValidationException;
import com.qindel.validation.InputFieldValidation;
import org.springframework.stereotype.Component;

/** InputFieldValidationImpl. */
@Component
public class InputFieldValidationImpl implements InputFieldValidation {

  @Override
  public void validateFilterFields(PrizesFilterData inputData) {
    
    if (inputData != null) {
      if ((inputData.getProductId() != null) && (inputData.getProductId() < 0)) {
        throw new InputValidationException("[VALIDATION] Invalid productId");
      }
    }
  }

}
