package com.example.stenleyaltidor.popularmoviesstage1;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class Data {


    public Data() {
    }


    public String getData(String url){



        try {
//            connecting to a url
            HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());

            connection.setRequestMethod("GET");

            connection.setDoOutput(true);
            connection.connect();

//            inputstream

            InputStream inputStream = connection.getInputStream();


//            read the data from the inputstream
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null){
                stringBuffer.append(line +"\n\r");


            }
            String data = stringBuffer.toString();

            inputStream.close();
            connection.disconnect();


            return data;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }


}
