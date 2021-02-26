package com.androprex.jcagro.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.androprex.jcagro.model.User;
import com.androprex.jcagro.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<User> userOptional = userRepository.findByMobile(username);
	        User user = userOptional
	                .orElseThrow(() -> new UsernameNotFoundException("No Mobile No.Found " +
	                        "Found with username : " + username));

	        return new org.springframework.security
	                .core.userdetails.User(user.getMobile(), user.getPassword(),
	                user.isEnabled(), true, true,
	                true, getAuthorities("USER"));
	}
	
	


	private Collection<? extends GrantedAuthority> getAuthorities(String string) {
		// TODO Auto-generated method stub
		return Collections.singletonList(new SimpleGrantedAuthority(string));
	}


	


	

}
