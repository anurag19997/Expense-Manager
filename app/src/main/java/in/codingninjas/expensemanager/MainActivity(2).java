package in.codingninjas.expensemanager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Expense> expenses;
//    ArrayAdapter<String> adapter;
    ExpenseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expenses = new ArrayList<>();
        for(int i = 0; i < 20; i++){
//            expenses.add("Expense "+i);
            Expense e = new Expense("Expense "+i, "Food" , (i + 1));
            expenses.add(e);
        }
        ListView listView = (ListView) findViewById(R.id.expenseListView);
//        adapter = new ArrayAdapter<>(this,R.layout.list_item,R.id.listItemTextView,expenses);
       adapter = new ExpenseAdapter(this,expenses);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
//              ExpenseAdapter adapter = (ExpenseAdapter) parent.getAdapter();
//                Toast.makeText(MainActivity.this,adapter.getItem(position).title + " clicked ", Toast.LENGTH_SHORT).show();
//                Toast t = new Toast(MainActivity.this);
//                t.setView();
//                t.show();

                Intent i = new Intent();
                i.setClass(MainActivity.this, ExpenseActivity.class);
                i.putExtra("title",expenses.get(position).title);
//                startActivity(i);
                    startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode == 1){
           if(resultCode == Activity.RESULT_OK){
               String title = data.getStringExtra("title");
               Log.i("MainActivityTag", "Result Ok " + " title "+ title);
           }else if(resultCode == Activity.RESULT_CANCELED){
               Log.i("MainActivityTag", "Result Cancelled ");
           }
       }
    }

    @Override
    public void onBackPressed() {


        super.onBackPressed();
    }

    @Override
    public void finish() {

        super.finish();
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
//            expenses.add("Expense " + expenses.size());
//            adapter.notifyDataSetChanged();
        }else if(id == R.id.menuRemoveExpense){

            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Confirm ");
            View v = getLayoutInflater().inflate(R.layout.dialog_view,null);
            b.setView(v);
            b.setCancelable(false);
            TextView tv = (TextView) v.findViewById(R.id.dialogTextView);
            tv.setText("Are you sure you want to delete ? " + expenses.get(expenses.size() - 1));
// b.setMessage("Are you sure you want to delete ?");

//            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    if(which == Dialog.BUTTON_POSITIVE){
//                        expenses.remove(expenses.size() - 1);
//                        adapter.notifyDataSetChanged();
//                    }else if(which == Dialog.BUTTON_NEGATIVE){
//                        dialog.cancel();
//                }
//
//                }
//            };
//            b.setPositiveButton("Yes", listener);
//            b.setNegativeButton("No",listener);
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

            AlertDialog aD = b.create();
            aD.show();
        }else if(id == R.id.menuAboutUs){
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("http://www.codingninjas.in");
            i.setData(uri);
            startActivity(i);
        }else if(id == R.id.menuContactUs){
            Intent i = new Intent();
            i.setAction(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:12345678");
            i.setData(uri);
            startActivity(i);
        }else if(id == R.id.menuFeedback){
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:manisha@codingninjas.in"));
            i.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
//            i.putExtra(Intent.EXTRA_TEXT)
            if(i.resolveActivity(getPackageManager()) != null){
                startActivity(i);
            }else{
                Toast.makeText(MainActivity.this, "Unhandled", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }
}
