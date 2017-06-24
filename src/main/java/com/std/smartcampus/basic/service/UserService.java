package com.std.smartcampus.basic.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.smartcampus.basic.annotation.Base64;
import com.std.smartcampus.basic.domain.User;
import com.std.smartcampus.basic.repository.UserRepository;
import com.std.smartcampus.basic.service.base.BaseService;

/**
 * @author Administrator
 */
@Service
@Transactional
public class UserService extends BaseService<User, Integer>{
	
	private UserRepository userRepository;//课程操作
	@Autowired
	public void setRepository(UserRepository repository){
		this.repository = repository;
		this.userRepository = repository;
	}
	
	/**
	 * 根据用户名和密码查找管理员
	 * @param username
	 * @param password
	 */
	@Base64
	public User findByNameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	/**
	 * @param admin 要保存的管理员
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@Base64
	public User save(User admin) {
		return userRepository.save(admin);
	}

	/** 
	 * 
	 */
	@Base64
	public void save(List<User> entities) {
		userRepository.save(entities);
	}

	

	/**
	 * @param username
	 * @param password
	 */
	@Base64
	public Integer updateUserPassword(String username, String password) {
		return userRepository.updateUserPassword(username, password);
	}

	/**
	 * @param username
	 */
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
