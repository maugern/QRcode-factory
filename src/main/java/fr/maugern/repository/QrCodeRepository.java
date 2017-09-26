package fr.maugern.repository;

import fr.maugern.model.QrCode;
import fr.maugern.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/** JPA repository for QRcode */
public interface QrCodeRepository extends JpaRepository<QrCode, Long>{
    
    List<QrCode> findByAuthor(User author);
        
}
