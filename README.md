# 🛍️ TLOSHOPPING WEBSITE E-Commerce Platform

🇻🇳 [Tiếng Việt](README_vi.md)

Welcome to **E-Commerce Platform**, an e-commerce system developed with **Spring Boot**, integrating modern technologies to provide a secure and seamless online shopping experience. This is my midterm project, where I have applied my backend programming knowledge to build a complete platform. Let's explore the highlights of this project! 🚀

---

## 🌟 Technologies & Design Principles

### 📌 Design Principles
- **Single Responsibility Principle**: Each class has only one responsibility, making it easier to maintain and extend.
- **Spring MVC Architecture**:
  - **Controller Layer**: Handles user requests and interactions.
  - **Service Layer**: Implements business logic.
  - **Repository Layer**: Communicates with the database, processes queries, and returns necessary data.

### 🔧 Technologies Used
- **Java Spring Boot** (Maven Project) - The core framework of the application.
- **PostgreSQL** - A robust database for storing information.
- **Docker** - Enables quick deployment and efficient database management.
- **Spring Security** - Ensures system security and role-based access, allowing only Admins to manage products and orders.
- **Thymeleaf** - A template engine that smoothly integrates backend and frontend components.

---

## 📂 Project Structure

- **📁 Model**: Stores entities corresponding to database tables.
- **📁 Controller**: Handles user requests and connects with the Service Layer.
- **📁 Service**: Contains business logic methods, interacting with Controllers.
- **📁 Repository**: Manages database queries.
- **📁 Security**: Configures Spring Security, handles authentication and authorization.
- **📁 Configuration**: Contains DataLoader to initialize sample data (Brand, Category, Color, Product).
- **📁 Resources**:
  - **Static**: Holds CSS files and images.
  - **Templates**: Stores HTML files for the user interface.

---

## 🔒 Role-Based Access Management with Spring Security

### 🎩 Admin Privileges
- **Full access to the system.**
- **Product management** (add, edit, delete).
- **Order management** (approve and process customer orders).
- **Manage categories, brands, and product colors.**

### 👤 User Privileges
- **View product listings.**
- **View product details.**
- **Add products to the cart and place orders.**
- **Restricted access to admin pages.**

---

## ⚙️ Installation & Running the Project

### 📥 Clone & Run the Project
```bash
# Clone the repository from GitHub
$ git clone https://github.com/thanhlongqax/Midterm.git

# Open the project in IntelliJ IDEA or VS Code

# Start Docker to run PostgreSQL

# No manual data input needed; the DataLoader in the Configuration folder will auto-generate sample data.

# If there are issues with DataLoader, you can manually import data using the provided SQL file.

# Run the project
$ mvn spring-boot:run

# Access the system at
$ http://localhost:8080/
```

### 🔑 Login Credentials
- **ADMIN Account:**
  - Username: `admin`
  - Password: `12345678`

- **USER Account:**
  - Username: `vovanthanh`
  - Password: `12345678`

> **Note:** Passwords are securely encrypted in the database using Spring Security.

---

## 📡 API Documentation

Below are the key APIs of the system:

### 🔹 Brand API
| Method  | URL          | Description |
|---------|------------|------------------|
| `GET`   | `/api/brands` | Retrieve the list of all brands |

### 🔹 Cart API
| Method  | URL            | Description |
|---------|---------------|----------------------|
| `GET`   | `/api/carts`  | Retrieve cart details |
| `POST`  | `/api/cart/save` | Save cart data to the database |

### 🔹 Category API
| Method  | URL             | Description |
|---------|----------------|----------------------------|
| `GET`   | `/api/categories` | Retrieve the list of product categories |

### 🔹 Product API
| Method  | URL                       | Description |
|---------|---------------------------|-----------------------------|
| `GET`   | `/api/products`           | Retrieve the list of all products |
| `GET`   | `/api/products/{id}`      | Retrieve product details by ID |
| `GET`   | `/api/products/filter`    | Filter products by category, brand, and price |

> **Note:** To test the API, you can disable Spring Security or use JWT authentication.

---

## 🛠️ Testing with JUnit & Mockito

### ✅ BrandApiControllerTest
- Successfully tested **4 test cases** with mock data.

### ✅ ProductControllerTest
- Successfully tested **4 test cases**.

---

## 🏗️ Database Model

### ⚙️ Entity-Relationship Diagram (ERD)
The system is designed with a normalized database model, including tables such as **User, Product, Category, Brand, Order, Cart**. Relationships between tables are visualized in the ERD.

---

## 📚 References
- [Spring Boot Official Docs](https://spring.io/)
- [Spring Security Guide](https://docs.spring.io/spring-security/reference/index.html)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [REST API Design Best Practices](https://www.tutorialspoint.com/restful/index.htm)

---

✨ Thank you for your interest in my project! I hope you have a great experience exploring this e-commerce system. Feel free to reach out if you have any questions! 🚀


