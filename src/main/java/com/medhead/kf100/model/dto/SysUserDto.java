package com.medhead.kf100.model.dto;

import lombok.Data;

@Data
public class SysUserDto {

    private Integer current = 1;

    private Integer size = 6;

    private String userName;

    private String account;

    private String email;

}
