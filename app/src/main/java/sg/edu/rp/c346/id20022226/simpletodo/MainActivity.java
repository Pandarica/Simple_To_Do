package sg.edu.rp.c346.id20022226.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button bt1;
    Button bt2;
    Button bt3;
    ListView lv;
    Spinner sp;
    ArrayList<String> phrase;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        lv = findViewById(R.id.lv);
        sp = findViewById(R.id.sp);

        phrase = new ArrayList<String>();

        adapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, phrase);

        lv.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        bt1.setEnabled(true);
                        bt2.setEnabled(false);
                        et.setHint("Type in new task here");
                        break;
                    case 1:
                        bt1.setEnabled(false);
                        bt2.setEnabled(true);
                        et.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et.getText().toString())) {
                    Toast.makeText(MainActivity.this, "You did not input any task", Toast.LENGTH_SHORT).show();
                } else {
                    String phrase2 = et.getText().toString();
                    phrase.add(phrase2);
                    adapter.notifyDataSetChanged();
                    et.setText("");
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et.getText().toString())) {
                    Toast.makeText(MainActivity.this, "You did not input any index number", Toast.LENGTH_SHORT).show();
                } else {
                    int number = Integer.parseInt(et.getText().toString());
                    if (phrase.size() == 0) {
                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    } else if (number > phrase.size() - 1) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    } else {
                        phrase.remove(number);
                        et.setText("");
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phrase.removeAll(phrase);
                adapter.notifyDataSetChanged();
            }
        });
    }
}