package in.codingninjas.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExpenseActivity extends AppCompatActivity {

    Button button;
    EditText titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        button = (Button) findViewById(R.id.submitButton);
        titleText = (EditText) findViewById(R.id.titleEditText);

        Intent i = getIntent();
        String title = i.getStringExtra("title");

        if(title != null && title.length() > 0){
            titleText.setText(title);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = titleText.getText().toString();
                Intent i = new Intent();
                i.putExtra("title", text);
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        });
    }
}
