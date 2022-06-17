package android.example.purse_manager;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button b,at;
    Spinner s;
    TextView c,o;
    ArrayAdapter<CharSequence> adapter;
    SharedPreferences srd;
    SharedPreferences.Editor editor;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = findViewById(R.id.spin);
        s.setOnItemSelectedListener(this);
        adapter = ArrayAdapter.createFromResource(this,R.array.months,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        c = findViewById(R.id.cash);
        o = findViewById(R.id.online);
        srd = getSharedPreferences("demo",MODE_PRIVATE);
        editor = srd.edit();
        b = findViewById(R.id.btnedt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), edit.class);
                startActivity(i);
            }

        });
        at = findViewById(R.id.adbtn);
        at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),transaction.class);
                startActivity(i);
            }
        });
        i = getIntent();


        if(i.getExtras()!=null)
        {
            setvalues();

        }








    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences s = getSharedPreferences("demo",MODE_PRIVATE);
        String oo = s.getString(adapterView.getItemAtPosition(i)+"online","0");
        String p = s.getString(adapterView.getItemAtPosition(i)+"purse","0");
        c.setText(p);
        o.setText(oo);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void setvalues()
    {
        String activity = i.getStringExtra("activity");
        if(activity.equals("edit"))
        {
            String month = i.getStringExtra("month");
            String om = i.getStringExtra("onlinemoney");
            String pm = i.getStringExtra("pursemoney");
            c.setText(pm);
            o.setText(om);
            int spinnerposition = adapter.getPosition(month);
            s.setSelection(spinnerposition);
            editor.putString(month+"online",om);
            editor.putString(month+"purse",pm);
            editor.apply();
        }
        else if(activity.equals("transaction"))
        {
            String mode = i.getStringExtra("mode");
            String amount = i.getStringExtra("amount");
            String tran = i.getStringExtra("transaction");
            String month = s.getSelectedItem().toString();
            String onlinamt = srd.getString(month+"online","0");
            String purseamt = srd.getString(month+"purse","0");



            if(tran.equals("debit"))
            {

                if(mode.equals("online"))
                {
                    int temp = Integer.parseInt(onlinamt);
                    temp = temp - Integer.parseInt(amount);
                    onlinamt=String.valueOf(temp);
                    o.setText(onlinamt);
                    editor.putString(month+"online",onlinamt);
                    editor.apply();

                }
                else
                {
                    int temp = Integer.parseInt(purseamt);
                    temp = temp - Integer.parseInt(amount);
                    purseamt=String.valueOf(temp);
                    c.setText(purseamt);
                    editor.putString(month+"purse",purseamt);
                    editor.apply();

                }
            }
            else
            {

                if(mode.equals("online"))
                {
                    int temp = Integer.parseInt(onlinamt);
                    temp = temp + Integer.parseInt(amount);
                    onlinamt=String.valueOf(temp);
                    o.setText(onlinamt);
                    editor.putString(month+"online",onlinamt);
                    editor.apply();

                }
                else
                {
                    int temp = Integer.parseInt(purseamt);
                    temp = temp + Integer.parseInt(amount);
                    purseamt=String.valueOf(temp);
                    c.setText(purseamt);
                    editor.putString(month+"purse",purseamt);
                    editor.apply();

                }

            }
       }

    }
}
