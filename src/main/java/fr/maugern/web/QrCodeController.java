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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.maugern.model.QrCode;
import fr.maugern.service.QrCodeService;
import fr.maugern.validator.QrCodeValidator;

/** QRcode controller */
@Controller
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private QrCodeValidator qrCodeValidator;

    /**
     * Get qrcode view
     * @param model to send
     * @return qrcode view
     */
    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    public String qrcode(Model model) {
        model.addAttribute("qrCodeForm", new QrCode());

        return "qrcode";
    }

    /**
     * Posting qrcodeForm
     * @param qrCodeForm qrcode who will be compute
     * @param bindingResult binding result from view
     * @param model model to send
     * @return redirection to qrCodeShow
     */
    @RequestMapping(value = "/qrcode", method = RequestMethod.POST)
    public String qrcode(@ModelAttribute("qrCodeForm") QrCode qrCodeForm, BindingResult bindingResult, Model model) {
        qrCodeValidator.validate(qrCodeForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "qrcode";
        }

        Optional<QrCode> optQrCodeForm = qrCodeService.save(new QrCode(null, qrCodeForm.getUrl()));
        if (optQrCodeForm.isPresent()) {
            model.addAttribute("url", qrCodeForm.getUrl());
            model.addAttribute("image", "data:image/png;base64," + qrCodeForm.getGeneratedImage());
            return "qrcodeShow";
        } else {
            return "500";
        }
    }

}
