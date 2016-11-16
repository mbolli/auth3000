package ch.mbolli.awt.auth3000;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable {

    private String email;
    private String password;
    private byte[] passwordHashed;
    private byte[] salt;

    public String save() {
        setSalt(generateSalt());
        setPasswordHashed(hashPassword(getPassword().toCharArray(), getSalt()));
        setPassword("\0");
        Persist.getInstance().addUser(this);
        return "user_save_success";
    }

    public static byte[] generateSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        return salt;
    }

    public static byte[] hashPassword( final char[] password, final byte[] salt ) {
        final int iterations = 30;
        final int keyLength = 160;
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;
        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed(byte[] passwordHashed) {
        this.passwordHashed = passwordHashed;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordHashed=" + Arrays.toString(passwordHashed) +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}