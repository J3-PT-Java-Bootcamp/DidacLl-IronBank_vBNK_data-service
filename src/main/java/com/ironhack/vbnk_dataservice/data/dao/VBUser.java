package com.ironhack.vbnk_dataservice.data.dao;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class VBUser {

    @Id
    String id;
    @NotNull
    String name;

    @CreationTimestamp
    Instant creationDate;
    @UpdateTimestamp
    Instant updateDate;
}
