package com.yakauleu.ibank.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CurrentUserDto {
    private Long id;
    private String login;
    private String name;
    private String role;
}
