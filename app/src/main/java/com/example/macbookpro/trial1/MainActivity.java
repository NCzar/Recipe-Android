package com.example.macbookpro.trial1;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    // we"ll make HTTP request to this URL to retrieve weather conditions
    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=seattle,us&appid=2156e2dd5b92590ab69c0ae1b2d24586&units=metric";
    //the loading Dialog

    // Textview to show temperature and description
    TextView temperature, description;
    // background image
    ImageView weatherBackground;
    // JSON object that contains weather information
    JSONObject jsonObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        temperature = (TextView) findViewById(R.id.temperature);
        description = (TextView) findViewById(R.id.description);
        weatherBackground = (ImageView) findViewById(R.id.weatherbackground);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String inputLine;
        String wholeoutput="";

        try{
            URL oracle = new URL(weatherWebserviceURL);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));


            wholeoutput="";
            while ((inputLine = in.readLine()) != null){

                wholeoutput=wholeoutput+inputLine;
            }
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e1){
            e1.printStackTrace();
        }


        String fullword=wholeoutput;
        int len=fullword.length();
        // String str10="["+fullword.substring(0, len)+"]";


        String str="";
        String str1="";
        String str2="";
        System.out.println("weathertype="+fullword);
    try{
    JSONParser jsonParser = new JSONParser();

    Object obj  = jsonParser.parse(fullword);
    JSONArray array = new JSONArray();
    array.add(obj);

    JSONObject jsonObject1=(JSONObject) array.get(0);

    JSONArray jArray1= (JSONArray) jsonObject1.get("weather");

    JSONObject jsonObject2 = (JSONObject) jArray1.get(0);

    str1=jsonObject2.get("main").toString();
    str=jsonObject2.get("description").toString();

        String st1 =  jsonObject1.get("main").toString();

        JSONParser jsonParser2 = new JSONParser();

        Object obj2  = jsonParser2.parse(st1);
        JSONArray array2 = new JSONArray();
        array2.add(obj2);

        System.out.println(array2.toString());
       JSONObject jsonObject3 = (JSONObject) array2.get(0);
        str2=jsonObject3.get("temp").toString();

    System.out.println("weathertype="+str1);
    System.out.println("description="+str);
        System.out.println("temp="+str2);
    }
    catch(ParseException e){
    e.printStackTrace();
    }

        String backgroundImage = "";

        if (str1.equals("Clouds")) {
            backgroundImage = "https://marwendoukh.files.wordpress.com/2017/01/clouds-wallpaper2.jpg";
        } else if (str1.equals("Rain")) {
            backgroundImage = "https://marwendoukh.files.wordpress.com/2017/01/rainy-wallpaper1.jpg";
        } else if (str1.equals("Snow")) {
            backgroundImage = "https://marwendoukh.files.wordpress.com/2017/01/snow-wallpaper1.jpg";
        }

        Glide
                .with(getApplicationContext())
                .load(backgroundImage)
                .centerCrop()
                .crossFade()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target target, boolean isFirstResource) {
                        System.out.println(e.toString());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(weatherBackground);
        temperature.setText(str2);
        description.setText(str);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
