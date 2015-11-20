package bytes.smart.ingdirectpay.utils;

import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class IBANUtils {

    public static boolean validateIBAN(String iban)
    {
        try {
            IbanUtil.validate(iban);
            return true;
            // valid
        } catch (IbanFormatException e) {
            // invalid
            e.printStackTrace();
            return false;
        }
    }
}
