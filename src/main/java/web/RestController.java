package web;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UserDao;

public class RestController {

    private Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired private UserDao userdao;

    @RequestMapping(value = "/api/users/{alias}", produces="application/json")
    @ResponseBody
    public Response getUser(@PathVariable String alias, @RequestHeader("Accept") String acceptHeader) {
        logger.trace("Ressource accept header form : {}", acceptHeader);
        return Response.ok().entity(userdao.getUserByAlias(alias)).build();
    }

}
