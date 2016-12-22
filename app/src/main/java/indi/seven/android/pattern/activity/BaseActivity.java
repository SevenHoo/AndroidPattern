package indi.seven.android.pattern.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Description: TODO <br/>
 * Date: 2016/12/18 <br/>
 *
 * @author mr.hoo7793@gmail.com
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().push(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().pop(this);
    }
}
