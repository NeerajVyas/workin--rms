package com.casestudy.rms.jwtsecurity;



import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.casestudy.rms.dao.UserDAO;
import com.casestudy.rms.util.ApplicationConstant;

/**
 * This class loads the user by user-name and it checks the active inactive status of user.
 * This class initializes the properties of inbuilt User class of Spring security.
 * which contains the user-name,password, user-role, active-inactive status.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		try {

			com.casestudy.rms.model.User activeUserInfo = userDAO.getUserByEmail(userName);
			int activeInactiveStatus=	activeUserInfo.getUserAIStatus();
				boolean accountNonExpired=false;
				if(activeInactiveStatus == ApplicationConstant.ACTIVE) {
					accountNonExpired=true;
				}
				
			GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getUserRole());
			
			return new User(activeUserInfo.getUserEmail(), activeUserInfo.getUserPassword(), true,accountNonExpired,true,true,Arrays.asList(authority));

		} catch (Exception e) {
			throw new UsernameNotFoundException("Invalid Credentials.");
		}
	}
}