package training.java.sharing_file.sercurity;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import training.java.sharing_file.dto.ActionDocumentDTO;
import training.java.sharing_file.dto.EmployeeDTO;
import training.java.sharing_file.dto.UserDTO;
import training.java.sharing_file.model.User;
import training.java.sharing_file.repository.UserRepository;
import training.java.sharing_file.utils.Constants;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username)
				.orElseThrow(() -> (new UsernameNotFoundException(Constants.USER_NOT_FOUND + ": " + username, null)));
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			
			userDTO.setActionDocuments(new HashSet<ActionDocumentDTO>(user.getActionDocuments().stream()
					.map(ad -> modelMapper.map(ad, ActionDocumentDTO.class)).collect(Collectors.toList())));
			userDTO.setEmployeeDto(modelMapper.map(user.getEmployee(), EmployeeDTO.class));
			userDTO.setUserName(username);
			userDTO.setPassword(user.getPassword());
			userDTO.setIsActive(user.getIsActive());
			
			return userDTO;
		}
		return null;
	}

}
