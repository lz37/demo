package com.zerozawa.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zerozawa.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbAccount {

  @TableId(type = IdType.AUTO)
  private Long id;

  private String name;
  private String personalId;
  private String email;
  private Double balance = 0.0;

  private String password;

  private Double loan;
  private Double ceiling;
  private AccountType accountType;
}
