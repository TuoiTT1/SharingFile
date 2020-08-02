package training.java.sharing_file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.java.sharing_file.dto.UserDTO;
import training.java.sharing_file.sercurity.JwtUtils;
import training.java.sharing_file.service.UserService;
import training.java.sharing_file.service.request.JwtRequest;
import training.java.sharing_file.service.response.JwtResponse;
import training.java.sharing_file.service.response.MessageResponse;
import training.java.sharing_file.utils.Constants;

@RestController
@RequestMapping(value = "/user")
public class UserController {

//	@Autowired
//	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @RequestBody JwtRequest authenRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenRequest.getUserName(), authenRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDTO userDTO = (UserDTO) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDTO.getUsername()));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Validated @RequestBody JwtRequest userRegist) {
		if (userService.existsByUsername(userRegist.getUserName())) {
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.USERNAME_ALREADY));
		}
		
		UserDTO userDTO = new UserDTO(userRegist.getUserName(), encoder.encode(userRegist.getPassword()),
				Constants.IS_ACTIVE);

		userService.insert(userDTO);
		return ResponseEntity.ok(new MessageResponse(Constants.REGISTER_USER_SUCCESS));
	}
}
