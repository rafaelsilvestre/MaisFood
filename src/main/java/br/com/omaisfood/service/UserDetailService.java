package br.com.omaisfood.service;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.User;

import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.repository.UserRepository;
import br.com.omaisfood.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyService companyService;

    UserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(email);
        }

        UserSecurity userLogged = new UserSecurity(user.getId(), user.getEmail(), user.getPassword(), user.getPermissions());

        if(userLogged.hasRole(Permission.COMPANY)) {
            Company company = this.companyService.getCompanyByUserId(user.getId());
            if(company != null) userLogged.setCompany(company);
        }

        return userLogged;
    }

    public static UserSecurity getUserAuthenticated() {
        try{
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }
}
