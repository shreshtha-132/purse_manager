package android.example.purse_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class transaction extends AppCompatActivity {
    EditText amt;
    RadioButton r1,r2;
    RadioGroup rg1,rg2;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction2);
        b = findViewById(R.id.done);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rg1 = findViewById(R.id.rg1);
                rg2 = findViewById(R.id.rg2);
                int id = rg1.getCheckedRadioButtonId();
                r1 = findViewById(id);
                int id2 = rg2.getCheckedRadioButtonId();
                r2 = findViewById(id2);
                amt = findViewById(R.id.amt);
                String amount = amt.getText().toString();
                String mode = r1.getText().toString();
                String transaction = r2.getText().toString();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("amount",amount);
                i.putExtra("mode",mode);
                i.putExtra("transaction",transaction);
                i.putExtra("activity","transaction");
                startActivity(i);

            }
        });
    }
}