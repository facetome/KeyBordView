package basic.keyboardviewa.ui;

import android.app.Activity;
import android.os.Bundle;

import basic.keyboardviewa.R;
import basic.keyboardviewa.view.KeyBoardView;

/**
 * Created by basic on 2015/8/29.
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KeyBoardView view = new KeyBoardView(this);
        setContentView(R.layout.main);
    }
}
