package org.thanhlong.Midterm.Configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.thanhlong.Midterm.Models.*;
import org.thanhlong.Midterm.Repository.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    private static boolean dataLoaded = false;
    @PostConstruct
    public void loadData(){

        if (!dataLoaded) {
            try {
                Role admin1 = new Role();
                admin1.setId(1L);
                admin1.setName("ROLE_ADMIN");

                Role user1 = new Role();
                user1.setId(2L);
                user1.setName("ROLE_USER");

                roleRepository.save(admin1);
                roleRepository.save(user1);

                //tao 1 list chua 2 role
                List<Role> admin2 = new ArrayList<>();
                admin2.add(user1);
                admin2.add(admin1);

                //tao list chua 1 role
                List<Role> user2 = new ArrayList<>();
                user2.add(user1);
                // Thêm dữ liệu mẫu khi ứng dụng khởi động
                if (userRepository.count() == 0) {
                    User user = new User();
                    user.setUserName("phongdeptrai");
                    user.setPassword(passwordEncoder.encode("12345678"));
                    user.setName("Phong Đẹp Trai");
                    user.setEmail("nguyenthanhphong@gmail.com");
                    user.setAddress("Đại Học Tôn Đức Thắng");
                    user.setPhoneNumber("0123456789");
                    user.setRoles(user2);

                    User admin = new User();
                    admin.setUserName("admin");
                    admin.setPassword(passwordEncoder.encode("12345678"));
                    admin.setName("Nguyễn Lâm Thành Long");
                    admin.setPhoneNumber("0817757012");
                    admin.setEmail("thanhlongqax@gmail.com");
                    admin.setAddress("ktx tòa I TDTU");
                    admin.setRoles(admin2);

                    userRepository.save(admin);
                    userRepository.save(user);
                }
                Brand iphone = new Brand();
                iphone.setId(1L);
                iphone.setName("Iphone");
                brandRepository.save(iphone);

                Brand samsung = new Brand();
                samsung.setId(2L);
                samsung.setName("Samsung");
                brandRepository.save(samsung);

                Brand xiaomi = new Brand();
                xiaomi.setId(3L);
                xiaomi.setName("Xiaomi");
                brandRepository.save(xiaomi);

                Brand oppo = new Brand();
                oppo.setId(4L);
                oppo.setName("Oppo");
                brandRepository.save(oppo);

                Color blue = new Color();
                blue.setId(1L);
                blue.setName("Xanh Dương");
                colorRepository.save(blue);

                Color red = new Color();
                red.setId(2L);
                red.setName("Đỏ");
                colorRepository.save(red);

                Color yellow = new Color();
                yellow.setId(3L);
                yellow.setName("Vàng");
                colorRepository.save(yellow);

                Color black = new Color();
                black.setId(4L);
                black.setName("Đen");
                colorRepository.save(black);

                Color gray = new Color();
                gray.setId(5L);
                gray.setName("Xám");
                colorRepository.save(gray);

                Color pink = new Color();
                pink.setId(6L);
                pink.setName("Hồng");
                colorRepository.save(pink);

                Category smartPhone = new Category();
                smartPhone.setId(1L);
                smartPhone.setName("Điện Thoại");
                categoryRepository.save(smartPhone);

                Category ipad = new Category();
                ipad.setId(2L);
                ipad.setName("Ipad");
                categoryRepository.save(ipad);

// Thêm dữ liệu vào bảng products
                productRepository.save(new Product(1L, "Iphone 15 Promax", black, iphone, smartPhone, 100, "iphone-15-pro-max-black-2-1.jpg", "🌟 Đặc điểm nổi bật của iPhone 15 Pro Max\n" +
                        "• Tăng độ cứng cáp và tối ưu khối lượng với chất liệu Titan\n" +
                        "• Bứt phá mọi giới hạn về hiệu năng nhờ chip A17 Pro\n" +
                        "• Phiên bản duy nhất cải tiến camera tele 5x\n" +
                        "• Tích hợp camera 48 MP\n" +
                        "• Nút tác vụ (Action Button) thay thế cần gạt rung\n" +
                        "• Chuyển đổi cổng sạc USB-C, truyền tải dữ liệu tốc độ cao 🌟"));
                productRepository.save(new Product(2L, "Iphone 14 plus xanh", blue, iphone,smartPhone, 100, "iphone-14-plus-xanh-1.jpg", "🌟 Sau nhiều thế hệ điện thoại của Apple thì cái tên “Plus” cũng đã chính thức trở lại vào năm 2022 và xuất hiện trên chiếc iPhone 14 Plus 128GB, nổi trội với ngoại hình bắt trend cùng màn hình kích thước lớn để đem đến không gian hiển thị tốt hơn cùng cấu hình mạnh mẽ không đổi so với bản tiêu chuẩn."));
                productRepository.save(new Product(3L, "Oppo A38", yellow, oppo, smartPhone, 100, "oppo-a38-vang-1-2.jpg", "🌟 OPPO A38 mẫu điện thoại tầm trung mới nhất được OPPO mang đến cho người tiêu dùng vào nửa cuối năm 2023. Máy sở hữu lối thiết kế quen thuộc của dòng điện thoại OPPO A, đồng thời có một hiệu năng ổn định cùng màn hình hiển thị sắc nét chắc chắn sẽ không làm bạn thất vọng."));
                productRepository.save(new Product(4L, "Samsung Galaxy A04s", black, samsung, smartPhone, 150, "samsung-galaxy-a04s-1.jpg", "🌟 Chào mừng bạn đến với thế giới của chúng tôi, nơi áo sơ mi không chỉ là một trang phục, mà là biểu tượng của sự quý phái và phong cách. Bộ Sưu Tập Áo Sơ Mi Nữ của chúng tôi sẽ đưa bạn vào một hành trình thời trang đầy ấn tượng."));
                productRepository.save(new Product(5L, "Samsung Galaxy A14", gray, samsung,smartPhone, 100, "samsung-galaxy-a14-5g-nau-1-1.jpg", "🌟 Samsung Galaxy A14 5G có thể là chiếc smartphone đầu tiên của năm 2023 được nhà sản xuất đến từ Hàn Quốc phát hành chính thức ở Việt Nam. Máy nổi bật với lối thiết kế trẻ trung, màn hình kích thước lớn và camera chính có độ phân giải lên tới 50 MP."));
                productRepository.save(new Product(6L, "Samsung Galaxy S21", black, samsung, smartPhone, 120, "samsung-galaxy-s21-fe-1.jpg", "🌟 Samsung Galaxy S21 FE 5G (6GB/128GB) được Samsung ra mắt với dáng dấp thời thượng, cấu hình khỏe, chụp ảnh đẹp với bộ 3 camera chất lượng, thời lượng pin đủ dùng hằng ngày và còn gì nữa? Mời bạn khám phá qua nội dung sau ngay."));
                productRepository.save(new Product(7L, "Samsung Galaxy S23", yellow, samsung, smartPhone, 130, "samsung-galaxy-s23-ultra-xanh-1.jpg", "🌟 Samsung Galaxy S23 Ultra 5G 256GB là chiếc smartphone cao cấp nhất của nhà Samsung, sở hữu cấu hình không tưởng với con chip khủng được Qualcomm tối ưu riêng cho dòng Galaxy và camera lên đến 200 MP, xứng danh là chiếc flagship Android được mong đợi nhất trong năm 2023."));
                productRepository.save(new Product(8L, "Samsung Galaxy ZFold5", gray, samsung, smartPhone, 140, "samsung-galaxy-zfold5-kem-256gb-1-1.jpg", "🌟Samsung Galaxy Z Fold5 là mẫu điện thoại cao cấp được ra mắt vào tháng 07/2023 với nhiều điểm đáng chú ý như thiết kế gập độc đáo, hiệu năng mạnh mẽ nhờ chip Snapdragon 8 Gen 2 for Galaxy cùng camera quay chụp sắc nét."));
                productRepository.save(new Product(8L, "Xiaomi Redmi 12", blue, xiaomi, smartPhone, 140, "xiaomi-redmi-12-xanh-1.jpg", "🌟Xiaomi Redmi 12 mẫu điện thoại mới nhất được nhà Xiaomi tung ra vào tháng 06/2023 khiến cho cộng đồng đam mê công nghệ vô cùng thích thú. Máy khoác lên mình một vẻ ngoài cá tính, màn hình lớn đem đến trải nghiệm đã mắt cùng một hiệu năng ổn định với mọi tác vụ."));
                dataLoaded = true; // Set the flag to true after data loading

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

