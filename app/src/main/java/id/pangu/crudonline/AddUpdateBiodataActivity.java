package id.pangu.crudonline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.pangu.crudonline.API.ApiClient;
import id.pangu.crudonline.API.ApiService;
import id.pangu.crudonline.Model.ApiResponse;
import id.pangu.crudonline.Model.ResultItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUpdateBiodataActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.et_nama) EditText etNama;
    @BindView(R.id.et_usia) EditText etUsia;
    @BindView(R.id.et_domisili) EditText etDomisili;
    @BindView(R.id.btn_save) Button btnSave;
    @BindView(R.id.btn_update) Button btnUpdate;
    @BindView(R.id.btn_delete) Button btnDelete;
    ProgressDialog pd;
    public static final String EXTRA_INTENT = "extra_intent";
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_biodata);
        ButterKnife.bind(this);

        btnSave.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        pd = new ProgressDialog(this);

        ResultItem data = getIntent().getParcelableExtra(EXTRA_INTENT);
        if (data != null){
            btnSave.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            id = String.valueOf(data.getId());
            etNama.setText(data.getNama());
            etUsia.setText(data.getUsia());
            etDomisili.setText(data.getDomisili());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save :
                pd.setMessage("send data ... ");
                pd.setCancelable(false);
                pd.show(); 

                String nama = etNama.getText().toString();
                String usia = etUsia.getText().toString();
                String domisili = etDomisili.getText().toString();

                ApiService service = ApiClient.getClient().create(ApiService.class);
                Call<ApiResponse> response = service.sendBiodata(nama, usia, domisili);
                response.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        int kode = response.body().getKode();

                        if((String.valueOf(kode)).equals("1"))
                        {
                            Toast.makeText(AddUpdateBiodataActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                            finish();
                        }else
                        {
                            Toast.makeText(AddUpdateBiodataActivity.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
                break;
            case R.id.btn_update :
                pd.setMessage("update ....");
                pd.setCancelable(false);
                pd.show();

                ApiService serviceUpdate = ApiClient.getClient().create(ApiService.class);
                Call<ApiResponse> responseUpdate = serviceUpdate.updateData(id, etNama.getText().toString(), etUsia.getText().toString(), etDomisili.getText().toString());
                responseUpdate.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        Log.d("Retro", "Response");
                        Toast.makeText(AddUpdateBiodataActivity.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        pd.hide();
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "OnFailure");
                    }
                });
                break;
            case R.id.btn_delete :
                pd.setMessage("Loading Hapus ...");
                pd.setCancelable(false);
                pd.show();

                ApiService serviceDelete = ApiClient.getClient().create(ApiService.class);
                Call<ApiResponse> responseDelete  = serviceDelete.deleteData(id);
                responseDelete.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        Log.d("Retro", "onResponse");
                        Toast.makeText(AddUpdateBiodataActivity.this, response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {

                    }
                });
                break;
        }
    }
}
