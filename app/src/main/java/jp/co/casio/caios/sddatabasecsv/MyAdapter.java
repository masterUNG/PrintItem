package jp.co.casio.caios.sddatabasecsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by masterUNG on 12/11/15 AD.
 */
public class MyAdapter extends BaseAdapter{

    //Explicit
    private Context objContext;
    private String[] itemNameStrings, qtyStrings, amtStrings;

    public MyAdapter(Context objContext, String[] itemNameStrings, String[] qtyStrings, String[] amtStrings) {
        this.objContext = objContext;
        this.itemNameStrings = itemNameStrings;
        this.qtyStrings = qtyStrings;
        this.amtStrings = amtStrings;
    }   // Constructor

    @Override
    public int getCount() {
        return itemNameStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.my_listview, viewGroup, false);

        //For ITEMNAME
        TextView itemNameTextView = (TextView) objView1.findViewById(R.id.textView);
        itemNameTextView.setText(itemNameStrings[i]);

        //For QTY
        TextView qtyTextView = (TextView) objView1.findViewById(R.id.textView2);
        qtyTextView.setText(qtyStrings[i]);

        //For AMT
        TextView amtTextView = (TextView) objView1.findViewById(R.id.textView3);
        amtTextView.setText(amtStrings[i]);

        return objView1;
    }
}   // Main Class










