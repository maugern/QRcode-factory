package web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UserDao;
import entity.User;

@Controller
public class UserController {

    @Autowired private UserDao userdao;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public void login(@RequestParam("alias") String alias,
            @RequestParam("password") String password,
            @RequestHeader("Accept") String acceptHeader,
            HttpSession session) {

        User user = userdao.getUserByAlias(alias);

        if (user.isGoodPassword(password)) {
            session.setAttribute("name", user.getName());
        } else {
            session.setAttribute("name", "unknow");
        }
    }

}
