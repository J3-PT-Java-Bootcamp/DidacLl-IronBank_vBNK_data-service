package com.ironhack.vbnk_dataservice.utils;

import com.ironhack.vbnk_dataservice.data.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, String> {

//    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
//    private static final byte[] KEY = "MySuperSecretKey".getBytes();

    @Override
    public String convertToDatabaseColumn(Money value) {
        // TODO: 06/09/2022 Convert Money into parseable string
        // do some encryption
//        Key key = new SecretKeySpec(KEY, "AES");
//        try {
//            Cipher c = Cipher.getInstance(ALGORITHM);
//            c.init(Cipher.ENCRYPT_MODE, key);
//            return Base64.toBase64String(c.doFinal(value.getBytes()));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public Money convertToEntityAttribute(String dbData) {
        // TODO: 06/09/2022 Parse string into Money
//        // do some decryption
//        Key key = new SecretKeySpec(KEY, "AES");
//        try {
//            Cipher c = Cipher.getInstance(ALGORITHM);
//            c.init(Cipher.DECRYPT_MODE, key);
//            return new String(c.doFinal(Base64.decode(dbData)));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }
}
