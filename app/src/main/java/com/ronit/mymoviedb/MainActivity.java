package com.ronit.mymoviedb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ImageView poster;
    TextView title, year, releaseDate,runtime, genre, director , plot, language, rating;
    EditText name;
    Button search;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        poster = findViewById(R.id.imageview);
        name = findViewById(R.id.enter_movie_name);
        year = findViewById(R.id.year);
        title = findViewById(R.id.title);
        releaseDate = findViewById(R.id.released_date);
        runtime= findViewById(R.id.runtime);
        genre = findViewById(R.id.genre);
        director = findViewById(R.id.Director);
        plot = findViewById(R.id.plot);
        language = findViewById(R.id.language);
        search = findViewById(R.id.searchBtn);
        rating = findViewById(R.id.IMDBrating);
        //cardView = findViewById(R.id.Card1);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty()){

                    Toast.makeText(MainActivity.this, "This field can't remain empty", Toast.LENGTH_SHORT).show();

                }else {
                    ShowMovie();
                   // cardView.setCardBackgroundColor(getResources().getColor(R.color.purple_700));
                }
            }
        });




    }

    private void ShowMovie() {
        String name_movie = name.getText().toString();
        String URL = "https://www.omdbapi.com/?t="+name_movie+"&apikey=API_KEY_HERE";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String title_get = response.getString("Title");
                    title.setText("Title: " +title_get);
                    title.setBackground(getResources().getDrawable(R.drawable.text1));

                    String year_get = response.getString("Year");
                    year.setText("Year: " +year_get);
                    year.setBackground(getResources().getDrawable(R.drawable.text2));

                    String released_get = response.getString("Released");
                    releaseDate.setText("Released Date: " +released_get);
                    releaseDate.setBackground(getResources().getDrawable(R.drawable.text1));

                    String runtime_get = response.getString("Runtime");
                    runtime.setText("Runtime: \n " +runtime_get);
                    runtime.setBackground(getResources().getDrawable(R.drawable.text2));

                    String genre_get = response.getString("Genre");
                    genre.setText("Genre: " +genre_get);
                    genre.setBackground(getResources().getDrawable(R.drawable.text1));

                    String director_get = response.getString("Director");
                    director.setText("Director: "+director_get);
                    director.setBackground(getResources().getDrawable(R.drawable.text2));

                    String language_get = response.getString("Language");
                    language.setText("Language: " +language_get);
                    language.setBackground(getResources().getDrawable(R.drawable.text1));

                    String rating_get = response.getString("imdbRating");
                    rating.setText("Rating: "+rating_get);
                    rating.setBackground(getResources().getDrawable(R.drawable.text2));

                    String plot_get  = response.getString("Plot");
                    plot.setText(plot_get);
                    plot.setBackground(getResources().getDrawable(R.drawable.plot));

                    String IMAGE_url = response.getString("Poster");
                    Glide.with(MainActivity.this).load(IMAGE_url).into(poster);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Failed to get data.. Try again later", Toast.LENGTH_LONG).show();

            }
        });
        queue.add(jsonObjectRequest);
    }
}