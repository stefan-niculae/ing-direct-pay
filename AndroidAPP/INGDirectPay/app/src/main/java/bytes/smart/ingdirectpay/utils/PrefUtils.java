package bytes.smart.ingdirectpay.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by alexandru.buicescu on 27.05.2015.
 */

public class PrefUtils {

    private static final String TAG = "PrefUtils";

    /**
     * Gets the shared preferences.
     *
     * @param context the context
     * @return the shared preferences
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Gets the shared preferences.
     *
     * @param context            the context
     * @param preferenceFileName the preference file name
     * @return the shared preferences
     */
    public static SharedPreferences getSharedPreferences(Context context, String preferenceFileName) {
        return context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
    }

    /**
     * Gets the string from prefs.
     *
     * @param context      the context
     * @param key          the key
     * @param defaultValue the default value
     * @return the string from prefs
     */
    public static String getStringFromPrefs(Context context, String key, String defaultValue) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        return localPrefs.getString(key, defaultValue);
    }

    /**
     * Gets the int from prefs.
     *
     * @param context      the context
     * @param key          the key
     * @param defaultValue the default value
     * @return the int from prefs
     */
    public static Integer getIntFromPrefs(Context context, String key, int defaultValue) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        return localPrefs.getInt(key, defaultValue);
    }

    public static Long getLongFromPrefs(Context context, String key, long defaultValue) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        return localPrefs.getLong(key, defaultValue);
    }

    public static void setLongToPrefs(Context context, String key, long value) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        localPrefs.edit().putLong(key, value).commit();
    }

    /**
     * Gets the boolean from prefs.
     *
     * @param context      the context
     * @param key          the key
     * @param defaultValue the default value
     * @return the boolean from prefs
     */
    public static boolean getBooleanFromPrefs(Context context, String key, boolean defaultValue) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        return localPrefs.getBoolean(key, defaultValue);
    }

    /**
     * Sets the string to prefs.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     */
    public static void setStringToPrefs(Context context, String key, String value) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        localPrefs.edit().putString(key, value).commit();
    }

    /**
     * Sets the int to prefs.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     */
    public static void setIntToPrefs(Context context, String key, int value) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        localPrefs.edit().putInt(key, value).commit();
    }

    /**
     * Sets the boolean to prefs.
     *
     * @param context the context
     * @param key     the key
     * @param value   the value
     */
    public static void setBooleanToPrefs(Context context, String key, boolean value) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        localPrefs.edit().putBoolean(key, value).commit();
    }

    /**
     * Gets the integer list from prefs.
     *
     * @param context      the context
     * @param key          the key
     * @param defaultValue the default value
     * @return the integer list from prefs
     */
    public static ArrayList<Integer> getIntListFromPrefs(Context context, String key, ArrayList<Integer> defaultValue) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        String s = localPrefs.getString(key, "");

        if (TextUtils.isEmpty(s)) {
            return defaultValue;
        }

        StringTokenizer st = new StringTokenizer(s, ",");
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (st.hasMoreTokens()) {
            result.add(Integer.parseInt(st.nextToken()));
        }
        return result;
    }

    /**
     * Sets the int list to prefs.
     *
     * @param context the context
     * @param key     the key
     * @param values  the int list
     */
    public static void setIntListToPrefs(Context context, String key, List<Integer> values) {
        String s = "";
        for (Integer i : values) {
            s += i + ",";
        }

        setStringToPrefs(context, key, s);
    }

    /**
     * Removes the key from shared preferences.
     *
     * @param context the context
     * @param key     the key to be removed
     */
    public static void removeKeyFromPrefs(Context context, String key) {
        SharedPreferences localPrefs = getSharedPreferences(context);
        localPrefs.edit().remove(key).commit();
    }

    /**
     * Clears all the shared preferences saved data.
     * @param context the context
     */
    public static void clearSharedPreferences(Context context)
    {
        SharedPreferences localPrefs = getSharedPreferences(context);
        localPrefs.edit().clear().commit();
    }

}
