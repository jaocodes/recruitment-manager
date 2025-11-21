package br.com.jotace.recruitment_manager.security;

import br.com.jotace.recruitment_manager.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        SecurityContextHolder.getContext().setAuthentication(null);

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var subjectToken = this.jwtProvider.validateToken(authHeader);
            if(subjectToken.isEmpty()){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            request.setAttribute("company_id", subjectToken);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(subjectToken,null, Collections.emptySet());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
            filterChain.doFilter(request, response);
    }

}
