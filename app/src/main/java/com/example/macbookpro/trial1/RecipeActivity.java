package com.example.macbookpro.trial1;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RecipeActivity extends AppCompatActivity {

    String url = "http://food2fork.com/api/search?key=87764dbb662709d1b599d6cba177344e&q=shredded%20chicken";
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Recipe Publishers");
        mListView = (ListView) findViewById(R.id.recipe_list_view);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String inputLine;
        String wholeoutput="";
        ArrayList<Recipe> r1 = new ArrayList<Recipe>();

        try{
            URL oracle = new URL(url);
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

        String str1="";
        String str2="";
        String str3="";
        // / System.out.println("weathertype="+fullword);
        String[] pub;
        String[] tit;

        try{
            JSONParser jsonParser = new JSONParser();

            Object obj  = jsonParser.parse(fullword);
            JSONArray array = new JSONArray();
            array.add(obj);

            JSONObject jsonObject1=(JSONObject) array.get(0);

            JSONArray jArray1= (JSONArray) jsonObject1.get("recipes");

            int len = jArray1.size();
            pub = new String[len];
            tit = new String[len];
            for(int i=0;i<len;i++){
                JSONObject jsonObject2 = (JSONObject) jArray1.get(i);

                str1=jsonObject2.get("title").toString();
                tit[i]= str1;
                str2=jsonObject2.get("publisher").toString();
                str3=jsonObject2.get("image_url").toString();

                Recipe r2 = new Recipe(str1,str2,str3);
                r1.add(r2);

            }

            //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pub);
            RecipeAdapter adapter = new RecipeAdapter(this, r1);
            mListView.setAdapter(adapter);
        }
        catch(ParseException e){
            e.printStackTrace();
        }


    }

}
