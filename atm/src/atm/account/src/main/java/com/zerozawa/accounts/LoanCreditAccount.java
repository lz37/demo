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
public class LoanCreditAccount extends Account implements Loanable, Creditable {

  private Double loan = 0.0;
  private Double ceiling = 0.0;

  public LoanCreditAccount(TbAccount tbAccount) {
    super(tbAccount);
    if (tbAccount.getAccountType().getValue() != LoanCreditAccount.class) {
      throw new ATMException(ExceptionEnum.ACCOUNT_TYPE_ERROR);
    }
    this.loan = tbAccount.getLoan();
    this.ceiling = tbAccount.getCeiling();
  }
}
