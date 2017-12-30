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

import fr.maugern.model.User;

import java.util.Optional;

/** User DAO interface */
public interface UserDao {

    /**
     * Make user persistent in database
     * @param user the User to make persistent
     * @return Optional User
     */
    Optional<User> save(User user);

    /**
     * Find user with username
     * @param username User's username
     * @return The user
     */
    Optional<User> findByUsername(String username);
}
