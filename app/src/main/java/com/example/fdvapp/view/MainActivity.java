package com.example.fdvapp.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.fdvapp.R;
import com.example.fdvapp.fdvapi.CustomDeserializer;
import com.example.fdvapp.fdvapi.FdvApiService;
import com.example.fdvapp.model.RandomUserResponse;
import com.example.fdvapp.model.User;
import com.example.fdvapp.utils.Adapter;
import com.example.fdvapp.utils.OnItemClickListener;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private Retrofit retrofit;
    private ArrayList<User> users;
    private RecyclerView recyclerView;

    private int page;
    private boolean suitableForCharging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    int totalItemCount = layoutManager.getItemCount();

                    if(suitableForCharging){
                        if((visibleItemCount + pastVisibleItems)>= totalItemCount){
                            suitableForCharging=false;
                            page++;
                            getData(page);

                        }

                    }

                }
            }
        });

        users = new ArrayList<>();

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(User.class, new CustomDeserializer());

        retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/api/")
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();

        getData(page);

    }

    private void getData(int page) {
        FdvApiService service = retrofit.create(FdvApiService.class);
        Call<RandomUserResponse> results = service.getUserList(page, 50, "abc");

        results.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {
                suitableForCharging = true;
                if (response.isSuccessful()) {

                    RandomUserResponse randomUserResponse = response.body();
                    users = randomUserResponse.getResults();

                    Adapter adapter = new Adapter(MainActivity.this, users);
                    adapter.addList(users);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(MainActivity.this);

                } else {
                    Toast.makeText(MainActivity.this, "Error onResponse: " + response.code(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                suitableForCharging = true;

                Toast.makeText(MainActivity.this, "Error onFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        Bundle detailBundle = new Bundle();
        User clickedUser = users.get(position);

        String firstName = clickedUser.getFirst();
        String lastName = clickedUser.getLast();
        String userName = clickedUser.getUsername();
        String email = clickedUser.getEmail();
        String largeImageUrl = clickedUser.getLarge();

        detailBundle.putString("firstName", firstName);
        detailBundle.putString("lastName", lastName);
        detailBundle.putString("userName", userName);
        detailBundle.putString("email", email);
        detailBundle.putString("largeImageUrl", largeImageUrl);

        detailIntent.putExtras(detailBundle);
        startActivity(detailIntent);
    }
}
