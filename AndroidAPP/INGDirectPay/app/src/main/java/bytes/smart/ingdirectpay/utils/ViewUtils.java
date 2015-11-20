package bytes.smart.ingdirectpay.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

import bytes.smart.ingdirectpay.R;
import bytes.smart.ingdirectpay.views.customwidgets.CustomTypefaceSpan;

/**
 * Created by Alexandru on 31-May-15.
 */
public class ViewUtils {

    private static final String TAG = "ViewUtils";


    public static void setActionBarIcon(Context context, Drawable icon)
    {
        if(((AppCompatActivity)context).getSupportActionBar() != null)
        {
            ((AppCompatActivity)context).getSupportActionBar().setIcon(icon);
        }
        else
        {
            Log.e("ViewUtils", "setActionBarIcon: actionbar is null");
        }
    }

    public static ArrayList<Integer> getCustomColorsArrayList(Context context)
    {
        TypedArray colorsTypedArray = context.getResources().obtainTypedArray(R.array.custom_colors_array);
        ArrayList<Integer> colorsArrayList = new ArrayList<>();

        for (int i = 0; i < colorsTypedArray.length(); i++) {
            colorsArrayList.add(colorsTypedArray.getColor(i, 0));
        }
        colorsTypedArray.recycle();

        return colorsArrayList;
    }

    public static Integer getRandomColor(Context context)
    {
        Random randomColor = new Random(System.currentTimeMillis());

        ArrayList<Integer> colorsArrayList = getCustomColorsArrayList(context);

        return colorsArrayList.get(randomColor.nextInt(colorsArrayList.size()));
    }

    public static void showKeyboard(Activity activity, EditText editText)
    {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(editText, 0);
    }

    public static void hideKeyboard(Activity activity, EditText editText)
    {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * Sets the action bar title. The back button will be shown by default.
     * Use setActionBarTitle(Activity activity, String title, boolean showBackButton) in order to
     * show or hide the back button.
     * @param context the context
     * @param title the title to show in action bar
     */
    public static void setActionBarTitle(Context context, String title)
    {
        setActionBarTitle(context, title, true);
    }

    /**
     * Sets the action bar title.
     * @param context the context
     * @param title the title to show in action bar
     * @param showBackButton if true then the back button will be visible,
     *                       if false then the back button will be hidden
     */
    public static void setActionBarTitle(Context context, String title, boolean showBackButton)
    {
        // Update the action bar title with the TypefaceSpan instance
        ActionBar actionBar = ((AppCompatActivity)context).getSupportActionBar();
        if(actionBar != null && title != null)// && title.length() > 0)
        {
            SpannableString s = new SpannableString(title);
            s.setSpan(new CustomTypefaceSpan(FontUtils.getRegularFont()), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            actionBar.setTitle(title);

            actionBar.setDisplayHomeAsUpEnabled(showBackButton);

            actionBar.setElevation(0);
            Log.i("ViewUtils", "setActionBarTitle: actionbar is " + title);
        }
        else
        {
            Log.e("ViewUtils", "setActionBarTitle: actionbar is null");
        }
    }

    public static void showProgressDialog(final Context context, String title, String message,
                                          final Runnable runnable) {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(context, title, message, true);
        //you usually don't want the user to stop the current process, and this will make sure of that
        ringProgressDialog.setCancelable(false);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                runnable.run();
                ringProgressDialog.dismiss();
            }
        });
        th.start();
    }

    public static void showCloseDialog(final Context context, int messageId, Runnable... okRunnable)
    {
        showCloseDialog(context, context.getResources().getString(messageId), okRunnable);
    }

    public static void showCloseDialog(final Context context, String message, final Runnable... okRunnable)
    {
        AlertDialog.Builder discardEditDialog = new AlertDialog.Builder(context);
        discardEditDialog.setTitle("Discard changes")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(okRunnable != null && okRunnable.length > 0 && okRunnable[0] != null)
                        {
                            okRunnable[0].run();
                        }
                        ((Activity)context).finish();
                    }
                })
                .setNegativeButton("No", null);
        discardEditDialog.setMessage(message);
        discardEditDialog.create().show();
    }

    /**
     *
     * @param context the context
     * @param color the color
     * @return a circle drawable with the color provided by color
     */
    public static Drawable getColoredCircleDrawableByColor(final Context context, int color)
    {
        Drawable tempDrawable = context.getResources().getDrawable(R.drawable.shape_color);
        LayerDrawable bubble = (LayerDrawable) tempDrawable;
        GradientDrawable solidColor = (GradientDrawable) bubble.findDrawableByLayerId(R.id.shape_color_item);
        solidColor.setColor(color);

        return tempDrawable;
    }

}
