package fr.maugern.data;

import fr.maugern.model.QrCode;
import fr.maugern.model.User;

import java.util.List;
import java.util.Optional;

/**
 * QRcode DAO interface
 */
public interface QrCodeDao {

    /**
     * Make qrCode persistent in database
     *
     * @param qrCode QrCode to save
     * @return an Optional QrCode
     */
    Optional<QrCode> save(QrCode qrCode);

    /**
     * Find qrCode by id
     *
     * @param hashid User's id
     * @return an optional qrCode
     */
    Optional<QrCode> findById(Long hashid);

    /**
     * Find qrCode by id
     *
     * @param user who was to get his QrCodes
     * @return List of user's QrCodes
     */
    List<QrCode> findByAuthor(User user);
}
