package fr.maugern.repository;

import fr.maugern.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

/** JPA repository for QRcode */
public interface QrCodeRepository extends JpaRepository<QrCode, Long>{
}
