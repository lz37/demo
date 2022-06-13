package com.zerozawa.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zerozawa.accounts.Account;
import com.zerozawa.accounts.Creditable;
import com.zerozawa.accounts.Loanable;
import com.zerozawa.dao.AccountDao;
import com.zerozawa.dao.PersonalInfoDao;
import com.zerozawa.enums.AccountType;
import com.zerozawa.enums.ExceptionEnum;
import com.zerozawa.exceptions.ATMException;
import com.zerozawa.exceptions.BalanceNotEnoughException;
import com.zerozawa.exceptions.LoanException;
import com.zerozawa.exceptions.LoginException;
import com.zerozawa.exceptions.RegisterException;
import com.zerozawa.pojo.TbAccount;
import com.zerozawa.pojo.VPersonalInfo;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

  @Autowired
  private PersonalInfoDao personalInfoDao;

  @Autowired
  private AccountDao accountDao;

  /**
   * 用户开户
   *
   * @param name
   * @param password
   * @param personalId
   * @param email
   * @param accountType
   * @return
   * @throws ReflectiveOperationException
   */
  public Account register(
    String name,
    String password,
    String personalId,
    String email,
    AccountType accountType
  )
    throws ReflectiveOperationException {
    if (accountType == AccountType.Admin) {
      throw new RegisterException(ExceptionEnum.REGISTER_ADMIN);
    }
    TbAccount tbAccount = new TbAccount(
      null,
      name,
      personalId,
      email,
      0.0,
      password,
      0.0,
      0.0,
      accountType
    );
    int success = accountDao.insert(tbAccount);
    if (success == 0) {
      throw new RegisterException(ExceptionEnum.REGISTER_FAIL_TO_SAVE);
    }
    Account account = null;
    account =
      accountType
        .getValue()
        .getConstructor(TbAccount.class)
        .newInstance(tbAccount);
    return account;
  }

  /**
   * 用户登录
   *
   * @param id
   * @param password
   * @return
   * @throws ReflectiveOperationException
   */
  public Account login(Long id, String password)
    throws ReflectiveOperationException {
    TbAccount tbAccount = accountDao.selectById(id);
    if (tbAccount == null) {
      throw new LoginException(ExceptionEnum.LOGIN_ACCOUNT_NOT_EXIST);
    }
    if (tbAccount.getPassword().equals(password)) {
      Account account = null;
      account =
        tbAccount
          .getAccountType()
          .getValue()
          .getConstructor(TbAccount.class)
          .newInstance(tbAccount);
      return account;
    } else {
      throw new LoginException(ExceptionEnum.LOGIN_PASSWORD_ERROR);
    }
  }

  /**
   * 用户存款
   *
   * @param id
   * @param money
   * @return
   * @throws ReflectiveOperationException
   */
  @Transactional
  public Account deposit(Long id, Double money)
    throws ReflectiveOperationException {
    TbAccount tbAccount = accountDao.selectById(id);
    tbAccount.setBalance(tbAccount.getBalance() + money);
    accountDao.updateById(tbAccount);
    Account account = null;
    account =
      tbAccount
        .getAccountType()
        .getValue()
        .getConstructor(TbAccount.class)
        .newInstance(tbAccount);
    return account;
  }

  /**
   * 用户取款
   *
   * @param id
   * @param money
   * @return
   * @throws ReflectiveOperationException
   */
  @Transactional
  public Account withdraw(Long id, Double money)
    throws ReflectiveOperationException {
    TbAccount tbAccount = accountDao.selectById(id);
    Account account = null;
    account =
      tbAccount
        .getAccountType()
        .getValue()
        .getConstructor(TbAccount.class)
        .newInstance(tbAccount);
    if (
      (
        account instanceof Creditable
          ? ((Creditable) account).getCeiling()
          : 0.0
      ) +
      account.getBalance() <
      money
    ) {
      throw new BalanceNotEnoughException(ExceptionEnum.BALANCE_NOT_ENOUGH);
    } else {
      tbAccount.setBalance(tbAccount.getBalance() - money);
      accountDao.updateById(tbAccount);
      account.setBalance(tbAccount.getBalance());
    }
    return account;
  }

  /**
   * 设置透支额度
   *
   * @param id
   * @param ceiling
   * @return
   * @throws ReflectiveOperationException
   */
  @Transactional
  public Account setCeiling(Long id, Double ceiling)
    throws ReflectiveOperationException {
    TbAccount tbAccount = accountDao.selectById(id);
    if(tbAccount==null){
      throw new ATMException(ExceptionEnum.LOGIN_ACCOUNT_NOT_EXIST);
    }
    if (tbAccount.getBalance() < 0) {
      throw new ATMException(ExceptionEnum.CEILING_NOT_PERMIT);
    }
    Account account = null;
    account =
      tbAccount
        .getAccountType()
        .getValue()
        .getConstructor(TbAccount.class)
        .newInstance(tbAccount);
    if (account instanceof Creditable) {
      ((Creditable) account).setCeiling(ceiling);
      tbAccount.setCeiling(ceiling);
      accountDao.updateById(tbAccount);
    } else {
      throw new ATMException(ExceptionEnum.CEILING_NOT_ENABLE);
    }
    return account;
  }

  /**
   * 转账功能
   *
   * @param from
   * @param to
   * @param money
   * @return
   * @throws ReflectiveOperationException
   */
  @Transactional
  public Account transfer(Long from, Long to, Double money)
    throws ReflectiveOperationException {
    Account res = withdraw(from, money);
    deposit(to, money);
    return res;
  }

  /**
   * 贷款
   *
   * @param id
   * @param money
   * @return
   * @throws ReflectiveOperationException
   */
  @Transactional
  public Account requestLoan(Long id, Double money)
    throws ReflectiveOperationException {
    TbAccount tbAccount = accountDao.selectById(id);
    Account account = tbAccount
      .getAccountType()
      .getValue()
      .getConstructor(TbAccount.class)
      .newInstance(tbAccount);
    if (account instanceof Loanable) {
      tbAccount.setLoan(tbAccount.getLoan() + money);
      accountDao.updateById(tbAccount);
      return deposit(id, money);
    } else {
      throw new ATMException(ExceptionEnum.LOAN_NOT_ENABLE);
    }
  }

  /**
   * 还贷款
   *
   * @param id
   * @param money
   * @return
   * @throws ReflectiveOperationException
   */
  @Transactional
  public Account payLoan(Long id, Double money)
    throws ReflectiveOperationException {
    TbAccount tbAccount = accountDao.selectById(id);
    Account account = tbAccount
      .getAccountType()
      .getValue()
      .getConstructor(TbAccount.class)
      .newInstance(tbAccount);
    if (account instanceof Loanable) {
      if (tbAccount.getLoan() < money) {
        throw new LoanException(ExceptionEnum.LOAN_PAY_TO_MUCH);
      }
      tbAccount.setLoan(tbAccount.getLoan() - money);
      accountDao.updateById(tbAccount);
      return withdraw(id, money);
    } else {
      throw new ATMException(ExceptionEnum.LOAN_NOT_ENABLE);
    }
  }

  /**
   * 查询所有的账户类型
   * @return
   */
  public Map<AccountType,String> getAllAccountTypeLabel() {
    Map<AccountType,String> res=new HashMap<>();
    for(AccountType accountType : AccountType.values()) {
      res.put(accountType, accountType.getLabel());
    }
    return res;
  }

  /**
   * 统计银行所有账户余额总数
   *
   * @return
   */
  public Double getSumOfBalance() {
    VPersonalInfo res = personalInfoDao.selectOne(
      new QueryWrapper<VPersonalInfo>().select("sum(balance) as balance")
    );
    return res.getBalance();
  }

  /**
   * 统计所有信用账户透支额度总数
   *
   * @return
   */
  public Double getSumOfCeiling() {
    QueryWrapper<VPersonalInfo> wrapper = new QueryWrapper<>();
    wrapper.select("sum(ceiling) as ceiling");
    VPersonalInfo res = personalInfoDao.selectOne(wrapper);
    return res.getCeiling();
  }

  /**
   * 统计所有账户贷款的总数
   *
   * @return
   */
  public Double getSumOfLoan() {
    QueryWrapper<VPersonalInfo> wrapper = new QueryWrapper<>();
    wrapper.select("sum(loan) as loan");
    VPersonalInfo res = personalInfoDao.selectOne(wrapper);
    return res.getLoan();
  }

  /**
   * 所有用户的总资产排名
   *
   * @param page 当前页
   * @param rows 每一页的条数
   * @return
   */
  public Page<VPersonalInfo> getRankOfBalance(Long page, Long rows) {
    QueryWrapper<VPersonalInfo> wrapper = new QueryWrapper<>();
    wrapper.orderByDesc("balance");
    Page<VPersonalInfo> pInfoPage = personalInfoDao.selectPage(
      new Page<>(page, rows),
      wrapper
    );
    return pInfoPage;
  }
}
