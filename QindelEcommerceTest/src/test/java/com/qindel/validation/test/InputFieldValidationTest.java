package com.qindel.validation.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.qindel.dto.PrizesFilterData;
import com.qindel.exception.InputValidationException;
import com.qindel.validation.InputFieldValidation;
import com.qindel.validation.impl.InputFieldValidationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** inputfieldvalidationtest. */
public class InputFieldValidationTest {

  /** module of validation. */
  InputFieldValidation validation;
   
  /** filter dummy. */
  PrizesFilterData dummy;
   
  /** filter empty. */
  PrizesFilterData empty;
   
  /** filter valid. */
  PrizesFilterData dataValid;
   
  /** filter no valid. */
  PrizesFilterData dataNoValid;
   
  /** load information. */
  @BeforeEach
  public void setUp() throws Exception {
    validation = new InputFieldValidationImpl();
    empty = new PrizesFilterData();
    dataValid = new PrizesFilterData();
    dataValid.setProductId(34544);
    dataNoValid = new PrizesFilterData();
    dataNoValid.setProductId(-1);
  }
   
  /** filter is null.*/
  @DisplayName("validation if filter is null")
  @Test
  public void criteriaIfNull() {
    validation.validateFilterFields(dummy);
  }
   
  /** filter is empty. */
  @DisplayName("validation if filter is empty")
  @Test
  public void criteriaIfEmpty() {
    validation.validateFilterFields(empty);
  }
   
  /** filter is empty. */
  @DisplayName("validation if filter is valid")
  @Test
  public void criteriaIfValid() {
    validation.validateFilterFields(dataValid);
  }
   
  /** filter is not empty. */
  @DisplayName("validation if filter is not valid")
  @Test
  public void criteriaIfNotValid() {
    assertThrows(InputValidationException.class,
                 () -> {
        validation.validateFilterFields(dataNoValid);
                 });
  }

}
