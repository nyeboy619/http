package rt.com.http;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Context mContext;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getApplicationContext();
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        mTextView = findViewById(R.id.text);
    }

    public void check(View v) {
        jsonObject();
//        stringObject();

    }

    public void jsonObject() {

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        String mJSONURLString ="https://reqres.in/api/users/2";

        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try {

                            StringBuilder formattedResult = new StringBuilder();

                            JSONArray responseJSONArray = response.getJSONArray("data");

                            for (int i = 0; i < responseJSONArray.length(); i++) { formattedResult.append("\n" + responseJSONArray.getJSONArray(i).get("id") + "=> \t" + responseJSONArray.getJSONObject(i).get("first_name"));

                            }
                            String data = formattedResult.toString();

                            mTextView.setText("List of Restaurants \n" + " ID no. " + "\t First : \n" + response.toString());

                        } catch (JSONException e) {

                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        mTextView.setText("That didn't work!");
                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }

        public void stringObject(){

// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://reqres.in/api/users/2";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
@Override
public void onResponse(String response) {
        // Display the first 500 characters of the response string.
        mTextView.setText("Response is: "+ response);
        }
        }, new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {
        mTextView.setText("That didn't work!");
        }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


        }

        }
