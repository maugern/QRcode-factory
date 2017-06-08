package fr.epsi.service;

import fr.epsi.model.QrCode;
import fr.epsi.repository.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Override
    public QrCode save(QrCode qrcode) {
        return qrCodeRepository.save(qrcode);
    }

    @Override
    public QrCode findByHashid(String hashid) {
        return qrCodeRepository.findByHashid(hashid);
    }
}
