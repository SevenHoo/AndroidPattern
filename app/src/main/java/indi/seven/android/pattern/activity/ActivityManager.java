package indi.seven.android.pattern.activity;

import android.app.Activity;
import android.util.Log;

import java.util.HashMap;

/**
 * Description: TODO <br/>
 * Date: 2016/12/18 <br/>
 *
 * @author mr.hoo7793@gmail.com
 */
public class ActivityManager {

    private static final String TAG = ActivityManager.class.getSimpleName();

    private HashMap<String, Activity> mActivityMap;

    private ActivityManager(){
        mActivityMap = new HashMap<>();
    }
    private static class InstanceHolder{
        private final static ActivityManager INSTANCE = new ActivityManager();
    }

    public static ActivityManager getInstance(){
        return InstanceHolder.INSTANCE;
    }


    /**
     * add the activity with the specified name to the manager.
     * @param name
     * @param activity
     */
    public void addActivity(String name, Activity activity) {

        if (!mActivityMap.containsKey(name)) {

            if(!mActivityMap.containsValue(activity)) {
                mActivityMap.put(name, activity);
            }else {
                Log.e(TAG,"already added the same activity " + activity);
            }

        } else {
            Log.e(TAG,"already used the same name " + name);
        }

    }

    /**
     * get the activity with the specified name.
     * @param name
     * @return
     */
    public Activity getActivity(String name) {
        return mActivityMap.get(name);
    }


    /**
     * return true if the manager contains no activities.
     * @return true or false
     */
    public boolean isEmpty() {
        return mActivityMap.isEmpty();
    }

    /**
     * return the number of activities in the manager.
     * @return size
     */
    public int size() {
        return mActivityMap.size();
    }


    /**
     * Removes the activity with the specified name.
     * @param name  activity's name
     */
    public void removeActivity(String name) {

        Activity activity = mActivityMap.get(name);

        if (activity != null) {
            activity.finish();
            mActivityMap.remove(name);
        }else {
            Log.e(TAG,"the activity named " + name + " not found.");
        }
    }

    /**
     * finish all activities contained in the manager.
     */
    public void finishAll() {

        for (String name : mActivityMap.keySet()) {
            Activity activity = mActivityMap.get(name);
            if (!activity.isFinishing()) {
                activity.finish();
            }
            mActivityMap.remove(name);
        }
        //System.exit(0);
    }

}
