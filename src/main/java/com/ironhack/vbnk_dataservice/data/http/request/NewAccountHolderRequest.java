package com.ironhack.vbnk_dataservice.data.http.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class NewAccountHolderRequest {
    String name;
    LocalDate dateOfBirth;
    String mainStreet, mainCity, mainCountry, mainAdditionalInfo;
    String mailStreet, mailCity, mailCountry, mailAdditionalInfo;
    Integer mainStreetNumber, mainZipCode;
    Integer mailStreetNumber, mailZipCode;
}
