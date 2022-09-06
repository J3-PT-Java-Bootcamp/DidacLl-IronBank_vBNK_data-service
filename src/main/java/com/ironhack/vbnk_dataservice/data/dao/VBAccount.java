package com.ironhack.vbnk_dataservice.data.dao;

import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.utils.CryptoConverter;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter @Setter
public abstract class VBAccount {
    @Id
    @GeneratedValue
    UUID id;
    @Convert(converter = MoneyConverter.class)
    Money balance;
    @NotNull
    @Convert(converter = CryptoConverter.class)
    String secretKey;
    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
    @NotNull
    VBUser primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
    @Nullable
    VBUser secondaryOwner;

    @CreationTimestamp
    Instant creationDate;
    @UpdateTimestamp
    Instant updateDate;
    @Enumerated(EnumType.STRING)
    AccountStatus status;




}
