package webplus.ezbacklog.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import webplus.ezbacklog.module.interfaces.BackloggerModule;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private BackloggerModule backloggerModule;

	private static final String WelcomePage = "/f/about";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			UserService userService = UserServiceFactory.getUserService();
			User user = userService.getCurrentUser();
			if (user == null) {
				if (request.getRequestURI().contains(WelcomePage)) {
					chain.doFilter(request, response);
				} else {
					((HttpServletResponse) response).sendRedirect(userService
							.createLoginURL(((HttpServletRequest) request).getRequestURI()));
					return;
				}
			} else {
				backloggerModule.registerBacklogger();
			}
		}
		chain.doFilter(request, response);
	}

}
