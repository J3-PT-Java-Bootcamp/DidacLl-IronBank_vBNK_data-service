package com.ironhack.vbnk_dataservice.data;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    String street,city,country, additionalInfo;
    Integer streetNumber,zipCode;

}
