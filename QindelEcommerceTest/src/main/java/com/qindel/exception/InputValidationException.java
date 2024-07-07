package com.qindel.exception;

/** InputValidationException. */
public class InputValidationException extends RuntimeException {
  /** id. */
  private static final long serialVersionUID = 7718828512143293558L;

  /**
  * Something happened while trying validate filter input fields.
  */
  public InputValidationException(String message) {
    super(message);
  }

}
