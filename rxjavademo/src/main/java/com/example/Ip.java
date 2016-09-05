package com.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

/**
 * Created by yakun on 2016/9/1.
 */

public class Ip {
    public static void main(String[] args) throws SocketException {
        Ip.GetNetIp();
    }

    public static void GetNetIp()
    {
        String IP = "";
        try
        {
            String address = "http://ip.taobao.com/service/getIpInfo2.php?ip=myip";
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setUseCaches(false);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStream in = connection.getInputStream();

// 将流转化为字符串
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(in));

                String tmpString = "";
                StringBuilder retJSON = new StringBuilder();
                while ((tmpString = reader.readLine()) != null)
                {
                    retJSON.append(tmpString + "\n");
                }
                System.out.println(retJSON);
                Gson gson = new Gson();
                IPGson ipGson = gson.fromJson(retJSON.toString(), IPGson.class);
                System.out.println(ipGson.getData().getIp());
            }
            else
            {
                IP = "";
            }
        }
        catch (Exception e)
        {
            IP = "";
        }
    }
}
