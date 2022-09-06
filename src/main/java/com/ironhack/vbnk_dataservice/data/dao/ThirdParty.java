package com.ironhack.vbnk_dataservice.data.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter @Getter
@NoArgsConstructor
@Entity
public class ThirdParty extends VBUser{
    String accountNumber;
    boolean trusted;
}
