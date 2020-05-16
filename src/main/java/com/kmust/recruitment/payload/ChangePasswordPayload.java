package com.kmust.recruitment.payload;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordPayload {
    String username;
    String oldPassword;
    String newPassword;
}
