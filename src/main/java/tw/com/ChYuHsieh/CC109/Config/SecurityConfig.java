package tw.com.ChYuHsieh.CC109.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tw.com.ChYuHsieh.CC109.Config.Filter.JWTAuthenticationFilter;
import tw.com.ChYuHsieh.CC109.Config.Filter.LoginFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {

      // 使用自定义身份验证组件
      auth.authenticationProvider(new CustomAuthenticationProvider());

  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      //禁用 csrf
      http.cors().and().csrf().disable().authorizeRequests()
              //允许以下请求
              .antMatchers("/login").permitAll()
              // 所有请求需要身份认证
              .anyRequest().authenticated()
              .and()
              //验证登陆
              .addFilterBefore(new LoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)
              //验证token
              .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

}
