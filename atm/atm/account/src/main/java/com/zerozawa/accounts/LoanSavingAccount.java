package com.zerozawa.accounts;

import com.zerozawa.enums.ExceptionEnum;
import com.zerozawa.exceptions.ATMException;
import com.zerozawa.pojo.TbAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LoanSavingAccount extends Account implements Loanable {

  private Double loan = 0.0;

  public LoanSavingAccount(TbAccount tbAccount) {
    super(tbAccount);
    if (tbAccount.getAccountType().getValue() != LoanSavingAccount.class) {
      throw new ATMException(ExceptionEnum.ACCOUNT_TYPE_ERROR);
    }
    this.loan = tbAccount.getLoan();
  }
}
