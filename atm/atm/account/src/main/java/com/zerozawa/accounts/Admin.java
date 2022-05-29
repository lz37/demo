package com.zerozawa.accounts;

import com.zerozawa.pojo.TbAccount;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Admin extends Account {

  public Admin(TbAccount tbAccount) {
    super(tbAccount);
  }
}
