package com.qindel.exception;

/** InputValidationException. */
public class InvalidCriteriaException extends RuntimeException {
  /** id. */
  private static final long serialVersionUID = 7718828512143293558L;

  /**
  * Something happened while trying validate filter input fields.
  */
  public InvalidCriteriaException(String message) {
    super(message);
  }

}
