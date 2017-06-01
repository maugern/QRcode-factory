-- DELETE user with non-correct format email
DELETE  FROM users
        WHERE email NOT LIKE '_%@_%._%'
;

-- DELETE comment with not exist author
DELETE  FROM guestbook
        WHERE author NOT IN (
            SELECT id
            FROM users
        )
;
