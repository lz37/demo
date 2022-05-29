package com.zerozawa.dto;

import com.zerozawa.enums.ExceptionEnum;
import com.zerozawa.exceptions.BaseErrorInfoInterface;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto {

  /**
   * 响应代码
   */
  private String code;

  /**
   * 响应消息
   */
  private String message;

  /**
   * 响应结果
   */
  private Object result;

  /**
   * 即success(null)
   * @return
   */
  public static ResponseDto success() {
    return success(null);
  }

  /**
   * 成功，code和message与ATMExceptionEnum.SUCCESS参数一致
   * @param result
   * @return
   */
  public static ResponseDto success(Object result) {
    return new ResponseDto(
      ExceptionEnum.SUCCESS.getResultCode(),
      ExceptionEnum.SUCCESS.getResultMsg(),
      result
    );
  }

  /**
   * 失败
   * @param code
   * @param message
   * @return
   */
  public static ResponseDto error(String code, String message) {
    return new ResponseDto(code, message, null);
  }

  public static ResponseDto error(BaseErrorInfoInterface e) {
    return new ResponseDto(e.getResultCode(), e.getResultMsg(), null);
  }
}
