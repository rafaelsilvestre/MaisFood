package br.com.omaisfood.security;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.User;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserSecurity implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private Company company;
    private Collection<? extends GrantedAuthority> authorities;


    UserSecurity() { }

    public  UserSecurity(Long id, String email, String password, List<Permission> permissions) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = permissions.stream().map(x -> new SimpleGrantedAuthority(x.getFlag())).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public boolean hasRole(Permission permission) {
        return getAuthorities().contains(new SimpleGrantedAuthority(permission.getFlag()));
    }
}
