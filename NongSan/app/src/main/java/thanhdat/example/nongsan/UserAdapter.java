package thanhdat.example.nongsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private int resource;
    private List<User> listUser;;

    public UserAdapter(Context context, int resource, List<User> listUser) {
        this.context = context;
        this.resource = resource;
        this.listUser = listUser;
    }

    @Override
    public int getCount() {
        return listUser.size();
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
            TextView txtUserName;
            ImageView imgAvt,chat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(resource,null);
            viewHolder.txtUserName = convertView.findViewById(R.id.txtUSERNAME);
            viewHolder.imgAvt = convertView.findViewById(R.id.imgAvt);
            viewHolder.chat = convertView.findViewById(R.id.imageAvt);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        User user = listUser.get(position);
        viewHolder.txtUserName.setText(user.getHoten());
        Picasso.with(context)
                .load(user.getAvatar())
                .fit()
                .centerCrop()
                .into(viewHolder.imgAvt);
          return convertView;
    }

}
