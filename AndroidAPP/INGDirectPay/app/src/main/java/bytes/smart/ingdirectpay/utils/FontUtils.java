package bytes.smart.ingdirectpay.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {

	private static final String TAG = "FontUtils";

	private static Typeface regularTf;
    private static Typeface mediumTf;
    private static Typeface lightTf;
	private static Typeface boldTf;
	private static Typeface italicTf;

	public static void init(Context context)
	{
		regularTf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        mediumTf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
        lightTf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
		boldTf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
		italicTf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Italic.ttf");

	}
	
	public static Typeface getRegularFont()
	{
		return regularTf;
	}

    public static Typeface getMediumFont()
    {
        return mediumTf;
    }

    public static Typeface getLightFont()
    {
        return lightTf;
    }

    public static Typeface getBoldFont()
	{
		return boldTf;
	}
	
	public static Typeface getItalicFont()
	{
		return italicTf;
	}

    public static Typeface getLightTf() {return lightTf;}

}
