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
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null) {
			if (request.getRequestURI().contains(WelcomePage)) {
				chain.doFilter(request, response);
			} else if (isBot(request)) {
				backloggerModule.registerBotBacklogger();
				chain.doFilter(request, response);
			} else {
				if (request.getRequestURI().equals("/")) {
					response.sendRedirect(WelcomePage);
				} else {
					response.sendRedirect(userService.createLoginURL(((HttpServletRequest) request).getRequestURI()));
				}
			}
		} else {
			backloggerModule.registerBacklogger();
			chain.doFilter(request, response);
		}
	}

	private boolean isBot(HttpServletRequest request) {
		boolean isBot = false;
		String agent = request.getHeader("User-Agent");
		if (agent == null || agent.isEmpty()) {
			isBot = true;
		} else if (agent.contains("Googlebot")) {
			isBot = true;
		} else if (agent.contains("Mediapartners-Google")) {
			isBot = true;
		} else if (agent.contains("AdsBot-Google")) {
			isBot = true;
		} else if (agent.contains("bingbot")) {
			isBot = true;
		}
		return isBot;
	}
}
