package com.zerozawa.enums;

import com.zerozawa.accounts.Account;
import com.zerozawa.accounts.Admin;
import com.zerozawa.accounts.LoanCreditAccount;
import com.zerozawa.accounts.LoanSavingAccount;
import lombok.Getter;

public enum AccountType {
  Admin(Admin.class,"管理员账户"),
  LoanCreditAccount(LoanCreditAccount.class,"贷款信用账户"),
  LoanSavingAccount(LoanSavingAccount.class,"贷款储蓄账户");

  @Getter
  private Class<? extends Account> value;

  @Getter
  private String label;

  private <T extends Account>AccountType(Class<T> value,String label) {
    this.value = value;
    this.label = label;
  }
}
