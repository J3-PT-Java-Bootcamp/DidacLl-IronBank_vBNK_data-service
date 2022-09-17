package com.ironhack.vbnk_dataservice.data.dto.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class VBUserDTO {
    private String id;

    private String username,firstName,lastName;
}
