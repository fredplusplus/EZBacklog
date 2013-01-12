package webplus.ezbacklog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import webplus.ezbacklog.module.BackloggerModule;
import webplus.ezbacklog.values.RequestAttribute;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired private BackloggerModule backloggerModule;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			UserService userService = UserServiceFactory.getUserService();
			User user = userService.getCurrentUser();
			if (user == null) {
				((HttpServletResponse) response).sendRedirect(userService.createLoginURL(((HttpServletRequest) request)
						.getRequestURI()));
			} else {
				backloggerModule.registerBacklogger();
				request.setAttribute(RequestAttribute.USER_NAME, user.getNickname());
			}
		}
		chain.doFilter(request, response);
	}

}
