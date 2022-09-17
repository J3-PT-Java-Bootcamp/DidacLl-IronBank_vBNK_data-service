package com.ironhack.vbnk_dataservice.data.http.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class NewAdminRequest {
    @NotNull
    private String id;
    @NotNull
    private String userName,email,firstname,lastname;

    

}
