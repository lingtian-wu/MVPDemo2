package com.bawei.wushuai20200223.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    private static final NetUtil NETUTIL = new NetUtil();

    private NetUtil(){}
    public static NetUtil getInstance(){
        return NETUTIL;
    }

    public boolean hasNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null&& activeNetworkInfo.isAvailable()){
            return true;
        }else {
            return false;
        }
    }
    private String intoString(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int  len = -1;
        while ((len = inputStream.read(bytes))!=-1){
            byteArrayOutputStream.write(bytes,0,len);
        }
        String bytes1 = byteArrayOutputStream.toString();
        String json = new String(bytes1);
        return json;
    }
    public String doGet(String urlstr){
        InputStream inputStream = null;
        try {
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            if (connection.getResponseCode() == 200){
                inputStream = connection.getInputStream();
                String json = intoString(inputStream);
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void getPost(final String path, final ImageView imageView) {
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                String pa = strings[0];
                try {
                    URL url = new URL(pa);
                    //获取HttpURLConnection
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //设置请求超时，读取超时
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.connect();
                    //根据状态码判断请求
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == 200) {
                        //成功
                        InputStream inputStream = httpURLConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                //判空
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }.execute(path);
    }
}
