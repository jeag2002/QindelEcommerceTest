package com.qindel.validation;

import com.qindel.dto.PrizesFilterData;

/** Validation inputFields. */
public interface InputFieldValidation {
  
  /**
  * input field validation.

  * @param inputData input data
  */
  void validateFilterFields(PrizesFilterData inputData);
  
}
