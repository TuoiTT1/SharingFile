package training.java.sharing_file.service.impl;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import training.java.sharing_file.dto.ActionDocumentDTO;
import training.java.sharing_file.dto.EmployeeDTO;
import training.java.sharing_file.dto.UserDTO;
import training.java.sharing_file.exception.UsernameNotFoundException;
import training.java.sharing_file.model.User;
import training.java.sharing_file.repository.UserRepository;
import training.java.sharing_file.service.UserService;
import training.java.sharing_file.utils.Constants;

@Service
@Transactional(propagation = Propagation.NESTED)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserDTO findByUserName(String userName) throws UsernameNotFoundException {

		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> (new UsernameNotFoundException(Constants.USER_NOT_FOUND + ": " + userName, null)));
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			
			userDTO.setActionDocuments(new HashSet<ActionDocumentDTO>(user.getActionDocuments().stream()
					.map(ad -> modelMapper.map(ad, ActionDocumentDTO.class)).collect(Collectors.toList())));
			userDTO.setEmployeeDto(modelMapper.map(user.getEmployee(), EmployeeDTO.class));
			userDTO.setUserName(userName);
			userDTO.setPassword(user.getPassword());
			userDTO.setIsActive(user.getIsActive());
			
			return userDTO;
		}
		return null;
	}

	@Override
	public boolean existsByUsername(String userName) {
		if (userRepository.existsByUserName(userName)) {
			return true;
		}
		return false;
	}

	@Override
	public UserDTO insert(UserDTO userDto) {
		try {

			User user = modelMapper.map(userDto, User.class);

			user = userRepository.save(user);
			return modelMapper.map(user, UserDTO.class);
		} catch (Exception e) {
			logger.error(Constants.MSG_ERROR + e.getMessage());
			return null;
		}
	}
}
