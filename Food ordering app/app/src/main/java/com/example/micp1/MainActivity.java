package com.example.foodorderingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public String orderedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void add(View view) {
        CheckBox chkCapp = findViewById(R.id.chkCapp);
        CheckBox chkFlat = findViewById(R.id.chkFlat);
        CheckBox chkLatte = findViewById(R.id.chkLatte);
        Spinner txtCappQty= findViewById(R.id.txtCappQty);
        Spinner txtFlatQty= findViewById(R.id.txtFlatQty);
        Spinner txtLatteQty= findViewById(R.id.txtLatteQty);

        Boolean cappValue =chkCapp.isChecked();
        Boolean flatValue =chkFlat.isChecked();
        Boolean latteValue =chkLatte.isChecked();
        Integer cappQtyValue = 0;
        Integer flatQtyValue =0;
        Integer latteQtyValue=0;

            cappQtyValue = Integer.parseInt(String.valueOf(txtCappQty.getSelectedItem()));
            flatQtyValue =Integer.parseInt(String.valueOf(txtFlatQty.getSelectedItem()));
            latteQtyValue =Integer.parseInt(String.valueOf(txtLatteQty.getSelectedItem()));

            float total = calculate(cappValue,flatValue,latteValue,cappQtyValue,flatQtyValue,latteQtyValue);

        Intent redirect = new Intent(MainActivity.this, Order.class).putExtra("total",total+"").putExtra("orderedItems",orderedItems);
        startActivity(redirect);
    }

    public float calculate(Boolean cappValue,Boolean flatValue,Boolean latteValue,Integer cappQtyValue,Integer flatQtyValue,Integer latteQtyValue){
        orderedItems = "";
        float total =0;
        if(cappValue){
            total = total + (5 * cappQtyValue);
            orderedItems = orderedItems+" Mushrooms:"+cappQtyValue ;
        }
        if(flatValue){
            total = total + (10 * flatQtyValue);
            orderedItems = orderedItems+"\n Cheese:"+flatQtyValue;
        }
        if(latteValue){
            total = total + (20 * latteQtyValue);
            orderedItems = orderedItems+"\n olives:"+latteQtyValue;
        }
        return total;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
