package org.thanhlong.Midterm.DTO;

import lombok.Data;

import java.util.List;

@Data
//@Setter @Getter // thêm
public class UserDTO {
    private Long id;

    private String email;

    private String password;

    private List<Integer> roleIds;

    private String name;

    private String address;

    private String phoneNumber;

}
