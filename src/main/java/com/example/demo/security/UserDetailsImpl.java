package com.example.demo.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.models.Role;
import com.example.demo.models.User;

public class UserDetailsImpl implements UserDetails {
	
	private Long id;
	
	private String name;
	
	private String password;
	
	private List<Role> roles;

	public UserDetailsImpl(Long id, String name, String password, List<Role> roles) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public UserDetailsImpl(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	

	public List<Role> getRoles() {
		return roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
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
