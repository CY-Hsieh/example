package tw.com.ChYuHsieh.CC109.Controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tw.com.ChYuHsieh.CC109.Service.LoginForm;
import tw.com.ChYuHsieh.CC109.Service.UserService;

@RestController
public class UserController {
  
  @Autowired
  UserService userService;
  
  @PostMapping(value = "/login")
  public Object userLogin(@RequestParam(value="username") String username,@RequestParam(value="password") String password,HttpServletRequest request) {
    Map<String, Object> result = new HashMap<>();   
    
    result.put("result", userService.loadUserByUsername(username));
    
    return result;
  }
}
