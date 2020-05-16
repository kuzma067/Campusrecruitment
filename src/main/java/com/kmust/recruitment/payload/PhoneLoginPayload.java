package com.kmust.recruitment.payload;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneLoginPayload {
    String phone;
    String code;
}
