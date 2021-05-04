package com.ag.springlogin.securityConfig;

import com.ag.springlogin.Model.User;
import com.ag.springlogin.Sevice.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomAuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        UsernamePasswordAuthenticationToken token;

        String name = authentication.getName();
        String password = DigestUtils.md5Hex(authentication.getCredentials().toString());

        User user = userService.getUser(name, password);
        if (user != null) {
            Collection<GrantedAuthority> authorities = getGrantedAuthority(user);

            return new UsernamePasswordAuthenticationToken(
                    new org.springframework.security.core.userdetails.User(name, password, authorities),
                    password,
                    authorities);
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Collection<GrantedAuthority> getGrantedAuthority(User user) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (user.getTipo() == 1) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (user.getTipo() == 2) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return grantedAuthorities;
    }
}
