package com.ironhack.vbnk_dataservice.data.http.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class NewAccountHolderRequest {
    private String id;
    private String username,email,firstname,lastname;
    private LocalDate dateOfBirth;
    private String mainStreet, mainCity, mainCountry, mainAdditionalInfo;
    private String mailStreet, mailCity, mailCountry, mailAdditionalInfo;
    private Integer mainStreetNumber, mainZipCode;
    private Integer mailStreetNumber, mailZipCode;
}
