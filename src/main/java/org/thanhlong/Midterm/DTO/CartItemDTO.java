package org.thanhlong.Midterm.DTO;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private String picture;
    private String name;
    private int price;
    private String detailProduct;
    private int quantity;
    private String brand;
    private String color;
}
