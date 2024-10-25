package com.example.umg_moto_xpress_android.tools;

import android.util.Base64;

import com.example.umg_moto_xpress_android.models.data.UserDecodeData;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class JwtDecoder {

    public static String decodeJWT(String jwtEncoded) throws Exception {
        try {
            String[] split = jwtEncoded.split("\\.");
            String payload = getJson(split[1]);
            return payload;
        } catch (UnsupportedEncodingException e) {
            throw new Exception("Error al decodificar el JWT", e);
        }
    }

    public static UserDecodeData getNameDecode(String jwtToken) throws Exception {
        try {
            String decodedPayload = JwtDecoder.decodeJWT(jwtToken);
            JSONObject payloadJson = new JSONObject(decodedPayload);
            return new UserDecodeData(payloadJson.getString("sub"),payloadJson.getString("role"));
        } catch (UnsupportedEncodingException e) {
            throw new Exception("Error al decodificar el JWT", e);
        }
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
