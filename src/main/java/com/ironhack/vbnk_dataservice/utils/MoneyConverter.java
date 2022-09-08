package com.ironhack.vbnk_dataservice.utils;

import com.ironhack.vbnk_dataservice.data.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;
import java.util.Currency;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, String> {

//    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
//    private static final byte[] KEY = "MySuperSecretKey".getBytes();

    @Override
    public String convertToDatabaseColumn(Money value) {
        // TODO: 06/09/2022 Convert Money into parseable string
        return value.toParseableString();
    }

    @Override
    public Money convertToEntityAttribute(String dbData) {
        // TODO: 06/09/2022 Parse string into Money
        var val = dbData.split("//");
        return new Money(new BigDecimal(val[0]), Currency.getInstance(val[1]));
    }
}
