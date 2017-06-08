package fr.epsi.web;

import fr.epsi.model.QrCode;
import fr.epsi.service.QrCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QrCodeController {

    private final Logger logger = LoggerFactory.getLogger(QrCodeController.class);

    @Autowired
    private QrCodeService qrCodeService;

    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    public String qrcode(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "qrcode";
    }

    @RequestMapping(value = "/qrcode", method = RequestMethod.POST)
    public String addFile(@ModelAttribute("qrCode") QrCode qrCode, Model model) {
        QrCode qrcode = qrCodeService.save(new QrCode(null, qrCode.getUrl()));
        model.addAttribute("url", qrcode.getUrl());
        model.addAttribute("image", qrcode.getGeneratedImage());
        return "qrcodeShow";
    }

}
