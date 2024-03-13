package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.error.ErrorResponse;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.exceptions.JWTFilterInvalidException;
import com.google.gson.Gson;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository; 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		try {
			String token = this.recoveryToken(request);
			if (token != null) {
				String subject = jwtTokenService.getSubjectFromToken(token);
				User user = userRepository.findByName(subject).get();
				UserDetailsImpl userDetails = new UserDetailsImpl(user);
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			filterChain.doFilter(request, response);
		} catch (JWTFilterInvalidException ex) {
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(errorResponseJson(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage()));
		}
	}
	
	private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
	
	private String errorResponseJson(int httpCode, String error) {
		ErrorResponse errorResponse = new ErrorResponse(httpCode, error);  
		return new Gson().toJson(errorResponse);
	}

}
