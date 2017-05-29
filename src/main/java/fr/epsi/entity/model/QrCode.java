package fr.epsi.entity.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Class used to generate QRcode
 */
public class QrCode implements Serializable {

    // TODO add serial UI

    // TODO add non instenciable entity annotation
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QrCode.class);

    // TODO add id


    public String genarateHashid() {
        // TODO
        // GENERATE RANDOM SECURE
        // ENCODE WITH ID
        // return hashid
        return null;
    }

    public QrCode() {}

    /**
     * Create and save a QRcode in image format.
     * @param data The url you want to convert in QR code
     * @param size in pixel, will be a square 
     * @param imageFormat I recommend to use "png"
     * @param fileName the name of generate image
     */
    public static void generateAndSave(String data, int size, String imageFormat, String fileName) {
        BitMatrix bitMatrix;
        FileOutputStream fileOutputStream;

        try {
            bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
            fileOutputStream = new FileOutputStream(new File(fileName));
            MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
            fileOutputStream.close();
        } catch (WriterException e) {
            logger.error("Fail to encode QRcode",e);
        } catch (IOException e) {
            logger.error("Fail to write " + fileName + ", maybe path not found,"
                    + " already file with the same name or you don't have permission.",e);
        }
    }

}
