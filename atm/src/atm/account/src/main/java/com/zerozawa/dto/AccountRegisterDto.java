package com.zerozawa.dto;

import com.zerozawa.enums.AccountType;
import lombok.Data;

@Data
public class AccountRegisterDto {

  private String name;
  private String password;
  private String personalId;
  private String email;
  private AccountType accountType;
}
