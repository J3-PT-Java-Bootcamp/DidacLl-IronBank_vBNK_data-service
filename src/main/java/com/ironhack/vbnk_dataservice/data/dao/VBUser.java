package com.ironhack.vbnk_dataservice.data.dao;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class VBUser {

    @Id
    @GeneratedValue
    UUID id;
    @NotNull
    String name;
}
