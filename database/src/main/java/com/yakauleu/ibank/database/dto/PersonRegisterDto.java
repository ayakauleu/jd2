package com.yakauleu.ibank.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PersonRegisterDto {
    private String name;
    private String loginName;
    private String phone;
    private String password;
}
