package org.thanhlong.Midterm.Models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Set;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "categories")
public class Category implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

}
