package com.zerozawa.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ATMException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * 错误码
   */
  protected String errorCode;
  /**
   * 错误信息
   */
  protected String errorMsg;

  public ATMException(BaseErrorInfoInterface e) {
    super(e.getResultCode());
    this.errorCode = e.getResultCode();
    this.errorMsg = e.getResultMsg();
  }
}
