-- DELETE user with non-correct format email
DELETE  FROM users
        WHERE email NOT LIKE '%@%.%'
;

