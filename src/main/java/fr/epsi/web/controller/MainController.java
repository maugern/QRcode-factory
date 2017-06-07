package fr.epsi.web.controller;

import fr.epsi.users.dao.UserDao;
import fr.epsi.users.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserDao userDao;

    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This is default page!");
        model.setViewName("hello");
        return model;
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView registration(@RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "logout", required = false) String logout,
                                     HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("registration");
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public @ResponseBody Integer registration(ModelMap model,
                                              final HttpServletRequest request,
                                              final HttpServletResponse response) throws Exception {
        ModelAndView model = new ModelAndView();
        model.

        User user = new User(username,name,email,password);
        userDao.saveUser(user);
        return 200;
    }

    @RequestMapping(value = "/registration/{username}/{name}/{email}/{password}", method = RequestMethod.POST)
    public @ResponseBody Integer restfullRegistration(@PathVariable final String username,
                                              @PathVariable final String name,
                                              @PathVariable final String email,
                                              @PathVariable final String password,
                                              final HttpServletRequest request,
                                              final HttpServletResponse response) throws Exception {
        User user = new User(username,name,email,password);
        userDao.saveUser(user);
        return 200;
    }

    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public ModelAndView accesssUnauthorized() {
        return new ModelAndView("401");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            logger.info("User detail" + userDetail);
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;
    }

    /**
     * Create personalised error message
     * @param request httpRequest
     * @param key the request attribute
     * @return "Invalid username or password" for login exception, then exception messsage
     */
    private String getErrorMessage(HttpServletRequest request, String key) {
        String error;
        Exception exception = (Exception) request.getSession().getAttribute(key);

        if (exception instanceof BadCredentialsException) {
            error = "Invalid username or password";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else {
            error = "Invalid username or password";
        }

        return error;
    }

}
