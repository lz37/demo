package com.zerozawa.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class VPersonalInfo {

  @TableId
  private String personalId;

  private Double balance;
  private Double loan;
  private Double ceiling;
}
