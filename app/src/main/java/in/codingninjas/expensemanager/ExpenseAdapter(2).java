package in.codingninjas.expensemanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by manishakhattar on 12/02/17.
 */

public class ExpenseAdapter extends ArrayAdapter<Expense> {

    Context mContext;
    ArrayList<Expense> mExpenses;

    /**
     * Constructor
     *
     * @param context  The current context.
     *
     * @param objects  The objects to represent in the ListView.
     */
    public ExpenseAdapter(Context context, ArrayList<Expense> objects) {
        super(context,0, objects);
        mContext = context;
        mExpenses = objects;
    }

    static class ExpenseViewHolder{
        TextView titleTextView;
        TextView categoryTextView;
        ExpenseViewHolder(TextView titleTextView,TextView categoryTextView){
            this.titleTextView = titleTextView;
            this.categoryTextView = categoryTextView;
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(mContext,R.layout.list_item,null);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.listItemTextView);
            TextView categoryTextView = (TextView) convertView.findViewById(R.id.listItemCategoryTextView);
            ExpenseViewHolder holder = new ExpenseViewHolder(titleTextView, categoryTextView);
            convertView.setTag(holder);
        }
        ExpenseViewHolder holder = (ExpenseViewHolder)convertView.getTag();
        Expense expense = mExpenses.get(position);
        holder.titleTextView.setText(expense.title);
        holder.categoryTextView.setText(expense.category);
        return convertView;
    }


    @Nullable
    @Override
    public Expense getItem(int position) {
        return super.getItem(position);
    }


    @Override
    public int getCount() {
        return super.getCount();
    }
}
