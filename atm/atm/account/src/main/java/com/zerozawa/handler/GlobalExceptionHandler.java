package com.zerozawa.handler;

import com.zerozawa.dto.ResponseDto;
import com.zerozawa.enums.ExceptionEnum;
import com.zerozawa.exceptions.ATMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(
    GlobalExceptionHandler.class
  );

  /**
   * 业务代码异常处理
   * @param e
   * @return
   */
  @ExceptionHandler(ATMException.class)
  public ResponseDto ATMExceptionHandler(ATMException e) {
    logger.error("ATMException: {}", e.getErrorMsg());
    return ResponseDto.error(e.getErrorCode(), e.getErrorMsg());
  }

  /**
   * 反射异常处理
   * @param e
   * @return
   */
  @ExceptionHandler(ReflectiveOperationException.class)
  public ResponseDto ReflectiveOperationExceptionHandler(
    ReflectiveOperationException e
  ) {
    logger.error("ReflectiveOperationException: {}", e.getMessage());
    return ResponseDto.error(ExceptionEnum.SERVER_ERROR);
  }

  /**
   * 数字非法输入
   * @param e
   * @return
   */
  @ExceptionHandler(NumberFormatException.class)
  public ResponseDto NumberFormatExceptionHandler(NumberFormatException e) {
    logger.error("NumberFormatException: {}", e.getMessage());
    return ResponseDto.error(ExceptionEnum.NUM_FORMAT_ERROR);
  }

  /**
   * 其他异常
   * @param e
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ResponseDto ExceptionHandler(Exception e) {
    logger.error("Exception: {}", e.getMessage());
    return ResponseDto.error(ExceptionEnum.UNKNOWN_ERROR);
  }
}
