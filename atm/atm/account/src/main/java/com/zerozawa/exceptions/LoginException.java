package com.zerozawa.exceptions;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LoginException extends ATMException {

  private static final long serialVersionUID = 4L;

  public LoginException(BaseErrorInfoInterface e) {
    super(e);
  }
}
