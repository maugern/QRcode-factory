/*
 * QrCode-factory, short link generator ditributed by QRcode
 * Copyright (C) 2017 Nicolas Mauger <https://maugern.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
