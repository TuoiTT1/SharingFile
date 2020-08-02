package training.java.sharing_file.sercurity;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import training.java.sharing_file.utils.Constants;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error(Constants.ERROR_UNAUTHORIZED + ": {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constants.ERROR_UNAUTHORIZED);
		
	}

}
