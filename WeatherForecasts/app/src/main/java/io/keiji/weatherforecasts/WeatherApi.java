package io.keiji.weatherforecasts;

import android.content.Context;
import android.net.http.AndroidHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yoshikinakamura on 12/2/14.
 */
public class WeatherApi {

    private static final String USER_AGENT = "WeatherForecasts Sample";
    private static final String URL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";

    /**
     * pointIdの示す天気情報をサーバから取得する
     * @param context
     * @param pointId
     * @return
     * @throws IOException
     */
    public static String getWeather(Context context, String pointId) throws IOException{

        AndroidHttpClient client = AndroidHttpClient.newInstance(USER_AGENT, context);
        HttpGet get = new HttpGet(URL + pointId);

        StringBuilder sb = new StringBuilder();
        try {
            HttpResponse responce = client.execute(get);
            BufferedReader br = new BufferedReader(new InputStreamReader(responce.getEntity().getContent()));

            String line = null;
            while((line = br.readLine()) != null){
                sb.append(line);
            }
        } finally {
            client.close();
        }

        return sb.toString();
    }
}
