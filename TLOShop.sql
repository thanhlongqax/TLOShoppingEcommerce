-- INSERT vào bảng product
INSERT INTO products (name, picture, description , price , brand_id, color_id , category_id)
VALUES 
    (N'Áo Thun Caro', 'Ao_ThunCaro.jpg', N'🌟 Áo Caro - Khi Phong Cách Gặp Độ Linh Hoạt 🌟Khám phá thế giới của sự phối hợp tinh tế và phóng khoáng với bộ sưu tập áo caro mới nhất của chúng tôi! 🎨 🔍 Họa Tiết Caro Độc Đáo: Mỗi chiếc áo đều là một tác phẩm nghệ thuật riêng' , 100,1, 1 ,1),
    (N'Áo Jean', 'Ao_Jean.jpg', N'🌟 Áo Jean Nữ không chỉ là sự kết hợp hoàn hảo giữa phong cách và thoải mái, mà còn là biểu tượng của sự tự tin và tinh tế Với nhiều kiểu dáng từ áo jean dài truyền thống đến áo jean cắt ngắn, từ áo jean slim-fit đến boyfriend-fit, chúng tôi mang đến nhiều sự lựa chọn để bạn có thể thể hiện phong cách cá nhân.' , 100,1, 1 ,1),
    (N'Áo Sọc Caro', 'Ao_Soccaro.jpg', N'🌟 Bộ Sưu Tập Áo Sơ Mi Sọc Caro của chúng tôi. Những chiếc áo sơ mi này không chỉ mang lại vẻ ngoài phóng khoáng mà còn là biểu tượng của sự tinh tế và trẻ trung.' , 100,1, 1 ,1),
    (N'Áo Sơ Mi', 'Ao_Somi.jpg', N'🌟 Chào mừng bạn đến với thế giới của chúng tôi, nơi áo sơ mi không chỉ là một trang phục, mà là biểu tượng của sự quý phái và phong cách. Bộ Sưu Tập Áo Sơ Mi Nữ của chúng tôi sẽ đưa bạn vào một hành trình thời trang đầy ấn tượng.,3, 1,1 ),
    (N'Áo Thun Nữ', 'Ao_Thun_Nu.jpg', N'🌟 áo thun của chúng tôi được làm từ chất liệu thoáng khí, mềm mại và thoải mái, giúp bạn tự tin và thoải mái mỗi khi mặc Với nhiều kiểu dáng từ basic đến trendy, chúng tôi tự hào mang đến sự đa dạng để bạn có thể tìm thấy chiếc áo thun phản ánh phong cách cá nhân của mình.' , 100,3, 1,1 ),
    (N'Đầm Ôm Dáng', 'Dam_Om.jpg', N'🌟 Đầm Ôm được thiết kế để ôm sát vóc dáng, tôn lên những đường cong quyến rũ của bạn. Kiểu dáng này không chỉ là sự kết hợp hoàn hảo giữa thoải mái và gợi cảm mà còn là biểu tượng của sự tự tin.' , 100,3, 1 ,2),
    (N'Đầm Vàng', 'Dam_Vang.jpg', N'🌟 Đầm Vàng Tươi Sáng không chỉ là một chiếc đầm, mà là sự kết hợp hài hòa giữa màu sắc năng động và kiểu dáng thanh lịch. Màu vàng tươi sáng mang lại năng lượng tích cực và làm nổi bật vẻ trẻ trung của bạn.' , 100,3, 1 ,2),
    (N'Đầm Xanh', 'Dam_Xanh.jpg', N'🌟 Được tạo nên từ chất liệu nhẹ nhàng và thoáng khí, chiếc đầm xanh này không chỉ mang lại sự thoải mái mà còn tỏa ra vẻ tươi mới và trẻ trung. Màu xanh dịu dàng tôn lên nét đẹp tự nhiên của bạn, làm nổi bật phong cách và cá tính riêng biệt.' , 150,3, 1 ,2),
    (N'Đầm Đen Dạ Hội', 'Dam_Den.jpg', N'🌟 Elegant và sang trọng, đầm đen dạ hội là sự lựa chọn tuyệt vời cho những dịp quan trọng và đêm tiệc trọng đại. Thiết kế này nổi bật với đường cắt tỉa tinh tế và chất liệu cao cấp, tạo nên vẻ đẹp lôi cuốn và quý phái.' , 200,3, 4 ,2)
    
insert into brands(name)  
values
	(N'Nike'),
	(N'Levi'),
	(N'UNIQULO')
	
insert into categories (name)
values
	(N'Áo'),
	(N'Đầm')
	
insert into colors (name)
values
	(N'Xanh Dưong'),
	(N'Đỏ'),
	(N'Vàng'),
	(N'Đen'),
	(N'Xám'),
	(N'Hồng'),
	(N'Xám'),
	