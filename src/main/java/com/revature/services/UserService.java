package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	RSSService rssService;
	

	public List<User> getAllUser(){
		List<User> users = userRepository.findAll();

		if(users.size() != 0) {
			System.out.print(users.size());
			return users;		
		}

		return null;
	}
	
	/**
	 * @author Ryan Clayton
	 * @param id this is the user's Id
	 * @return User this returns the user entity with updated RSS points
	 */

	public User getUserById(int id) {
			try {
				Optional<User> optUser = userRepository.findById(id);
				if(optUser.isPresent()) {
					return optUser.get();
				
					//user.setPoints(rssService.getPoints(id));
					//return userRepository.save(user);
				}
			}catch(Exception e) {

				//Logger logger = Logger.getLogger(UserService.class);
				//logger.error("error in UserService getUserById", e);
				return null;
			}
			return null;
	}
	
	
//	public Collection<GrantedAuthority> getAuthority(User u){
//		Collection<GrantedAuthority>auths = new ArrayList<>();
//		Optional<User> optUser = userRepository.findById(u.getUserID());
//		SimpleGrantedAuthority a= null; 
//		
//		if (optUser.isPresent()) {
//			User user = optUser.get();
//			if(user.isAdmin()) {
//				a = new SimpleGrantedAuthority("admin");
//				auths.add(a);
//				a = new SimpleGrantedAuthority("user");
//				auths.add(a);
//			}else {
//				a = new SimpleGrantedAuthority("user");
//				auths.add(a);
//				
//			}
//		}
//		return auths;
//		
//	}

}
