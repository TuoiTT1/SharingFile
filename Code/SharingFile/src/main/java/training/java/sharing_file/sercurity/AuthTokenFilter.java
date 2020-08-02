package training.java.sharing_file.sercurity;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import training.java.sharing_file.dto.UserDTO;
import training.java.sharing_file.service.UserService;
import training.java.sharing_file.utils.Constants;

public class AuthTokenFilter extends OncePerRequestFilter{
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class); 
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtils jwtUtils;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = parseJwt(request);
			if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
				String userName = jwtUtils.getUserNameFromJwtToken(jwt);
				
				UserDTO userDetail = userService.findByUserName(userName);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, null);
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		}catch (Exception e) {
			logger.error("Can't set user authentication: {}", e);
		}
		filterChain.doFilter(request, response);
		
	}
	
	private String parseJwt(HttpServletRequest request) {
		String headerAuthor  = request.getHeader(Constants.HEADER_AUTHORIZATION);
		
		if(StringUtils.hasText(headerAuthor) && headerAuthor.startsWith(Constants.HEADER_AUTHORIZATION_START_BEARER)) {
			return headerAuthor.substring(7, headerAuthor.length());
		}
		return null;
	}

}
