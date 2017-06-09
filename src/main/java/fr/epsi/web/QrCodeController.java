package fr.epsi.web;

import fr.epsi.model.QrCode;
import fr.epsi.service.QrCodeService;
import fr.epsi.validator.QrCodeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QrCodeController {

    private final Logger logger = LoggerFactory.getLogger(QrCodeController.class);

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private QrCodeValidator qrCodeValidator;

    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    public String qrcode(Model model) {
        model.addAttribute("qrCodeForm", new QrCode());

        return "qrcode";
    }

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
