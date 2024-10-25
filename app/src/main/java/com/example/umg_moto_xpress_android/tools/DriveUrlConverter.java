package com.example.umg_moto_xpress_android.tools;

public class DriveUrlConverter {


    public static String convertDriveUrl(String originalUrl) {
        // Verifica si la URL tiene el formato esperado de Google Drive
        if (originalUrl.contains("/file/d/")) {
            String[] parts = originalUrl.split("/file/d/");
            String[] idParts = parts[1].split("/");
            return "https://drive.google.com/uc?export=download&id=" + idParts[0];
        } else {
            // Si la URL no es del formato esperado, retorna la URL sin modificar
            return originalUrl;
        }
    }
}
