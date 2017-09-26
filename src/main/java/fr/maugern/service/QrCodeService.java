package fr.maugern.service;

import java.util.Optional;

import fr.maugern.model.QrCode;

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
     * @param hashid QrCode's Id
     * @return QRcode found
     */
    Optional<QrCode> findById(Long id);
}
