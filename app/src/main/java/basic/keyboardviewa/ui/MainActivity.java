package basic.keyboardviewa.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import basic.keyboardviewa.R;
import basic.keyboardviewa.view.KeyBoardView;
import basic.keyboardviewa.view.KeyBoardView.OnClickListener;

/**
 * Created by basic on 2015/8/29.
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KeyBoardView view = new KeyBoardView(this);
        setContentView(R.layout.main);
        KeyBoardView keyBoardView = (KeyBoardView) findViewById(R.id.keyView);
        keyBoardView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(int witch) {
                Toast.makeText(MainActivity.this, "switch " + witch, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
