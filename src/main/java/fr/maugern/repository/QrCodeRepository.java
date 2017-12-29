package fr.maugern.repository;

import fr.maugern.model.QrCode;
import fr.maugern.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA repository for QRcode
 */
public interface QrCodeRepository extends JpaRepository<QrCode, Long> {

    /**
     * Return all QrCode for a given user
     *
     * @param user User's QrCode
     * @return All user's QrCode, or an empty list
     */
    List<QrCode> findByAuthor(User user);

}
