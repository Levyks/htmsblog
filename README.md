# HTMS Blog

Blog made using the HTMS stack (HTMX, Thymeleaf, MySQL, Spring), used as learning project for the TJW (Java Topics for
the Web) class in the Computer Science course of IFCE.

## Requirements

- Java 21
- MariaDB/MySQL database
    - Tested with MariaDB 11.1.2 and 11.2.2
- Environment variables

  | Variable      | Description       | Default                                   |
        |---------------|-------------------|-------------------------------------------|
  | `DB_URL`      | Database URL      | `jdbc:mariadb://localhost:3306/htms_blog` |
  | `DB_USERNAME` | Database username | `root`                                    |
  | `DB_PASSWORD` | Database password | `password`                                |

Server will listen on port 8080 by default, this can be overridden by setting the `SERVER_PORT` environment variable.