package bytes.smart.ingdirectpay.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.image.ImageType;

import java.io.ByteArrayOutputStream;

/**
 * Created by alexbuicescu on 21.11.2015.
 */
public class QRCodeUtils {

    public static Bitmap generateQRCode(String data){
        ByteArrayOutputStream blob = QRCode.from(data).to(ImageType.PNG).withSize(512, 512).stream();
        byte[] bitmapdata = blob.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);

        return bitmap;
    }
}
