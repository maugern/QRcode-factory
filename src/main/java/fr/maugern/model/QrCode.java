package fr.maugern.model;

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

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

/** QrCode Entity model */
@Entity
@Table(name = "qrcode")
public class QrCode {

    private static final Logger logger = LoggerFactory.getLogger(QrCode.class);

    private Long id;
    private User author;
    private String url;
    private String hashid;
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
     * Generate image in base64
     * @return String who representing Qrcode in base64
     */
    @Transient
    public String getGeneratedImage() {
        BitMatrix bitMatrix;
        BufferedImage bufferedImage = null;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            bitMatrix = new QRCodeWriter().encode(url,BarcodeFormat.QR_CODE,400,400);
            bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(bufferedImage, "png", bao);
        } catch (WriterException e) {
            logger.error("Fail to encode QRcode, maybe URL is in bad format",e);
        } catch (IOException e) {
            logger.error("Fail to write bitmatrix in buffer",e);
        }
        return Base64.getEncoder().encodeToString(bao.toByteArray());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if(this.id == null)
            genarateHashid();
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = true)
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
        if (salt == null || salt.isEmpty())
            generateSalt();
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Generate Hashid with qrcode id's and generated salt
     */
    private void genarateHashid() {
        if(id != null) {
            Hashids h = new Hashids(getSalt());
            this.hashid = h.encode(id);
        } else {
            Hashids h = new Hashids(getSalt());
            this.hashid = h.encode(37L);
        }
    }

    private void generateSalt() {
        SecureRandom random = new SecureRandom();
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putLong(random.nextLong());
        this.salt = hasher.hash().toString();
    }

}
