package fr.maugern.web;

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

        qrCodeForm = qrCodeService.save(new QrCode(null, qrCodeForm.getUrl()));
        model.addAttribute("url", qrCodeForm.getUrl());
        model.addAttribute("image", "data:image/png;base64," + qrCodeForm.getGeneratedImage());
        return "qrcodeShow";
    }

}
