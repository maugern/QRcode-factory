package fr.maugern.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.NoHandlerFoundException;

/** Advice controller, useful to handle error */
@Controller
public class AdviceController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(Exception ex) {
        return "redirect:/404";
    }

    @ExceptionHandler(ModelAndViewDefiningException.class)
    public String handleInternalError(Exception ex) {
        return "redirect:/500";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFoundPage() {
        return "404";
    }
    
    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String internalError() {
        return "500";
    }

}
