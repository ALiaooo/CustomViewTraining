package com.aliao.cvtraining;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.aliao.cvtraining.utils.Constants;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        findViewById(R.id.btn_canvas).setOnClickListener(this);
        findViewById(R.id.btn_aige_circular_ring).setOnClickListener(this);
        findViewById(R.id.btn_aige_color_filter).setOnClickListener(this);
        findViewById(R.id.btn_poter_duff_view).setOnClickListener(this);
        findViewById(R.id.btn_beauty_poter_duff_view).setOnClickListener(this);
        findViewById(R.id.btn_eraser_view).setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, AigeActivity.class);
        switch (v.getId()){
            case R.id.btn_canvas:
                startActivity(new Intent(MainActivity.this, CanvasActivity.class));
                break;
            case R.id.btn_aige_circular_ring:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.CIRCULAR_TING_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_aige_color_filter:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.COLOR_FILTER_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_poter_duff_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.POTER_DUFF_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_beauty_poter_duff_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.BEAUTY_POTER_DUFF_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_eraser_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.ERASER_VIEW_INTEX);
                startActivity(intent);
                break;
        }
    }
}
