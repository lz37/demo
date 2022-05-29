package com.zerozawa.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

  @Override
  public int getOrder() {
    return 0;
  }

  @Override
  public Mono<Void> filter(
    ServerWebExchange exchange,
    GatewayFilterChain chain
  ) {
    ServerHttpRequest req = exchange
      .getRequest()
      .mutate()
      .header("Authorization", "true")
      .build();
    return chain.filter(
      exchange.mutate().request(req.mutate().build()).build()
    );
  }
}
