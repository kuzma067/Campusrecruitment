package com.kmust.recruitment.payload;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginPayload {
    String username ;
    String password;
}
