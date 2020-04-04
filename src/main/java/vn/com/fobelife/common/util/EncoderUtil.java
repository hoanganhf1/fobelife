/**
 * 
 */
package vn.com.fobelife.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ahuynh
 *
 */
public class EncoderUtil {

    public static String encode(String rawData) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(rawData);
    }
}
