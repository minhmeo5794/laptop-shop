# Laptop_shop

Laptop_shop is a backend web application built with Spring Boot
that allows users to browse products, place orders, and manage accounts.
The system supports authentication, authorization, and role-based access control (User/Admin).

---

## Tech Stack

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- MySQL
- Maven
- Git

---

## Features

### Authentication & Authorization
- User registration
- User login/logout
- Role-based access control (USER/ADMIN)
- Password encryption using Spring Security

### Product Management
- View product list
- View product details
- Admin can create/update/delete products

### Order Management
- Add products to cart
- Place orders
- View order history
- Admin can manage all orders

### User Management
- View user list
- Manage roles

---

## Architecture

The project follows MVC pattern and 3-layer architecture:

- **Controller Layer**: Handles HTTP requests and returns views
- **Service Layer**: Contains business logic
- **Repository Layer**: Interacts with database using JPA
- **Security Layer**: Handles authentication and authorization

---

## Database Design

Main tables:

- users
- roles
- products
- orders
- order_details

Relationships:
- One user can have many orders
- One order contains multiple order details
- Each order detail references a product

---

## How to Run

1. Clone the repository
2. Create MySQL database:
   ```
   CREATE DATABASE laptop_shop;
   ```
3. Update `application.properties` with your database credentials
4. Run the project:
   ```
   mvn spring-boot:run
   ```
5. Access at:
   ```
   http://localhost:8080
   ```

---