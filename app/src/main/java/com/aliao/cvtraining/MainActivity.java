package com.aliao.cvtraining;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.aliao.cvtraining.activity.CheckedViewActivity;
import com.aliao.cvtraining.activity.TouchEventActivity;
import com.aliao.cvtraining.utils.Constants;
import com.aliao.cvtraining.utils.L;
import com.aliao.cvtraining.view.widget.CheckedEditText;


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
        findViewById(R.id.btn_font_view).setOnClickListener(this);
        findViewById(R.id.btn_mask_filter_view).setOnClickListener(this);
        findViewById(R.id.btn_path_effect_view).setOnClickListener(this);
        findViewById(R.id.btn_ecg_view).setOnClickListener(this);
        findViewById(R.id.btn_shader_view).setOnClickListener(this);
        findViewById(R.id.btn_brick_view).setOnClickListener(this);
        findViewById(R.id.btn_reflect_view).setOnClickListener(this);
        findViewById(R.id.btn_dream_effect_view).setOnClickListener(this);
        findViewById(R.id.btn_matrix_view).setOnClickListener(this);
        findViewById(R.id.btn_cheked_view).setOnClickListener(this);
        findViewById(R.id.btn_touch).setOnClickListener(this);
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
            case R.id.btn_font_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.FONT_VIEW_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_mask_filter_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.MASK_FILTER_VIEW_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_path_effect_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.PATH_EFFECT_VIEW_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_ecg_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.ECG_VIEW_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_shader_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.SHADER_VIEW_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_brick_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.BRICK_VIEW_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_reflect_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.REFLECT_VIEW_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_dream_effect_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.DREAM_EFFECT_VIEW_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_matrix_view:
                intent.putExtra(Constants.FRAGMENT_INDEX, Constants.MATRIX_VIEW_INTEX);
                startActivity(intent);
                break;
            case R.id.btn_cheked_view:
                startActivity(new Intent(MainActivity.this, CheckedViewActivity.class));
                break;
            case R.id.btn_touch:
                startActivity(new Intent(MainActivity.this, TouchEventActivity.class));
                break;
        }
    }

}
