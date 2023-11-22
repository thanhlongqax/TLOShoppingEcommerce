package org.thanhlong.Midterm.DTO;

import lombok.*;

import java.util.List;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private int price;
    private String description;
    private String picture;
    private String brand;
    private String color;
    private String category; // Thêm một danh sách các ID của các danh mục liên quan


}
