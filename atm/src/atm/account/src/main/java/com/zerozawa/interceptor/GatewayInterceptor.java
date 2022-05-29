package com.zerozawa.interceptor;

import com.zerozawa.enums.ExceptionEnum;
import com.zerozawa.exceptions.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Order(1)
@Component
public class GatewayInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler
  )
    throws Exception {
    String auth = request.getHeader("Authorization");
    if (auth != null && auth.equals("true")) {
      return true;
    } else {
      throw new LoginException(ExceptionEnum.NOT_AUTHORIZED);
    }
  }
}
