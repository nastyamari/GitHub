package com.example.gitnubpr;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view) {
        GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
        final Call<List<Contributor>> call =
                gitHubService.repoContributors("square", "picasso");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                final TextView textView = (TextView) findViewById(R.id.textView);
                //final TextView textView2 = (TextView) findViewById(R.id.textView2);
                String[] contr = response.body().toString().split(",");
                //String[] contr2 = contr.toString().split(";");
                StringBuilder builderName = new StringBuilder();
                for (int i = 0; i < contr.length; i++)
                {
                    builderName.append(contr[i]);
                    builderName.append("\n");
                }

                builderName.append("Васильева настя");
                //textView.setText(response.body().toString());
               textView.setText(builderName.toString());

            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable throwable) {
                final TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("Что-то пошло не так: " + throwable.getMessage());
            }
        });
    }
}