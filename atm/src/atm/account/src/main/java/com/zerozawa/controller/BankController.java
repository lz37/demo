package com.zerozawa.controller;

import com.zerozawa.accounts.Account;
import com.zerozawa.dto.AccountLoginDto;
import com.zerozawa.dto.AccountRegisterDto;
import com.zerozawa.dto.ResponseDto;
import com.zerozawa.enums.AccountType;
import com.zerozawa.enums.ExceptionEnum;
import com.zerozawa.exceptions.ATMException;
import com.zerozawa.service.BankService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {

  @Autowired
  private BankService bankService;

  /**
   * PUT请求 用户开户 需要参数如下 String name, String password, String personalId, String
   * email, AccountType accountType
   *
   * @param dto AccountRegisterDto
   * @return
   * @throws ReflectiveOperationException
   */
  @PutMapping("/account")
  public ResponseDto register(@RequestBody AccountRegisterDto dto)
    throws ReflectiveOperationException {
    return ResponseDto.success(
      bankService.register(
        dto.getName(),
        dto.getPassword(),
        dto.getPersonalId(),
        dto.getEmail(),
        dto.getAccountType()
      )
    );
  }

  /**
   * POST请求 用户登录 需要参数如下 Long id, String password
   *
   * @param dto      AccountLoginDto
   * @param response
   * @return
   * @throws ReflectiveOperationException
   */
  @PostMapping("/account")
  public ResponseDto login(
    @RequestBody AccountLoginDto dto,
    HttpServletResponse response
  )
    throws ReflectiveOperationException {
    Cookie id = new Cookie("id", dto.getId().toString());
    id.setPath("/");
    Cookie password = new Cookie("password", dto.getPassword());
    password.setPath("/");
    response.addCookie(id);
    response.addCookie(password);
    Account account = bankService.login(dto.getId(), dto.getPassword());
    return ResponseDto.success(account);
  }

  /**
   * GET请求 获取用户信息 需要Cookie
   * @param id
   * @param password
   * @return
   * @throws ReflectiveOperationException
   */
  @GetMapping("/account")
  public ResponseDto getAccount(
    @CookieValue("id") String id,
    @CookieValue("password") String password
  )
    throws ReflectiveOperationException {
    Account account = bankService.login(Long.parseLong(id), password);
    return ResponseDto.success(account);
  }

  /**
   * GET请求 获取cookie (账户和密码)(前端)
   *
   * @param id
   * @param password
   * @return
   */
  @GetMapping("/cookie")
  public ResponseDto getCookie(
    @CookieValue("id") String id,
    @CookieValue("password") String password
  ) {
    return ResponseDto.success(new AccountLoginDto(Long.valueOf(id), password));
  }

  /**
   * POST请求 用户存款
   *
   * @param id       (Cookie)
   * @param password (Cookie)
   * @param money    (路径变量)
   * @return
   * @throws ReflectiveOperationException
   */
  @PostMapping("/balance/{money}")
  public ResponseDto deposit(
    @CookieValue("id") Long id,
    @CookieValue("password") String password,
    @PathVariable Double money
  )
    throws ReflectiveOperationException {
    if (bankService.login(id, password) != null) {
      bankService.deposit(id, money);
      return ResponseDto.success();
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }

  /**
   * DELETE请求 用户取款
   *
   * @param id
   * @param password
   * @param money
   * @return
   * @throws ReflectiveOperationException
   */
  @DeleteMapping("/balance/{money}")
  public ResponseDto withdraw(
    @CookieValue("id") Long id,
    @CookieValue("password") String password,
    @PathVariable Double money
  )
    throws ReflectiveOperationException {
    if (bankService.login(id, password) != null) {
      bankService.withdraw(id, money);
      return ResponseDto.success();
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }

  /**
   * POST请求 转账功能
   *
   * @param id
   * @param password
   * @param to
   * @param money
   * @throws ReflectiveOperationException
   */
  @PostMapping("/transfer/{to}/{money}")
  public ResponseDto transfer(
    @CookieValue("id") Long id,
    @CookieValue("password") String password,
    @PathVariable Long to,
    @PathVariable Double money
  )
    throws ReflectiveOperationException {
    if (bankService.login(id, password) != null) {
      return ResponseDto.success(bankService.transfer(id, to, money));
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }

  /**
   * POST请求 贷款
   *
   * @param id
   * @param password
   * @param money
   * @return
   * @throws ReflectiveOperationException
   */
  @PostMapping("/loan/{money}")
  public ResponseDto requestLoan(
    @CookieValue("id") Long id,
    @CookieValue("password") String password,
    @PathVariable Double money
  )
    throws ReflectiveOperationException {
    if (bankService.login(id, password) != null) {
      return ResponseDto.success(bankService.requestLoan(id, money));
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }

  /**
   * DELETE请求 还贷款
   *
   * @param id
   * @param password
   * @param money
   * @return
   * @throws ReflectiveOperationException
   */
  @DeleteMapping("/loan/{money}")
  public ResponseDto payLoan(
    @CookieValue("id") Long id,
    @CookieValue("password") String password,
    @PathVariable Double money
  )
    throws ReflectiveOperationException {
    if (bankService.login(id, password) != null) {
      return ResponseDto.success(bankService.payLoan(id, money));
    } else {
      throw new ATMException(ExceptionEnum.LOGIN_NOT_LOGIN);
    }
  }

  /**
   * GET请求 获取所有账户类型
   * @return
   */
  @GetMapping("/sum/account-type")
  public ResponseDto getAllAccountTypeLabel() {
    Map<AccountType, String> allAccountTypeLabel = bankService.getAllAccountTypeLabel();
    List<Map<String, String>> json = new ArrayList<>();
    for (Map.Entry<AccountType, String> entry : allAccountTypeLabel.entrySet()) {
      json.add(
        new HashMap<String, String>() {
          {
            put("key", entry.getKey().toString());
            put("label", entry.getValue());
          }
        }
      );
    }
    return ResponseDto.success(json);
  }
}
