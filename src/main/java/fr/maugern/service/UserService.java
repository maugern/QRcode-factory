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

import java.util.Optional;

import fr.maugern.model.User;

/** User service interface */
public interface UserService {

    /**
     * Saving user
     * @param user User to save
     * @return The optionally saved user
     */
    Optional<User> save(User user);

    /**
     * Find User by username
     * @param username User's username
     * @return User with corresponding username
     */
    Optional<User> findByUsername(String username);
}
