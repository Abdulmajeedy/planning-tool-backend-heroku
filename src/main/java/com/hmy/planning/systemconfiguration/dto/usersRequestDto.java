package com.hmy.planning.systemconfiguration.dto;

import lombok.Data;
import java.time.Year;

@Data
public class usersRequestDto {

    private String role;
    private String description;
    private int status;

}
