package com.ironhack.vbnk_dataservice.data.dao;

import com.ironhack.vbnk_dataservice.utils.CryptoConverter;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter @Setter
public abstract class Account {
    @Id
    @GeneratedValue
    UUID id;
    @NotNull
    @Convert(converter = CryptoConverter.class)
    String secretKey;




}
