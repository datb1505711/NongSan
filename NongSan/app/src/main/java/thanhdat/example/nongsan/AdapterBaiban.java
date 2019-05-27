package thanhdat.example.nongsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBaiban extends BaseAdapter {
    public AdapterBaiban(Context context, int resource, List<Baiban> listbaiban) {
        this.context = context;
        this.resource = resource;
        this.listbaiban = listbaiban;
    }

    private Context context;
    private int resource;
    private List<Baiban> listbaiban;
    @Override
    public int getCount() {
        return listbaiban.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        ImageView imgSanpham;
        TextView txttieude,txtthoigian,txtgia,txtmota,txttinhthanh;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(resource,null);
            viewHolder.txttieude = convertView.findViewById(R.id.txtTieude);
            viewHolder.txtmota = convertView.findViewById(R.id.txtMota);
            viewHolder.txtthoigian = convertView.findViewById(R.id.txtThoigian);
            viewHolder.txttinhthanh = convertView.findViewById(R.id.txtTinhthanh);
            viewHolder.txtgia = convertView.findViewById(R.id.txtGiasanpham);
            viewHolder.imgSanpham = convertView.findViewById(R.id.imgBaiviet);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Baiban baiban = listbaiban.get(position);
        viewHolder.txttieude.setText(baiban.getTieude());
        viewHolder.txtmota.setText(baiban.getMotasanpham());
        viewHolder.txtthoigian.setText(baiban.getNgaydangbaiviet());
        viewHolder.txttinhthanh.setText(baiban.getTinhthanh());
        viewHolder.txtgia.setText(baiban.getGiasanpham() + " Vnd");
        Picasso.with(context)
                .load(baiban.getIdhinhanhsanpham())
                .fit()
                .centerCrop()
                .into(viewHolder.imgSanpham);
        return convertView;
    }
}
