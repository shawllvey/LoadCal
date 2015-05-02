package com.example.carpenshaw.loancal;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carpenshaw.util.StringUtils;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private EditText et_totalTurnover;
    private Spinner s_mortgageratio;
    private Spinner s_mortgageYears;
    private Spinner s_loanRate;
    private RadioGroup rg_payment;

    private void init() {
        et_totalTurnover = (EditText) this.findViewById(R.id.et_totalTurnover);
        s_mortgageratio = (Spinner) this.findViewById(R.id.s_mortgageratio);
        s_mortgageYears = (Spinner) this.findViewById(R.id.s_mortgageYears);
        s_loanRate = (Spinner) this.findViewById(R.id.s_loanRate);
        rg_payment = (RadioGroup) this.findViewById(R.id.rg_payment);
    }



    public void calc(View view) {
        String totalStr = et_totalTurnover.getText().toString();
        if (StringUtils.isBlank(totalStr)) {
            Toast.makeText(this, "成交总额", Toast.LENGTH_SHORT).show();
            et_totalTurnover.requestFocus();
            return;
        }

        double totalTurnover = Double.parseDouble(totalStr);
        double mortgageRatio = Double.parseDouble(s_mortgageratio.getSelectedItem().toString());
        double loadRate = Double.parseDouble(s_loanRate.getSelectedItem().toString());
        int mortgageYears = Integer.parseInt(s_mortgageYears.getSelectedItem().toString());

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.TOTALTURNOVER, totalTurnover);
        intent.putExtra(ResultActivity.MORTGAGERATIO, mortgageRatio);
        intent.putExtra(ResultActivity.LOANRATE, loadRate);
        intent.putExtra(ResultActivity.MORTGAGEYEARS, mortgageYears);
        this.startActivity(intent);

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
