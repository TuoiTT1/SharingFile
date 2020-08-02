package training.java.sharing_file.utils;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;

import training.java.sharing_file.dto.UserDTO;

public class Util {

	public static String getCurrentUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 if (principal instanceof UserDTO) {
		  return ((UserDTO) principal).getUsername();
		 }
		 if (principal instanceof Principal) {
		  return ((Principal) principal).getName();
		 }
		 return String.valueOf(principal);
	}
}
