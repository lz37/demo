package com.zerozawa.exceptions;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LoanException extends ATMException {

  private static final long serialVersionUID = 5L;

  public LoanException(BaseErrorInfoInterface e) {
    super(e);
  }
}
