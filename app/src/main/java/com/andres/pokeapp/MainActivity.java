package com.andres.pokeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andres.pokeapp.interfaces.PostServe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText editText;
Button button;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.edtPalabra);
        button=findViewById(R.id.btnbuscar);
        textView=findViewById(R.id.txtResultado);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                resultado(editText.getText().toString());
            }
        });
    }

    public void resultado(String q) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostServe postServe=retrofit.create(PostServe.class);
        Call<List<Post>> call=postServe.find(q);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> postList=response.body();
                for (Post p:postList){
                    String resultado="";
                    resultado+="UserID: "+p.getUserId()+"\n";
                    resultado+="ID: "+p.getId()+"\n";
                    resultado+="Title :"+p.getTitle()+"\n";
                    resultado+="Body: "+p.getBody()+"\n";
                    textView.append(resultado);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
              textView.setText(t.getMessage());
            }
        });

    }
}