package bytes.smart.ingdirectpay.utils;

/**
 * Created by Alexandru on 09-Jul-15.
 */
public class StringUtils {

    private static final String TAG = "StringUtils";

    /**
     * Trims the white space at the start and end of a string
     * @param value the string to be trimmed
     * @return the trimmed string
     */
    public static String trimStartEnd(String value)
    {
        value = trimStart(value);
        value = trimEnd(value);
        return value;
    }

    /**
     * Trims the white space at the start of a string.
     * @param value the string to be trimmed
     * @return the trimmed string
     */
    public static String trimStart(String value) {
        if (value != null) {
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) != ' ') {
                    value = value.substring(i, value.length());
                    break;
                }
            }
        }
        return value;
    }

    /**
     * Trims the white space at the end of a string.
     * @param value the string to be trimmed
     * @return the trimmed string
     */
    public static String trimEnd(String value) {
        if (value != null) {
            for (int i = value.length() - 1; i >= 0; i--) {
                if (value.charAt(i) != ' ') {
                    value = value.substring(0, i + 1);
                    break;
                }
            }
        }
        return value;
    }
}
