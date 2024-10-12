package com.example.umg_moto_xpress_android.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LocalStorageBase64 {
    private static LocalStorageBase64 INSTANCE = null;

    public static LocalStorageBase64 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalStorageBase64();
        }
        return INSTANCE;
    }

    /**
     * Con esto puedo pintar una imagen base64 en un viewimagen
     */
    public Bitmap displayBase64Image(String base64Image) {
        Bitmap bitmap = base64ToBitmap(base64Image);
       // binding.imgEmpty.setImageBitmap(bitmap);
        return bitmap;
    }
    public Bitmap base64ToBitmap(String base64String) {
        byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    /**
     * Sirve para traer la Base64 de una imagen
     */
    public String getBaseSelectStorage(Context context,Uri imageUri){
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
            byte[] imageBytes = getBytes(inputStream);

            String base64Image = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            return base64Image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        return byteBuffer.toByteArray();
    }
}
