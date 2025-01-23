# StartupBridge Backend

StartupBridge is a robust backend system designed for a platform that connects entrepreneurs with investors. It provides comprehensive API endpoints to manage users, projects, investments, and various other entities essential for the platform's functionality.

## Features

### User Management
- Register and authenticate users.
- Manage user roles and permissions.

### Project Management
- Add, update, and remove projects.
- Link projects with entrepreneurs and investors.

### Investment Management
- Track investment portfolios.
- Assign investments to specific projects.

### Communication Management
- Manage preferences for user communication.
- Support for comments and blog interactions.

### Admin Actions
- Admin-specific functionalities for platform moderation.

## Technology Stack

### Backend
- Java
- Spring Boot
- Hibernate (JPA)
- PostgreSQL

### Utilities
- Maven for dependency management
- MapStruct for object mapping

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher
- Git

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/StartupBridge.git
   cd StartupBridge
   ```

2. Set up the PostgreSQL database and update the `application.properties` file with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/startupbridge
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### Users
- `POST /users` - Create a new user.
- `GET /users/{id}` - Get user details by ID.
- `PUT /users/{id}` - Update user information.
- `DELETE /users/{id}` - Delete a user.

### Projects
- `POST /projects` - Add a new project.
- `GET /projects/{id}` - Get project details.
- `PUT /projects/{id}` - Update a project.
- `DELETE /projects/{id}` - Remove a project.

### Investments
- `POST /investments` - Add a new investment.
- `GET /investments` - Get all investments.
- `GET /investments/{id}` - Get investment details.
- `DELETE /investments/{id}` - Remove an investment.

### Communication
- `POST /comments` - Add a comment.
- `GET /comments/{id}` - Get a comment.
- `DELETE /comments/{id}` - Remove a comment.

## Contribution

Contributions are welcome! Please fork the repository and create a pull request with detailed explanations of your changes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
