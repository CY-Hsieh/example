package tw.com.ChYuHsieh.CC109.Config.Filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import tw.com.ChYuHsieh.CC109.Utils.JwtUtils;

public class JWTAuthenticationFilter extends GenericFilterBean {
  
  @Autowired
  JwtUtils jwtUtils;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    Authentication authentication = jwtUtils
        .getAuthentication((HttpServletRequest)request);

    SecurityContextHolder.getContext()
            .setAuthentication(authentication);
    chain.doFilter(request,response);
    
  }

}
