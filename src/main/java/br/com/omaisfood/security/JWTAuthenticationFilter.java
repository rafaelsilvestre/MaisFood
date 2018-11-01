package br.com.omaisfood.security;

import br.com.omaisfood.model.Company;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    @Autowired
    private CompanyService companyService;

    JWTAuthenticationFilter() { }

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try{
            Credentials credentials = new ObjectMapper()
                    .readValue(req.getInputStream(), Credentials.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword());
            Authentication auth = authenticationManager.authenticate(authToken);

            return auth;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        String username = ((UserSecurity) auth.getPrincipal()).getUsername();
        Long userId = ((UserSecurity) auth.getPrincipal()).getId();
        String token = jwtUtil.generateToken(username);

        // Verify roles
        Boolean isCompany = ((UserSecurity) auth.getPrincipal()).hasRole(Permission.COMPANY);
        Boolean isAdmin = ((UserSecurity) auth.getPrincipal()).hasRole(Permission.ADMIN);
        Boolean isClient = ((UserSecurity) auth.getPrincipal()).hasRole(Permission.CLIENT);

        Permission role = null;
        if (isCompany)
            role = Permission.COMPANY;
        else if (isAdmin)
            role = Permission.ADMIN;
        else if (isClient)
            role = Permission.CLIENT;

        Company company = null;
        if(role != null && role == Permission.COMPANY)
            company = ((UserSecurity) auth.getPrincipal()).getCompany();

        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("access-control-expose-headers", "Authorization");
        res.addHeader("Content-Type", "application/json");

        StringBuilder response = new StringBuilder();
        response.append("{");
        response.append("\"role\": \"" + role.toString() + "\",");
        response.append("\"user_id\": " + userId);

        if(company != null && role != null && role == Permission.COMPANY)
            response.append(",\"company_id\": " + company.getId());
        response.append("}");

        res.getWriter().write(response.toString());
    }
}
