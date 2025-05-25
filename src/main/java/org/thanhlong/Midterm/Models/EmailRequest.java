package org.thanhlong.Midterm.Models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class EmailRequest {
    private String to;
    private String subject;
    private String body;
}