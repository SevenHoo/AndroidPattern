package indi.seven.android.pattern.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Description: TODO <br/>
 * Date: 2016/12/18 <br/>
 *
 * @author mr.hoo7793@gmail.com
 */
public abstract class BaseActivity extends Activity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().remove(this);
        Log.d(TAG,this.getClass().getSimpleName() + " onDestroy.");
    }
}
