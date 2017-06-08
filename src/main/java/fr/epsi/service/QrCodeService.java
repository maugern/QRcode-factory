package fr.epsi.service;

import fr.epsi.model.QrCode;

public interface QrCodeService {
    QrCode save(QrCode qrcode);
    QrCode findByHashid(String hashid);
}
