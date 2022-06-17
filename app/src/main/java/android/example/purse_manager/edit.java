package android.example.purse_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class edit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String month,om,pm;
    EditText p,o;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Spinner s = findViewById(R.id.spin);
        s.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,R.array.months,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        p = findViewById(R.id.pamt);
        o = findViewById(R.id.oamt);
        b = findViewById(R.id.done);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                om = o.getText().toString();
                pm = p.getText().toString();
                call();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        month = adapterView.getItemAtPosition(i).toString();


    }

    public void call() {
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("month",month);
        i.putExtra("pursemoney",pm);
        i.putExtra("onlinemoney",om);
        i.putExtra("activity","edit");
        startActivity(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}