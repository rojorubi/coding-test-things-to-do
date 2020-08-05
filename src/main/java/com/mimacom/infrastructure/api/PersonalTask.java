package com.mimacom.infrastructure.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalTask {

    private Integer id;
    private String typology;
    private String description;
    private String status;

}
