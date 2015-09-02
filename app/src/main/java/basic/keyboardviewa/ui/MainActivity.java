package basic.keyboardviewa.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import basic.keyboardviewa.R;
import basic.keyboardviewa.view.KeyBoardView;
import basic.keyboardviewa.view.TestDialog;

/**
 * Created by basic on 2015/8/29.
 */
public class MainActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button test = (Button) findViewById(R.id.test);
        test.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDialog dialog = TestDialog.newInstance();
                dialog.show(getSupportFragmentManager(), "tag");
                dialog.setOnKeyClickListener(new KeyBoardView.OnClickListener() {
                    @Override
                    public void onClick(int witch) {
                        Toast.makeText(getApplicationContext(), "witch" + witch, Toast
                                .LENGTH_SHORT).show();
								//
                    }
                });
            }
        });
    }


}
