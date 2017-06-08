package fr.epsi.model;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;

/**
 * Class used to generate QRcode
 */
@Entity
@Table(name = "qrcode")
public class QrCode implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(QrCode.class);

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qrcode_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = true)
    private User author;

    @Size(max = 4000)
    @Column(name = "url")
    private String url;

    @Column(name = "hashid")
    private String hashid;

    @Column(name = "salt")
    private String salt;

       /** Default constructor */
    public QrCode() {}

    /**
     * QrCode contructor. Will generate hashid and is salt.
     * @param author Author of the QRcode
     * @param url Url of the redirection
     */
    public QrCode(User author, String url) {
        this.author = author;
        this.url = url;
    }

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

    public BufferedImage getGeneratedImage() {
        BitMatrix bitMatrix;
        BufferedImage bufferedImage = null;

        try {
            bitMatrix = new QRCodeWriter().encode(url,BarcodeFormat.QR_CODE,400,400);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e1) {
            logger.error("Fail to encode QRcode, maybe URL is in bad format",e1);
        }
        return bufferedImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHashid() {
        if (hashid == null || hashid.isEmpty())
            genarateHashid();
        return hashid;
    }

    public void setHashid(String hashid) {
        this.hashid = hashid;
    }

    public String getSalt() {
        if (salt == null || salt.isEmpty()) {
            generateSalt();
        }
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Generate Hashid with qrcode id's and generated salt
     */
    private void genarateHashid() {
        Hashids hashid = new Hashids(getSalt());
        this.hashid = hashid.encode(id);
    }

    private void generateSalt() {
        SecureRandom random = new SecureRandom();
        Hasher hasher = Hashing.md5().newHasher();
        hasher.putLong(random.nextLong());
        this.salt = hasher.hash().toString();
    }

}
