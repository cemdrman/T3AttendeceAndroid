package jekirdek.com.t3mobil.utility;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * Created by cem on 28.09.2017.
 */

public class Security {

    private static String apiKey = "mobileapp";
    private static String password = "!Deneyap@T3#Automation$";
    private static int random = getRandomNumber();

    private static int getRandomNumber(){
        Random random = new Random();
        int rnd = random.nextInt(23031994);
        Log.i(TAG, "getRandomNumber: " + rnd);
        return rnd;
    }

    private static byte[] makeMd5Key(){
        String hashString = apiKey + password + random;
        MessageDigest messageDigest = null;
        byte[] digest = new byte[1000];
        try {
            messageDigest  = MessageDigest.getInstance("MD5");
            messageDigest.update(hashString.getBytes());
            digest = messageDigest.digest();
            convertByteArrayToHexString(digest);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return digest;
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }

    public static String getUrl(){
        String url;
        url = "&random=" + random + "&hash=" + convertByteArrayToHexString(makeMd5Key());
        return url;
    }
}
