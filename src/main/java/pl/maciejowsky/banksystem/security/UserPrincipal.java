package pl.maciejowsky.banksystem.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.maciejowsky.banksystem.model.User;

import java.util.*;

public class UserPrincipal implements UserDetails {
    private User user;

    public UserPrincipal(User user) {

       this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        this.user.getAuthoritiesList().forEach(p-> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorityList.add(authority);
        });
        this.user.getRolesList().forEach(r-> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+r);
            authorityList.add(authority);
        });
       return authorityList;

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
