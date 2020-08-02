package training.java.sharing_file.service;

import org.springframework.stereotype.Service;

import training.java.sharing_file.dto.UserDTO;

@Service
public interface UserService {
	
	UserDTO insert(UserDTO userDto);
	
	UserDTO findByUserName(String userName);
	
	boolean existsByUsername(String userName);
	
}
