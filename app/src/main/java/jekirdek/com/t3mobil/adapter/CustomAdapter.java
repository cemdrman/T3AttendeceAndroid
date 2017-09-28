package jekirdek.com.t3mobil.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

import jekirdek.com.t3mobil.R;
import jekirdek.com.t3mobil.model.AttendeceListModel;

/**
 * Created by cem
 */
public class CustomAdapter extends BaseAdapter {

    boolean[] checkBoxState;
    private ArrayList<AttendeceListModel> dataSet;
    private Context mContext;

    public CustomAdapter( Context context, ArrayList<AttendeceListModel> data) {
        this.dataSet = data;
        this.mContext = context;
        checkBoxState = new boolean[data.size()];
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public AttendeceListModel getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ogrenci_bilgi_satir, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /**
         * dataları her satır için yerleştiriyore
         */
         AttendeceListModel item = getItem(position);
        viewHolder.txtName.setText(item.getName());
        viewHolder.checkBox.setChecked(item.isChecked());
        viewHolder.checkBox.setTag(position);

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    checkBoxState[position] = true;
                    dataSet.get(position).setChecked(true);
                }else{
                    checkBoxState[position] = false;
                    dataSet.get(position).setChecked(false);
                }
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView txtName;
        CheckBox checkBox;
    }
}

