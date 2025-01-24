package az.abbtech.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class BCryptEncoderUtil {
    private static final BCryptPasswordEncoder INSTANCE;

    private BCryptEncoderUtil() {
    }

    static {
        INSTANCE = new BCryptPasswordEncoder();
    }

    public static String encode(String rawPassword) {
        return INSTANCE.encode(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return INSTANCE.matches(rawPassword, encodedPassword);
    }

}
