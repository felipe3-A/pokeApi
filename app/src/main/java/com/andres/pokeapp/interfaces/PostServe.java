package com.andres.pokeapp.interfaces;

import com.andres.pokeapp.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public  interface PostServe {

    //Crear el metodo para obtener los datos
    @GET("post") Call<List<Post>> find(@Query("q") String q);
}
