package com.catatanasad.crudmakanan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.catatanasad.crudmakanan.model.ResponseGetMakanan;
import com.catatanasad.crudmakanan.network.ConfigRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {

    private EditText edtInsertNama, edtInsertHarga, edtInsertUrlGambar;
    private Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtInsertHarga = findViewById(R.id.edt_insert_harga);
        edtInsertNama = findViewById(R.id.edt_insert_nama);
        edtInsertUrlGambar = findViewById(R.id.edt_insert_urlgambar);
        btnInsert = findViewById(R.id.btn_insert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nama = edtInsertNama.getText().toString();
                String harga = edtInsertHarga.getText().toString();
                String urlGambar = edtInsertUrlGambar.getText().toString();

                // validasi jika kosong
                if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(harga) || TextUtils.isEmpty(urlGambar)){
                    Toast.makeText(InsertActivity.this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    // insert ke database
                    ConfigRetrofit.service.insertMakanan(nama, harga, urlGambar).enqueue(new Callback<ResponseGetMakanan>() {
                        @Override
                        public void onResponse(Call<ResponseGetMakanan> call, Response<ResponseGetMakanan> response) {
                            if (response.isSuccessful()){

                                ResponseGetMakanan responseGetMakanan = response.body();
                                String pesan = responseGetMakanan.getPesan();
                                boolean sukses = responseGetMakanan.isSukses();

                                if (sukses){
                                    Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseGetMakanan> call, Throwable t) {
                            Toast.makeText(InsertActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
