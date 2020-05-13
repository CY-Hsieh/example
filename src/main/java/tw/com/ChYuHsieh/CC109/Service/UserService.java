package tw.com.ChYuHsieh.CC109.Service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  
  private static final String account = "yes123";
  private static final String password = "123456";
  

  private class UserDeatil implements UserDetails {
    String userAccount;
    String userPassword;
    
    public String getUserAccount() {
      return userAccount;
    }
    public void setUserAccount(String userAccount) {
      this.userAccount = userAccount;
    }
    public String getUserPassword() {
      return userPassword;
    }
    public void setUserPassword(String userPassword) {
      this.userPassword = userPassword;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return null;
    }
    @Override
    public String getPassword() {
      return userPassword;
    }
    @Override
    public String getUsername() {
      return userAccount;
    }
    @Override
    public boolean isAccountNonExpired() {
      return false;
    }
    @Override
    public boolean isAccountNonLocked() {
      return false;
    }
    @Override
    public boolean isCredentialsNonExpired() {
      return false;
    }
    @Override
    public boolean isEnabled() {
      return false;
    }

  }
  
  
  public  UserDeatil  loadUserByUsername(String acc) {
    UserDeatil user = new UserDeatil();
    user.setUserAccount(account);
    user.setUserPassword(password);
    return user;
  }

}
