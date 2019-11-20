package com.catatanasad.crudmakanan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.catatanasad.crudmakanan.model.DataItem;
import com.catatanasad.crudmakanan.model.ResponseGetMakanan;
import com.catatanasad.crudmakanan.network.ConfigRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDeleteActivity extends AppCompatActivity {

    DataItem dataItem;
    private ImageView imgMakanan;
    private EditText edtUpdateNama, edtUpdateHarga, edtUpdateUrl;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        imgMakanan = findViewById(R.id.img_update);
        edtUpdateNama = findViewById(R.id.edt_update_nama);
        edtUpdateHarga = findViewById(R.id.edt_update_harga);
        edtUpdateUrl = findViewById(R.id.edt_update_url);

        // terima data
        dataItem = getIntent().getParcelableExtra("DATA_ITEM");


        // tampilkan data yang akan diupdate
        edtUpdateNama.setText(dataItem.getMenuNama());
        edtUpdateHarga.setText(dataItem.getMenuHarga());
        edtUpdateUrl.setText(dataItem.getMenuGambar());
        Glide.with(this).load(dataItem.getMenuGambar()).into(imgMakanan);

        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete){

            ConfigRetrofit.service.deleteMakanan(dataItem.getMenuId()).enqueue(new Callback<ResponseGetMakanan>() {
                @Override
                public void onResponse(Call<ResponseGetMakanan> call, Response<ResponseGetMakanan> response) {
                    if (response.isSuccessful()){

                        ResponseGetMakanan responseGetMakanan = response.body();
                        String pesan = responseGetMakanan.getPesan();
                        boolean sukses = responseGetMakanan.isSukses();

                        if (sukses){
                            Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(UpdateDeleteActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetMakanan> call, Throwable t) {
                    Toast.makeText(UpdateDeleteActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }
}
