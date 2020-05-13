package tw.com.ChYuHsieh.CC109.Config.Filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import tw.com.ChYuHsieh.CC109.Utils.JwtUtils;


public class LoginFilter extends AbstractAuthenticationProcessingFilter {
  
  @Autowired
  JwtUtils jwtUtils;

  public LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authManager) {
    super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
    setAuthenticationManager(authManager);
  }
  
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
      String username = request.getParameter("username");
      String password = request.getParameter("password");
  
      if (username == null) {
          username = "";
      }
  
      if (password == null) {
          password = "";
      }
  
      username = username.trim();
      // 觸發AuthenticationManager
      return getAuthenticationManager().authenticate(
              new UsernamePasswordAuthenticationToken(
                      username,password
              )
      );
  }
  
  @SuppressWarnings("static-access")
  @Override
  protected void successfulAuthentication(
          HttpServletRequest req,
          HttpServletResponse res, FilterChain chain,
          Authentication auth) throws IOException, ServletException {
      // 登入成功，將token透過JwtUtil放到res中
    jwtUtils.addAuthentication(res, auth);
  }
  
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getOutputStream().println("Loing error!!!");
  }


}
