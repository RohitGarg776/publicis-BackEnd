# Publicis User API

## Overview
Publicis User API is a Spring Boot application that fetches user data from an external API and stores it in a MySQL database. The application is designed to manage user-related information, including addresses, banks, companies, coordinates, and other user details.

## Features
- Fetches user data from an external API: [dummyjson.com/users](https://dummyjson.com/users)
- Stores data in MySQL database
- Uses Hibernate and JPA for ORM
- Supports CRUD operations on users and related entities
- Logs SQL queries for better debugging
- Uses HikariCP for efficient connection pooling
- Hibernate Search integration with Lucene

## Technologies Used
- Java (Spring Boot)
- Hibernate (JPA)
- MySQL
- Lombok
- HikariCP
- Hibernate Search (Lucene)
- Git for version control

## Database Configuration
The application is configured to connect to a MySQL database. The connection settings are specified in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/publicis_db?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

### Reset Database on Application Start
To delete all existing records and insert fresh data on every application run, modify `ddl-auto` to `create`:

```properties
spring.jpa.hibernate.ddl-auto=create
```
This will drop and recreate all tables each time the application starts.

## API Endpoints
| Method | Endpoint         | Description                |
|--------|----------------|----------------------------|
| GET    | `/users`       | Fetch all users           |
| GET    | `/users/{id}`  | Get user by ID            |
| POST   | `/users`       | Create a new user         |
| PUT    | `/users/{id}`  | Update user by ID         |
| DELETE | `/users/{id}`  | Delete user by ID         |

## Setup & Run
1. **Clone the Repository**
   ```sh
   git clone https://github.com/your-username/publicis-user-api.git
   cd publicis-user-api
   ```
2. **Configure Database**
   - Ensure MySQL is running.
   - Update database credentials in `application.properties`.
3. **Build & Run the Application**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access API**
   - Default URL: `http://localhost:8080`

## Logging Configuration
For enhanced logging of SQL queries:
```properties
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## Git Commands to Push Code
```sh
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/your-username/publicis-user-api.git
git push -u origin main
```

## Contributing
Contributions are welcome! Feel free to submit a pull request or open an issue.

## License
This project is licensed under the MIT License.

