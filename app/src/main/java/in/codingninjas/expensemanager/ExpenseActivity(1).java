package in.codingninjas.expensemanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.activeandroid.query.Select;

import java.sql.Date;
import java.util.Calendar;


public class ExpenseActivity extends AppCompatActivity {

    Button button;
    EditText titleEditText;
    EditText categoryEditText;
    EditText priceEditText;
    EditText quantityEditText;
    EditText dateEditText;
    long id;
    long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        button = (Button) findViewById(R.id.submitButton);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        categoryEditText = (EditText) findViewById(R.id.categoryEditText);
        quantityEditText = (EditText) findViewById(R.id.quantityEditText);
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        dateEditText = (EditText) findViewById(R.id.expenseDateEditText);

        Intent i = getIntent();
         id = i.getLongExtra(MainActivity.ID, -1);
        Log.i("ExpenseActivityTag", "id = " + id);

        if(id !=  -1){
                populateViews();
            }

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                int month = newCalendar.get(Calendar.MONTH);
                int year = newCalendar.get(Calendar.YEAR);
                showDatePicker(ExpenseActivity.this, year, month, 1);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String category = categoryEditText.getText().toString();
                String price = priceEditText.getText().toString();
                String quantity = quantityEditText.getText().toString();

                if(title.trim().isEmpty()){
                    titleEditText.setError("Title can't be empty ");
                    return;
                }
                if(category.trim().isEmpty()){
                    categoryEditText.setError("Category can't be empty ");
                    return;
                }

                if(price.trim().isEmpty()){
                    priceEditText.setError("Price can't be empty ");
                    return;
                }
                if(quantity.trim().isEmpty()){
                    quantityEditText.setError("Quantity can't be empty ");
                    return;
                }


                if (date == 0.0) {
                    date = new Date(System.currentTimeMillis()).getTime();
                }

//                ExpenseHelper expenseHelper = new ExpenseHelper(ExpenseActivity.this);
//                SQLiteDatabase db = expenseHelper.getWritableDatabase();
//                ContentValues cv = new ContentValues();
//                cv.put(ExpenseHelper.EXPENSE_TABLE_COLUMN_TITLE, title);
//                cv.put(ExpenseHelper.EXPENSE_TABLE_COLUMN_CATEGORY, category);
//                cv.put(ExpenseHelper.EXPENSE_TABLE_COLUMN_PRICE, Double.parseDouble(price));
//                cv.put(ExpenseHelper.EXPENSE_TABLE_COLUMN_QUANTITY, Double.parseDouble(quantity));
//                db.insert(ExpenseHelper.EXPENSE_TABLE_NAME, null, cv);


                Expense expense = new Expense(title,category,Double.parseDouble(price),Double.parseDouble(quantity),date);
                expense.save();

                Intent i = new Intent();
//                i.putExtra("title", text);
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        });
    }

    public void showDatePicker(Context context, int initialYear, int initialMonth, int initialDay) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);
                        date = calendar.getTime().getTime();
                        dateEditText.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, initialYear, initialMonth, initialDay);

        datePickerDialog.show();

    }
    private void populateViews() {
        Log.i("ExpenseActivityTag", "populateViews");
        Expense expense = new Select().from(Expense.class).where("id = ?", id).executeSingle();
        titleEditText.setText(expense.title);
        categoryEditText.setText(expense.category);
        String d = new Date(expense.date).toString();
        dateEditText.setText(d);
        priceEditText.setText(expense.price + "");
        quantityEditText.setText(expense.quantity + "");

    }
}
