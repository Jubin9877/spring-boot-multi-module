package io.manco.maxim.sbmm.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		logger.info("Authentication request came.");
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			if (StringUtils.hasText(targetUrl)) {
				model.addObject("targetUrl", targetUrl);
				model.addObject("loginUpdate", true);
			}
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
		return model;
	}

	private String getRememberMeTargetUrlFromSession(HttpServletRequest request) {
		String targetUrl = "/";
		HttpSession session = request.getSession(false);
		if (session != null) {
			targetUrl = session.getAttribute("targetUrl") == null ? targetUrl
					: session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}
	
	 @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
	  public ModelAndView defaultPage() {
	    ModelAndView model = new ModelAndView();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth.getPrincipal().equals("anonymousUser")) {
	      model.setViewName("login");
	    } else {
	      model.setViewName("redirect:/signed/list-accounts");
	    }
	    return model;
	  }

//	  @RequestMapping(value = {"/signed/search-auth"}, method = RequestMethod.GET)
//	  public String searchPage1() {
//	    return "search-auth";
//	  }

//	  @RequestMapping(value = "/signed**", method = RequestMethod.GET)
//	  public ModelAndView adminPage() {
//	    ModelAndView model = new ModelAndView();
//	    model.addObject("title", "Spring Security Remember Me");
//	    model.addObject("message", "This page is for ROLE_ADMIN only!");
//	    model.setViewName("signed");
//	    return model;
//	  }

	  @RequestMapping(value = "/logout**", method = RequestMethod.GET)
	  public String logOut() {
	    return "redirect:login";
	  }

	  @GetMapping(value = "/ajax-stock")
	  public String ajaxStock() {
	    return "ajax-stock";
	  }

	  @RequestMapping(value = "/signed/update**", method = RequestMethod.GET)
	  public ModelAndView updatePage(HttpServletRequest request) {
	    ModelAndView model = new ModelAndView();
	    if (isRememberMeAuthenticated()) {
	      //send login for update
	      setRememberMeTargetUrlToSession(request);
	      model.addObject("loginUpdate", true);
	      model.setViewName("/login");
	    } else {
	      model.setViewName("update");
	    }
	    return model;
	  }

	  private boolean isRememberMeAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authentication != null && RememberMeAuthenticationToken.
	      class.isAssignableFrom(authentication.getClass());
	  }

	  private void setRememberMeTargetUrlToSession(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	      session.setAttribute("targetUrl", "/signed/update");
	    }
	  }

}
