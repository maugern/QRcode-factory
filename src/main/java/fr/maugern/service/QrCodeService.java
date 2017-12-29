package fr.maugern.service;

import fr.maugern.model.QrCode;
import fr.maugern.model.User;

import java.util.List;
import java.util.Optional;

/**
 * QRcode service
 */
public interface QrCodeService {

    /**
     * Save the QRcode
     *
     * @param qrcode QrCode to save
     * @return saved QRcode
     */
    Optional<QrCode> save(QrCode qrcode);

    /**
     * Finding QRcode by his hashid
     *
     * @param hashid The QRcode hashid
     * @return The QRcode associed with hashid
     */
    Optional<QrCode> findById(Long hashid);

    /**
     * Get All QrCode of an user
     *
     * @param user User's QRcode
     * @return All users QRcode
     */
    List<QrCode> findByAuthor(User user);
}
