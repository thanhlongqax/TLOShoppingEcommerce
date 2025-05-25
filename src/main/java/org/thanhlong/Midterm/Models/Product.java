package org.thanhlong.Midterm.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private long price;
    @Column(name = "picture")
    private String picture;
    @Column(length = 1000)
    private String description;

    public String getBrandName() {
        if (brand != null) {
            return brand.getName();
        } else {
            return null; // or some default value if needed
        }
    }
    @Override
    public String toString(){
        return "id: "+id+", name: "+name+", price: "+price+", image: "+picture;
    }

}
