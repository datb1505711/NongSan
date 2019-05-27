package thanhdat.example.nongsan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
public class MessageAdapter extends BaseAdapter {
    private Context context;
    private int[] resource;
    private List<Message> listMessage;

    public MessageAdapter(Context context, int[] resource, List<Message> listMessage) {
        this.context = context;
        this.resource = resource;
        this.listMessage = listMessage;
    }




    @Override
    public int getCount() {
        return listMessage.size();
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
        TextView txtMessage;
    }

    public MessageAdapter() {
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            if(resource[position]==1) {
                convertView = inflater.inflate(R.layout.layout_message2, null);
                viewHolder.txtMessage = convertView.findViewById(R.id.txtMessage);
            }
            else{
                convertView = inflater.inflate(R.layout.layout_message, null);
                viewHolder.txtMessage = convertView.findViewById(R.id.txtMessage);

            }
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Message mess = listMessage.get(position);
        if(resource[position]==1) {
            viewHolder.txtMessage.setText(mess.getMessage());
        }
        else{
            viewHolder.txtMessage.setText(mess.getMessage());
        }

        return convertView;
    }
}
