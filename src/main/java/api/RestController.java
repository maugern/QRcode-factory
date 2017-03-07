package api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UserDao;
import entity.User;

public class RestController {
	
	private Logger logger = LoggerFactory.getLogger(RestController.class);
	
	@Autowired private UserDao userdao;
	
	@RequestMapping(value = "/users/{id}", produces="application/json")
	@ResponseBody
	public User getUser(@PathVariable Long id, @RequestHeader("Accept") String acceptHeader) {
		logger.trace("Ressource accept header form : {}", acceptHeader);
		return userdao.getUserById(id);
	}
	
}
