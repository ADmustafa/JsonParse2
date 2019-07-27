package com.mustafayavuz.mustafa.petlistjsonparse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerview;
    private ArrayList<ModelPetItem> myPetList;
    private PetAdapter myPetAdapter;
    private RequestQueue myRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerview = findViewById(R.id.rvObjects);
        myRecyclerview.setHasFixedSize(true);
        myRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        myPetList = new ArrayList<>();
        myRequestQueue = Volley.newRequestQueue(this);

        getParseJSON();
    }

    private void getParseJSON() {
        String url = "https://raw.githubusercontent.com/LearnWebCode/json-example/master/pets-data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("pets");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject pet = jsonArray.getJSONObject(i);

                                String name = pet.getString("name");
                                String species = pet.getString("species");
                                String photoURL = pet.getString("photo");

                                String foods;
                                if (pet.has("favFoods")) {
                                    JSONArray favFoods = pet.getJSONArray("favFoods");
                                    StringBuilder sbFood = new StringBuilder();

                                    for (int j = 0; j < favFoods.length(); j++) {
                                        String favFood = favFoods.getString(j);
                                        sbFood.append(favFood);

                                        if (j != favFoods.length() - 1)
                                           sbFood.append(", ");
                                    }

                                    foods = sbFood.toString();
                                } else {
                                    foods = "";
                                }

                                int birthDay = pet.getInt("birthYear");

                                myPetList.add(new ModelPetItem(name, species, photoURL, foods, birthDay));
                            }

                            myPetAdapter=new PetAdapter(MainActivity.this,myPetList);
                            myRecyclerview.setAdapter(myPetAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        myRequestQueue.add(request);
    }
}
