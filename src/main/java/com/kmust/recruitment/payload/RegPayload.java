package com.kmust.recruitment.payload;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegPayload {
    String username ;
    String password;
    String passworda;
    String phone;
    String code;
}
