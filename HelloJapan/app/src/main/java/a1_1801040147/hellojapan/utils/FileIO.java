package a1_1801040147.hellojapan.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileIO {
    /**
     * @return
     * */
    public static String loadStringFromAsset(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            int length = is.read(buffer);

            System.out.println(length);

            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonString;
    }
}
