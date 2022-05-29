package com.zerozawa.controller;

import com.zerozawa.accounts.Account;
import com.zerozawa.accounts.Admin;
import com.zerozawa.dto.ResponseDto;
import com.zerozawa.enums.ExceptionEnum;
import com.zerozawa.exceptions.ATMException;
import com.zerozawa.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private BankService bankService;

  /**
   * GET请求 判断是否为管理员
   * @param id
   * @param password
   * @return
   * @throws ReflectiveOperationException
   */
  @GetMapping("/isadmin")
  public ResponseDto isAdmin(
    @CookieValue("id") String id,
    @CookieValue("password") String password
  )
    throws ReflectiveOperationException {
    Account admin = bankService.login(Long.parseLong(id), password);
    if (admin instanceof Admin) {
      return ResponseDto.success(true);
    } else {
      return ResponseDto.success(false);
    }
  }

  /**
   * POST请求 设置透支额度 需要管理员权限
   *
   * @param cookieId
   * @param cookiePassword
   * @param id
   * @param ceiling
   * @return
   * @throws ReflectiveOperationException
   */
  @PostMapping("/ceiling/{id}/{ceiling}")
  public ResponseDto setCeiling(
    @CookieValue("id") Long cookieId,
    @CookieValue("password") String cookiePassword,
    @PathVariable Long id,
    @PathVariable Double ceiling
  )
    throws ReflectiveOperationException {
    Account account = bankService.login(cookieId, cookiePassword);
    if (account != null) {
      if (account instanceof Admin) {
        return ResponseDto.success(bankService.setCeiling(id, ceiling));
      } else {
        throw new ATMException(ExceptionEnum.ADMIN_ONLY);
      }
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }

  /**
   * GET请求 统计银行所有账户余额总数 需要管理员权限
   *
   * @param cookieId
   * @param cookiePassword
   * @return
   * @throws ReflectiveOperationException
   */
  @GetMapping("/sum/balance")
  public ResponseDto getSumOfBalance(
    @CookieValue("id") Long cookieId,
    @CookieValue("password") String cookiePassword
  )
    throws ReflectiveOperationException {
    Account account = bankService.login(cookieId, cookiePassword);
    if (account != null) {
      if (account instanceof Admin) {
        return ResponseDto.success(bankService.getSumOfBalance());
      } else {
        throw new ATMException(ExceptionEnum.ADMIN_ONLY);
      }
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }

  /**
   * GET请求 统计所有信用账户透支额度总数 需要管理员权限
   *
   * @param cookieId
   * @param cookiePassword
   * @return
   * @throws ReflectiveOperationException
   */
  @GetMapping("/sum/ceiling")
  public ResponseDto getSumOfCeiling(
    @CookieValue("id") Long cookieId,
    @CookieValue("password") String cookiePassword
  )
    throws ReflectiveOperationException {
    Account account = bankService.login(cookieId, cookiePassword);
    if (account != null) {
      if (account instanceof Admin) {
        return ResponseDto.success(bankService.getSumOfCeiling());
      } else {
        throw new ATMException(ExceptionEnum.ADMIN_ONLY);
      }
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }

  /**
   * GET请求 统计所有账户贷款的总数 需要管理员权限
   *
   * @param cookieId
   * @param cookiePassword
   * @return
   * @throws ReflectiveOperationException
   */
  @GetMapping("/sum/loan")
  public ResponseDto getSumOfLoan(
    @CookieValue("id") Long cookieId,
    @CookieValue("password") String cookiePassword
  )
    throws ReflectiveOperationException {
    Account account = bankService.login(cookieId, cookiePassword);
    if (account != null) {
      if (account instanceof Admin) {
        return ResponseDto.success(bankService.getSumOfLoan());
      } else {
        throw new ATMException(ExceptionEnum.ADMIN_ONLY);
      }
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }

  /**
   * GET请求 所有用户的总资产排名 需要管理员权限
   *
   * @param page
   * @param rows
   * @param cookieId
   * @param cookiePassword
   * @return
   * @throws ReflectiveOperationException
   */
  @GetMapping("/rank/{page}/{rows}")
  public ResponseDto getRankOfBalance(
    @PathVariable Long page,
    @PathVariable Long rows,
    @CookieValue("id") Long cookieId,
    @CookieValue("password") String cookiePassword
  )
    throws ReflectiveOperationException {
    Account account = bankService.login(cookieId, cookiePassword);
    if (account != null) {
      if (account instanceof Admin) {
        return ResponseDto.success(bankService.getRankOfBalance(page, rows));
      } else {
        throw new ATMException(ExceptionEnum.ADMIN_ONLY);
      }
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }
}
