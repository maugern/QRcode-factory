package fr.epsi.repository;

import fr.epsi.model.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrCodeRepository extends JpaRepository<QrCode, Long>{
    QrCode findByHashid(String hashid);
}
