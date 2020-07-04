package ua.testing.repairagency.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ua.testing.repairagency.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static HashMap<String,String > registeredRoles = new HashMap<>();

    static {
        registeredRoles.put(Constants.ADMIN_ROLE,"/admin");
        registeredRoles.put(Constants.USER_ROLE,"/user");
        registeredRoles.put(Constants.MASTER_ROLE,"/master");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        List<GrantedAuthority> roles = AuthorityUtils.createAuthorityList(String.valueOf(authentication.getAuthorities()));
        String authority = roles.get(0).toString().replaceAll("[\\[\\]]","");

        httpServletResponse.sendRedirect(registeredRoles.get(authority));
    }
}
