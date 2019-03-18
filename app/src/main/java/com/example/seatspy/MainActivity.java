package com.example.seatspy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.railwayapi.com/v2/between/source/";
    String original=url;
    EditText src,dest,date;
    TextView txtresponse;
    String source,destination,doj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.btn);
        txtresponse = findViewById(R.id.txtresponse);
        src=findViewById(R.id.src);
        dest = findViewById(R.id.dest);
        date = findViewById(R.id.date);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Getting your PNR status", Toast.LENGTH_SHORT).show();
                source=src.getText().toString();
                destination=dest.getText().toString();
                doj = date.getText().toString();
                url = original+source+"/dest/"+destination+"/date/"+doj+"/apikey/v0936vp7s6/";
                display();
            }
        });
    }

    private void display() {
        Toast.makeText(this, "url: "+url, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "response: "+response.toString(), Toast.LENGTH_SHORT).show();
                    txtresponse.setText(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
