package id.pangu.crudonline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.pangu.crudonline.AddUpdateBiodataActivity;
import id.pangu.crudonline.Model.ResultItem;
import id.pangu.crudonline.R;

public class BiodataAdapter extends RecyclerView.Adapter<BiodataAdapter.BiodataViewHolder> {

    private List<ResultItem> mList ;
    private Context ctx;

    public BiodataAdapter(Context ctx, List<ResultItem> mList) {
        this.mList = mList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public BiodataAdapter.BiodataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_biodata,parent, false);
        return new BiodataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BiodataAdapter.BiodataViewHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class BiodataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nama) TextView tvNama;
        @BindView(R.id.tv_domisili) TextView tvDomisili;
        @BindView(R.id.tv_usia) TextView tvUsia;

        public BiodataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ResultItem resultItem) {
            tvNama.setText(resultItem.getNama());
            tvDomisili.setText(resultItem.getDomisili());
            tvUsia.setText(resultItem.getUsia());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ctx, AddUpdateBiodataActivity.class);
                    i.putExtra(AddUpdateBiodataActivity.EXTRA_INTENT, resultItem);
                    ctx.startActivity(i);
                }
            });
        }
    }
}
