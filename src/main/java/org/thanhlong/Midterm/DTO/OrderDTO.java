package org.thanhlong.Midterm.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class OrderDTO {
    private long id;
    private String FullName;
    private LocalDateTime OrderCreated;
    private String address;
    private int Total;
    private String Order_status;
    private String phoneNumber;
    private Long userID;
    private String email;
    private int quantity;
}
