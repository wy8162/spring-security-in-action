package y.w.security.basic.ch3_4_5_security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class EncryptDecrypt {

    @Test
    void std() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor e = Encryptors.standard(password, salt);
        byte [] encrypted = e.encrypt(valueToEncrypt.getBytes());
        byte [] decrypted = e.decrypt(encrypted);

        System.out.println(encrypted.toString());
        System.out.println(decrypted.toString());
    }

    @Test
    void stronger() {

        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor e = Encryptors.stronger(password, salt);
        byte [] encrypted = e.encrypt(valueToEncrypt.getBytes());
        byte [] decrypted = e.decrypt(encrypted);

        System.out.println(encrypted.toString());
        System.out.println(decrypted.toString());
    }

    @Test
    void textStd() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        TextEncryptor e = Encryptors.text(password, salt);
        String encrypted = e.encrypt(valueToEncrypt);
        String decrypted = e.decrypt(encrypted);

        System.out.println(encrypted.toString());
        System.out.println(decrypted.toString());
    }

    @Test
    void textStronger() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        TextEncryptor e = Encryptors.delux(password, salt);
        String encrypted = e.encrypt(valueToEncrypt);
        String decrypted = e.decrypt(encrypted);

        System.out.println(encrypted.toString());
        System.out.println(decrypted.toString());
    }
}
