package com.example.bloodrequisitionsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText uid,upass;
    public static String url = "https://bloodrequisitionsystem.000webhostapp.com/";   //Online php file url
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        uid=(EditText)findViewById(R.id.logid);
        upass=(EditText)findViewById(R.id.logpass);

        if(ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]  {   Manifest.permission.WRITE_EXTERNAL_STORAGE},101 );

        }
    }


    public void login(View view) {

        if (uid.getText().toString().equals("") || upass.getText().toString().equals("") ) {
            if (uid.getText().toString().equals("")) {
                uid.setError("Please Enter UID.");
            }
            if (upass.getText().toString().equals("")) {
                upass.setError("Please Enter Password.");
            }

        } else {

            StringRequest request = new StringRequest(Request.Method.POST, url + "login.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(LoginActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                            if (response.equals("logged in successfully")) {
                                SharedPreferences sharedPreferences = getSharedPreferences("myinfo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("myuid", uid.getText().toString());
                                editor.apply();
                                takedata();
                            }
                            else{
                                uid.setText("");
                                uid.setError("Please check login ID");
                                upass.setText("");
                                upass.setError("Please check Password");
                            }
                            response = null;
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(LoginActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> param = new HashMap<String, String>();
                    param.put("username", uid.getText().toString());
                    param.put("password", upass.getText().toString());
                    return param;

                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(request);
        }
        }

    private void takedata() {

        StringRequest request = new StringRequest(Request.Method.POST, url+"getmyinfo.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray ja=new JSONArray(response);

                            JSONObject job= ja.getJSONObject(0);
                            SharedPreferences sharedPreferences1 = getSharedPreferences("myinfo", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                            editor1.putString("myname", job.getString("fname")+" "+job.getString("lname"));
                            editor1.putString("mybg", job.getString("bloodgroup"));
                            editor1.apply();
                            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("firstStart", false);
                            editor.apply();
                            uid.setText("");
                            upass.setText("");
                            Intent i = new Intent(LoginActivity.this, Home_Activity.class);
                            startActivity(i);
                            finish();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> param = new HashMap<String,String>();
                param.put("query","SELECT fname,lname,bloodgroup FROM users WHERE uid ='"+uid.getText().toString()+ "';");
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(request);
    }


    public void register(View view) {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}