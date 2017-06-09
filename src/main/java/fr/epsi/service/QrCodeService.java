package fr.epsi.service;

import fr.epsi.model.QrCode;

/** QRcode service */
public interface QrCodeService {

    /**
     * Save the QRcode
     * @param qrcode QrCode to save
     * @return saved QRcode
     */
    QrCode save(QrCode qrcode);

    /**
     * Find the QRcode
     * @param hashid QrCode's hashid
     * @return QRcode found
     */
    QrCode findByHashid(String hashid);
}
