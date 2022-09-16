package com.ironhack.vbnk_dataservice.data.dao.accounts;

import com.ironhack.vbnk_dataservice.data.AccountStatus;
import com.ironhack.vbnk_dataservice.data.Money;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.utils.CryptoConverter;
import com.ironhack.vbnk_dataservice.utils.MoneyConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Getter
@Setter
public abstract class VBAccount {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
//    @Type(type = "uuid-char")
    private  String id;
    private  String accountNumber;
    @Convert(converter = MoneyConverter.class)
    private  Money balance;

    //    @NotNull
    @Convert(converter = CryptoConverter.class)
    private  String secretKey;

    @ManyToOne
    @JoinColumn(name = "primary_owner_id")
//    @NotNull
    private  AccountHolder primaryOwner;

    @ManyToOne
    @JoinColumn(name = "secondary_owner_id")
//    @Nullable
    AccountHolder secondaryOwner;

    @CreationTimestamp
    Instant creationDate;

    @UpdateTimestamp
    Instant updateDate;

    @Enumerated(EnumType.STRING)
    AccountStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    VBAdmin administratedBy;
}
