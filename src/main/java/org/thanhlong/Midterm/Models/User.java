package org.thanhlong.Midterm.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;

@Entity
@Table( name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String email;
    private String userName;
    private String phoneNumber;

    @Column(nullable = false)
    @NotEmpty
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn (name = "ROLE_ID", referencedColumnName = "ID")}
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL)
    private List<Order> orders;
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + "]";
    }

}
