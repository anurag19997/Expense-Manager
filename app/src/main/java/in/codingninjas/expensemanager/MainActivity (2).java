package in.codingninjas.expensemanager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> expenses;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expenses = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            expenses.add("Expense "+i);
        }
        ListView listView = (ListView) findViewById(R.id.expenseListView);
        adapter = new ArrayAdapter<>(this,R.layout.list_item,R.id.listItemTextView,expenses);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
                Toast.makeText(MainActivity.this,  adapter.getItem(position) + " clicked ", Toast.LENGTH_SHORT).show();
//                Toast t = new Toast(MainActivity.this);
//                t.setView();
//                t.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menuAddExpense){
            expenses.add("Expense " + expenses.size());
            adapter.notifyDataSetChanged();
        }else if(id == R.id.menuRemoveExpense){

            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Confirm ");
            View v = getLayoutInflater().inflate(R.layout.dialog_view,null);
            b.setView(v);
            b.setCancelable(false);
            TextView tv = (TextView) v.findViewById(R.id.dialogTextView);
            tv.setText("Are you sure you want to delete ? " + expenses.get(expenses.size() - 1));
// b.setMessage("Are you sure you want to delete ?");
            b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    expenses.remove(expenses.size() - 1);
                    adapter.notifyDataSetChanged();
                }
            });
            b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            b.create().show();


        }
        return true;
    }
}
