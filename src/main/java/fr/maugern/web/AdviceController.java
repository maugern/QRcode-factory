/*
 * QrCode-factory, short link generator ditributed by QRcode
 * Copyright (C) 2017 Nicolas Mauger <https://maugern.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
