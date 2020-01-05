package com.sistempintar.app.myquiz;

import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CommunicationAsynctask extends AsyncTask <String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        String response = "";
        try {
            URL url = new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                response = IOUtils.toString(in, StandardCharsets.UTF_8.name());
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
