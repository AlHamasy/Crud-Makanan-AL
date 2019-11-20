package com.catatanasad.crudmakanan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.catatanasad.crudmakanan.model.DataItem;
import com.catatanasad.crudmakanan.model.ResponseGetMakanan;
import com.catatanasad.crudmakanan.network.ConfigRetrofit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvMakanan = findViewById(R.id.rv_makanan);
        rvMakanan.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvMakanan.setHasFixedSize(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });

       reloadData();

    }

    @Override
    protected void onResume() {
        super.onResume();

        reloadData();
    }

    private void reloadData(){

        ConfigRetrofit.service.getMakanan().enqueue(new Callback<ResponseGetMakanan>() {
            @Override
            public void onResponse(Call<ResponseGetMakanan> call, Response<ResponseGetMakanan> response) {
                if (response.isSuccessful()){

                    // tampilkan di log, data pesan dan sukses'
                    ResponseGetMakanan responseGetMakanan = response.body();
                    List<DataItem> dataItem = responseGetMakanan.getData();

                    MakananAdapter adapter = new MakananAdapter(dataItem, MainActivity.this);
                    rvMakanan.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<ResponseGetMakanan> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

// menampilkan data list makanan dgn recycler view
// 1. buat layout item yang akan ditampilkan
// 2. panggil widget recyclerview di content_main
// 3. buat class adapter
// 4. panggil adapter di MainActivity

