package com.ironhack.vbnk_dataservice.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class VBUserDTO {
    UUID id;
    String name;

}
