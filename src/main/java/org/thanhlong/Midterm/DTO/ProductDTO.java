package org.thanhlong.Midterm.DTO;

import lombok.*;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private long price;
    private String description;
    private String picture;
    private String brand;
    private String color;
    private String category;


}
