package com.zerozawa.exceptions;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RegisterException extends ATMException {

  private static final long serialVersionUID = 3L;

  public RegisterException(BaseErrorInfoInterface e) {
    super(e);
  }
}
