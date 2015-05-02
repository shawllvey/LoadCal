package com.example.carpenshaw.loancal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ResultActivity extends ActionBarActivity {

    public static final String TOTALTURNOVER = "totalTurnOver";
    public static final String MORTGAGERATIO = "mortgageRatio";
    public static final String LOANRATE = "loadRate";
    public static final String MORTGAGEYEARS = "mortgageYears";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        init();

        Bundle bundle = this.getIntent().getExtras();

        double totalTurnover = bundle.getDouble(TOTALTURNOVER);
        double mortgageRatio = bundle.getDouble(MORTGAGERATIO);
        double loanRate = bundle.getDouble(LOANRATE);
        int mortgageYears = bundle.getInt(MORTGAGEYEARS);



        double firstPay = totalTurnover*(1-mortgageRatio);
        double loan = totalTurnover*mortgageRatio;

        double monthlyLoadRate = loanRate/12;
        int totalPayMonths = mortgageYears*12;

        // [贷款本金×月利率×（1+月利率）^还款月数]÷[（1+月利率）^还款月数－1]
        double a = loan*monthlyLoadRate*Math.pow(1+monthlyLoadRate, totalPayMonths);
        double b = Math.pow(1+monthlyLoadRate, totalPayMonths)-1;
        double monthlyPay = a/b;

        tv_totalTurnOver.setText("" + totalTurnover);
        tv_firstPay.setText("" + firstPay);
        tv_load.setText("" + loan);
        tv_loanRate.setText("" + loanRate);
        tv_monthlyLoanRate.setText("" + monthlyLoadRate);
        tv_totalPayMonths.setText("" + totalPayMonths);
        tv_monthlyPay.setText("" + monthlyPay);
    }

    private TextView tv_totalTurnOver;
    private TextView tv_firstPay;
    private TextView tv_load;
    private TextView tv_loanRate;
    private TextView tv_monthlyLoanRate;
    private TextView tv_totalPayMonths;
    private TextView tv_monthlyPay;
    private void init() {
        tv_totalTurnOver = (TextView)this.findViewById(R.id.tv_totalTurnOver);
        tv_firstPay = (TextView)this.findViewById(R.id.tv_firstPay);
        tv_load = (TextView)this.findViewById(R.id.tv_loan);
        tv_loanRate = (TextView)this.findViewById(R.id.tv_loanRate);
        tv_monthlyLoanRate = (TextView)this.findViewById(R.id.tv_monthlyLoanRate);
        tv_totalPayMonths = (TextView)this.findViewById(R.id.tv_totalPayMonths);
        tv_monthlyPay = (TextView)this.findViewById(R.id.tv_monthlyPay);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
