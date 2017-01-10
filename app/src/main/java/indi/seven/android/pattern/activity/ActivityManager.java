package indi.seven.android.pattern.activity;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Manage all activities in the application. <br/>
 * Date: 2016/12/18 <br/>
 *
 * @author mr.hoo7793@gmail.com
 */
public class ActivityManager {

    private static final String TAG = ActivityManager.class.getSimpleName();

    private List<Activity> mActivityList;

    private ActivityManager(){
        mActivityList = new ArrayList<>();
    }
    private static class InstanceHolder{
        private final static ActivityManager INSTANCE = new ActivityManager();
    }

    public static ActivityManager getInstance(){
        return InstanceHolder.INSTANCE;
    }

    /**
     * check whether the activity exists in the manager.
     * @param activity
     * @return
     */
    public boolean isExist(Activity activity){

        if(activity == null){
            return false;
        }

        for(int i = 0; i < mActivityList.size(); i ++){
            Activity element = mActivityList.get(i);
            if(element == activity){
                return true;
            }
        }

        return false;

    }

    /**
     * check whether the activity is at the top of manager.
     * @param activity
     * @return
     */
    public boolean isTop(Activity activity){

        if(activity == null || mActivityList.isEmpty()){
            return false;
        }

        int lastIndex = mActivityList.size() - 1;
        Activity top = mActivityList.get(lastIndex);
        return (top == activity);
    }


    /**
     * add the specified activity to the manager.
     * @param activity the specified activity
     */
    public void add(Activity activity) {

        if(activity == null){
            Log.e(TAG,"activity is null");
            return;
        }

        if(!isExist(activity)){
            mActivityList.add(activity);
        }

        else {
            Log.e(TAG,"already added the same activity " + activity);
        }
    }

    /**
     * get the activity with the specified class without removing it.
     * @param cls the specified class.
     * @return the activity with the specified class.
     */
    public Activity getActivity(Class<?> cls) {

        int size = mActivityList.size();
        for(int i = 0; i < size; i++) {
            Activity activity = mActivityList.get(i);
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * start activity with the specified class which is held by the manager.
     * @param cls the specified class.
     */
    public void goToActivity(Class<?> cls){

        Activity target = this.getActivity(cls);
        if(target == null){
            Log.d(TAG,cls.getSimpleName() + " instance not found in the manager.");
            return;
        }

        for(int i = mActivityList.size(); i > 0; i--){
            Activity activity = mActivityList.get(i - 1);
            if(activity == target){
               return;
            }
            activity.finish(); //onDestroy of BaseActivity will remove it.
        }

    }

    /**
     * return true if the manager contains no activities.
     * @return true or false
     */
    public boolean isEmpty() {
        return mActivityList.isEmpty();
    }

    /**
     * return the number of activities in the manager.
     * @return size
     */
    public int size() {
        return mActivityList.size();
    }


    /**
     * Removes the specified activity if it is at the top.
     */
    public void remove(Activity activity) {

        if (activity == null){
            Log.e(TAG,"activity is null");
            return;
        }

        if(isTop(activity)){
            mActivityList.remove(mActivityList.size() - 1);
        }

        else {
            Log.e(TAG,"activity not on the top");
        }
    }

    /**
     * finish all activities contained in the manager.
     */
    public void clear() {

        int size = mActivityList.size();
        for (int i = size; i > 0; i--) {
            Activity activity = mActivityList.get(i - 1);
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        //System.exit(0);
    }

}
