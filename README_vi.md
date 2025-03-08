# 🛍️ TLOSHOPPING WEBSITE E-Commerce Platform

Chào mừng bạn đến với **E-Commerce Platform**, một hệ thống thương mại điện tử được phát triển dựa trên **Spring Boot** với các công nghệ tiên tiến, mang đến trải nghiệm mua sắm trực tuyến hiện đại và bảo mật. Đây là bài tập giữa kỳ, nơi mình đã áp dụng những kiến thức về lập trình backend để xây dựng một nền tảng hoàn chỉnh. Hãy cùng khám phá những điểm nổi bật của dự án này! 🚀

---

## 🌟 Công Nghệ & Nguyên Tắc Sử Dụng

### 📌 Nguyên Tắc Thiết Kế
- **Nguyên tắc Single Responsibility**: Mỗi lớp chỉ chịu trách nhiệm cho một nhiệm vụ duy nhất, giúp dễ dàng bảo trì và mở rộng.
- **Kiến trúc Spring MVC**:
  - **Controller Layer**: Tiếp nhận yêu cầu từ người dùng và xử lý tương tác với hệ thống.
  - **Service Layer**: Xử lý logic nghiệp vụ.
  - **Repository Layer**: Giao tiếp với cơ sở dữ liệu, xử lý truy vấn và trả về dữ liệu cần thiết.

### 🔧 Công Nghệ Sử Dụng
- **Java Spring Boot** (Maven Project) - Nền tảng chính của ứng dụng.
- **PostgreSQL** - Cơ sở dữ liệu mạnh mẽ để lưu trữ thông tin.
- **Docker** - Triển khai nhanh chóng và dễ dàng quản lý cơ sở dữ liệu.
- **Spring Security** - Phân quyền và bảo mật hệ thống, đảm bảo chỉ Admin có quyền quản lý sản phẩm và đơn hàng.
- **Thymeleaf** - Engine template giúp kết hợp backend và frontend một cách mượt mà.

---

## 📂 Cấu Trúc Dự Án

- **📁 Model**: Lưu trữ các thực thể (Entity) tương ứng với bảng dữ liệu trong Database.
- **📁 Controller**: Xử lý yêu cầu từ người dùng và liên kết với Service Layer.
- **📁 Service**: Chứa các phương thức thực hiện logic nghiệp vụ, giao tiếp với Controller.
- **📁 Repository**: Giao tiếp với cơ sở dữ liệu, xử lý truy vấn.
- **📁 Security**: Cấu hình Spring Security, phân quyền và quản lý truy cập.
- **📁 Configuration**: Chứa DataLoader để khởi tạo dữ liệu (Brand, Category, Color, Product).
- **📁 Resources**:
  - **Static**: Chứa file CSS, hình ảnh.
  - **Templates**: Chứa các file HTML cho giao diện người dùng.

---

## 🔒 Quản Lý Phân Quyền với Spring Security

### 🎩 Quyền Hạn Của Admin
- **Quyền truy cập toàn bộ hệ thống.**
- **Quản lý sản phẩm** (thêm, sửa, xóa).
- **Quản lý đơn hàng** (duyệt, xử lý đơn đặt hàng của khách hàng).
- **Quản lý danh mục, thương hiệu, màu sắc sản phẩm.**

### 👤 Quyền Hạn Của Người Dùng
- **Xem danh sách sản phẩm**.
- **Xem chi tiết sản phẩm**.
- **Thêm sản phẩm vào giỏ hàng và đặt hàng**.
- **Không được truy cập vào trang quản trị của Admin.**

---

## ⚙️ Hướng Dẫn Cài Đặt & Chạy Dự Án

### 📥 Clone & Chạy Dự Án
```bash
# Clone repository từ GitHub
$ git clone https://github.com/thanhlongqax/Midterm.git

# Mở dự án bằng IntelliJ IDEA hoặc VS Code

# Khởi động Docker để chạy PostgreSQL

# Không cần nhập dữ liệu thủ công, DataLoader trong Configuration sẽ tự động thêm dữ liệu mẫu.

# Nếu gặp lỗi với DataLoader, bạn có thể nhập dữ liệu bằng file SQL đi kèm dự án.

# Chạy dự án
$ mvn spring-boot:run

# Truy cập hệ thống tại
$ http://localhost:8080/
```

### 🔑 Đăng Nhập
- **Tài khoản ADMIN:**
  - Username: `admin`
  - Password: `12345678`

- **Tài khoản USER:**
  - Username: `vovanthanh`
  - Password: `12345678`

> **Lưu ý:** Mật khẩu đã được mã hóa trong database bằng Spring Security.

---

## 📡 API Documentation

Dưới đây là các API chính của hệ thống:

### 🔹 Brand API
| Method  | URL          | Mô tả |
|---------|------------|------------------|
| `GET`   | `/api/brands` | Lấy danh sách tất cả các thương hiệu |

### 🔹 Cart API
| Method  | URL            | Mô tả |
|---------|---------------|----------------------|
| `GET`   | `/api/carts`  | Lấy danh sách giỏ hàng |
| `POST`  | `/api/cart/save` | Lưu dữ liệu giỏ hàng vào database |

### 🔹 Category API
| Method  | URL             | Mô tả |
|---------|----------------|----------------------------|
| `GET`   | `/api/categories` | Lấy danh sách danh mục sản phẩm |

### 🔹 Product API
| Method  | URL                       | Mô tả |
|---------|---------------------------|-----------------------------|
| `GET`   | `/api/products`           | Lấy danh sách tất cả sản phẩm |
| `GET`   | `/api/products/{id}`      | Lấy thông tin sản phẩm theo ID |
| `GET`   | `/api/products/filter`    | Lọc sản phẩm theo danh mục, thương hiệu, giá |

> **Lưu ý:** Để kiểm tra API, bạn có thể tắt Spring Security hoặc sử dụng JWT authentication.

---

## 🛠️ Kiểm Thử Với JUnit & Mockito

### ✅ BrandApiControllerTest
- Kiểm thử thành công **4 test case** với dữ liệu giả lập.

### ✅ ProductControllerTest
- Kiểm thử thành công **4 test case**.

---

## 🏗️ Mô Hình Dữ Liệu

### ⚙️ Sơ Đồ Quan Hệ (ERD)
Hệ thống được thiết kế theo mô hình chuẩn với các bảng như: **User, Product, Category, Brand, Order, Cart**. Các quan hệ giữa bảng được thể hiện qua sơ đồ ERD.

---

## 📚 Tài Liệu Tham Khảo
- [Spring Boot Official Docs](https://spring.io/)
- [Spring Security Guide](https://docs.spring.io/spring-security/reference/index.html)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [REST API Design Best Practices](https://www.tutorialspoint.com/restful/index.htm)

---

✨ Cảm ơn bạn đã quan tâm đến dự án của mình! Hy vọng bạn sẽ có trải nghiệm tuyệt vời khi khám phá hệ thống thương mại điện tử này. Nếu có bất kỳ thắc mắc nào, đừng ngần ngại liên hệ nhé! 🚀
