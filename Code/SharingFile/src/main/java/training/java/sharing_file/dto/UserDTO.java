package training.java.sharing_file.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private String password;

	private char isActive;

	private Set<ActionDocumentDTO> actionDocuments = new HashSet<ActionDocumentDTO>();

	private EmployeeDTO employeeDto;

	public UserDTO(String userName, String password, char isActive, EmployeeDTO employeeDTO) {
		this.userName = userName;
		this.password = password;
		this.isActive = isActive;
		this.employeeDto = employeeDTO;
	}
	
	public UserDTO(String userName, String password, char isActive) {
		this.userName = userName;
		this.password = password;
		this.isActive = isActive;
	}
	
	public UserDTO() {
	}
	
	@Override
	public String toString() {
		return "UserDTO [UserName=" + userName + ", password=" + password + ", isActive=" + isActive
				+ ", actionDocuments=" + actionDocuments + ", employeeDto=" + employeeDto + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return userName;
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
