package id.pangu.crudonline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.pangu.crudonline.API.ApiClient;
import id.pangu.crudonline.API.ApiService;
import id.pangu.crudonline.Adapter.BiodataAdapter;
import id.pangu.crudonline.Model.ApiResponse;
import id.pangu.crudonline.Model.ResultItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rv_main) RecyclerView rvMain;
    @BindView(R.id.fab_add_biodata) FloatingActionButton fabAdd;
    private BiodataAdapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<ResultItem> mItems = new ArrayList<>();
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pd = new ProgressDialog(this);
        fabAdd.setOnClickListener(this);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        rvMain.setLayoutManager(mManager);

    }

    private void getData(){
        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ApiResponse> response = service.getBiodata();
        response.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new BiodataAdapter(MainActivity.this, mItems);
                rvMain.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : respon gagal");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_add_biodata :
                startActivity(new Intent(MainActivity.this, AddUpdateBiodataActivity.class));
                break;
        }
    }
}
