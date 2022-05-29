package com.zerozawa.enums;

import com.zerozawa.exceptions.BaseErrorInfoInterface;
import lombok.Getter;

public enum ExceptionEnum implements BaseErrorInfoInterface {
  SUCCESS("00000", "success"),
  ACCOUNT_TYPE_ERROR("00001", "ETC: Account type error"),
  SERVER_ERROR("00002", "ETC: Server error"),
  NUM_FORMAT_ERROR("00003", "ETC: Number format error"),
  NOT_AUTHORIZED("00004", "ETC: Not authorized"),
  REGISTER_ADMIN("10001", "Register: Admin account can not be registered"),
  REGISTER_FAIL_TO_SAVE("10002", "Register: Message fail to save"),
  LOGIN_ACCOUNT_NOT_EXIST("20001", "Login: Account not exist"),
  LOGIN_PASSWORD_ERROR("20002", "Login: Password error"),
  LOGIN_NOT_LOGIN("20003", "Login: Account not login"),
  BALANCE_NOT_ENOUGH("30001", "Balance: Not enough"),
  CEILING_NOT_PERMIT("40001", "Ceiling: Not permit"),
  CEILING_NOT_ENABLE("40002", "Ceiling: Not enable"),
  LOAN_NOT_ENABLE("50001", "Loan: Not enable"),
  LOAN_PAY_TO_MUCH("50002", "Loan: Pay to much"),
  ADMIN_ONLY("60001", "Admin: Admin only"),
  UNKNOWN_ERROR("70001", "Unknown: Unknown error");

  /**
   * 错误码
   */
  @Getter
  private final String resultCode;

  /**
   * 错误描述
   */
  @Getter
  private final String resultMsg;

  ExceptionEnum(String resultCode, String resultMsg) {
    this.resultCode = resultCode;
    this.resultMsg = resultMsg;
  }
}
