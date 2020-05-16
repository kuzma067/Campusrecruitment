package com.kmust.recruitment.payload;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissPasswordPayload {
    String phone;
    String password;
    String passworda;
    String code;
}
