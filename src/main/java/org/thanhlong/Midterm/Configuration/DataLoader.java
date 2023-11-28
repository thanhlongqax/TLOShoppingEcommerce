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
                    user.setUserName("vovanthanh");
                    user.setPassword(passwordEncoder.encode("12345678"));
                    user.setName("Võ Văn Thành");
                    user.setEmail("vovanthanh@gmail.com");
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
                Brand nike = new Brand();
                nike.setId(1L);
                nike.setName("Nike");
                brandRepository.save(nike);

                Brand levi = new Brand();
                levi.setId(2L);
                levi.setName("Levi");
                brandRepository.save(levi);

                Brand uniqulo = new Brand();
                uniqulo.setId(3L);
                uniqulo.setName("UNIQULO");
                brandRepository.save(uniqulo);


                Color blue = new Color();
                blue.setId(1L);
                blue.setName("Xanh Dưong");
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

                Category ao = new Category();
                ao.setId(1L);
                ao.setName("Áo");
                categoryRepository.save(ao);

                Category dam = new Category();
                dam.setId(2L);
                dam.setName("Đầm");
                categoryRepository.save(dam);

// Thêm dữ liệu vào bảng products
                productRepository.save(new Product(1L, "Áo Thun Caro", blue, nike, ao, 100, "Ao_ThunCaro.jpg", "🌟 Áo Caro - Khi Phong Cách Gặp Độ Linh Hoạt 🌟..."));
                productRepository.save(new Product(2L, "Áo Jean", yellow, levi, ao, 100, "Ao_Jean.jpg", "🌟 Áo Jean Nữ không chỉ là sự kết hợp hoàn hảo giữa phong cách và thoải mái..."));
                productRepository.save(new Product(3L, "Áo Sọc Caro", yellow, levi, ao, 100, "Ao_Soccaro.jpg", "🌟 Bộ Sưu Tập Áo Sơ Mi Sọc Caro của chúng tôi. Những chiếc áo sơ mi này không chỉ mang lại vẻ ngoài phóng khoáng mà còn là biểu tượng của sự tinh tế và trẻ trung."));
                productRepository.save(new Product(4L, "Áo Sơ Mi", black, uniqulo, ao, 150, "Ao_Somi.jpg", "🌟 Chào mừng bạn đến với thế giới của chúng tôi, nơi áo sơ mi không chỉ là một trang phục, mà là biểu tượng của sự quý phái và phong cách. Bộ Sưu Tập Áo Sơ Mi Nữ của chúng tôi sẽ đưa bạn vào một hành trình thời trang đầy ấn tượng."));
                productRepository.save(new Product(5L, "Áo Thun Nữ", gray, levi, ao, 100, "Ao_Thun_Nu.jpg", "🌟 áo thun của chúng tôi được làm từ chất liệu thoáng khí, mềm mại và thoải mái, giúp bạn tự tin và thoải mái mỗi khi mặc Với nhiều kiểu dáng từ basic đến trendy, chúng tôi tự hào mang đến sự đa dạng để bạn có thể tìm thấy chiếc áo thun phản ánh phong cách cá nhân của mình."));
                productRepository.save(new Product(6L, "Đầm Ôm Dáng", pink, uniqulo, dam, 120, "Dam_Om.jpg", "🌟 Đầm Ôm được thiết kế để ôm sát vóc dáng, tôn lên những đường cong quyến rũ của bạn. Kiểu dáng này không chỉ là sự kết hợp hoàn hảo giữa thoải mái và gợi cảm mà còn là biểu tượng của sự tự tin."));
                productRepository.save(new Product(7L, "Đầm Vàng", yellow, levi, dam, 130, "Dam_Vang.jpg", "🌟 Đầm Vàng Tươi Sáng không chỉ là một chiếc đầm, mà là sự kết hợp hài hòa giữa màu sắc năng động và kiểu dáng thanh lịch. Màu vàng tươi sáng mang lại năng lượng tích cực và làm nổi bật vẻ trẻ trung của bạn."));
                productRepository.save(new Product(8L, "Đầm Xanh", blue, uniqulo, dam, 140, "Dam_Xanh.jpg", "🌟 Được tạo nên từ chất liệu nhẹ nhàng"));

                dataLoaded = true; // Set the flag to true after data loading

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

