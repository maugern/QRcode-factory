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
