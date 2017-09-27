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
    Optional<QrCode> save(QrCode qrcode);

    /**
     * @param idFromHashid
     * @return
     */
    Optional<QrCode> findById(Long hashid);
}
