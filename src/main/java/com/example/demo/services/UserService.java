package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Category;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.configs.SecurityConfiguration;
import com.example.demo.dtos.JwtTokenDto;
import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.UserCreateDto;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.models.enums.RoleName;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtTokenService;
import com.example.demo.security.UserDetailsImpl;

@Service
public class UserService {
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public List<User> findAll() { return userRepository.findAll(); }

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuário informado não encontrado."));
    }

    public JwtTokenDto authenticateUser(LoginDto login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtTokenDto(jwtTokenService.generateToken(userDetails));
    }
    
    public void createUser(UserCreateDto user) {
    	User newUser = new User();
    	newUser.setName(user.getName());
    	newUser.setPassword(securityConfiguration.passwordEncoder().encode(user.getPassword()));
    	newUser.setRoles(List.of(new Role(RoleName.ROLE_USER)));
        userRepository.save(newUser);
    }
    
}
