package com.zerozawa.exceptions;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BalanceNotEnoughException extends ATMException {

  private static final long serialVersionUID = 2L;

  public BalanceNotEnoughException(BaseErrorInfoInterface e) {
    super(e);
  }
}
