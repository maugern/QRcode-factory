package fr.epsi.web;

import fr.epsi.entity.model.QrCode;
import fr.epsi.entity.service.QrCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/red")
public class QrCodeController {

    private final Logger logger = LoggerFactory.getLogger(QrCodeController.class);

    @Autowired
    private QrCodeService qrCodeService;

    @RequestMapping(value = "/{hashid}", method = RequestMethod.GET, produces = "image/jpg")
    public @ResponseBody byte[] getFile(@PathVariable String hashid)  {
        try {
            QrCode qrCode = qrCodeService.findByHash(hashid).get();

            // Create a byte array output stream.
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            // Write to output stream
            ImageIO.write(qrCode.getGeneratedImage(), "jpg", bao);

            return bao.toByteArray();
        } catch (IOException e) {
            logger.error("Error during writing buffered image",e);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.PUT)
    public @ResponseBody String putFile(@PathVariable String url) {
        QrCode qrcode = qrCodeService.save(new QrCode(null, url));
        return qrcode.getHashid();
    }

}
