package com.zerozawa.accounts;

import com.zerozawa.pojo.TbAccount;
import lombok.Data;

@Data
public abstract class Account {

  private Long id;
  private String name;
  private String personalId;
  private String email;
  private Double balance = 0.0;

  private String password;

  public Account(TbAccount tbAccount) {
    this.id = tbAccount.getId();
    this.name = tbAccount.getName();
    this.personalId = tbAccount.getPersonalId();
    this.email = tbAccount.getEmail();
    this.balance = tbAccount.getBalance();
    this.password = tbAccount.getPassword();
  }
}
