package indi.seven.android.pattern.activity;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * Description: Manage all activities in the application. <br/>
 * Date: 2016/12/18 <br/>
 *
 * @author mr.hoo7793@gmail.com
 */
public class ActivityManager {

    private static final String TAG = ActivityManager.class.getSimpleName();

    private Stack<Activity> mActivityStack;

    private ActivityManager(){
        mActivityStack = new Stack<>();
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
        if(activity != null){
            int index =  mActivityStack.search(activity);
            return (index != -1);
        }
        return false;
    }

    /**
     * check whether the activity is at the top of manager.
     * @param activity
     * @return
     */
    public boolean isTop(Activity activity){
        if(activity != null){
            if(!mActivityStack.isEmpty()){
                Activity top = mActivityStack.peek();
                return (activity == top);
            }
        }
        return false;
    }


    /**
     * add the specified activity to the manager.
     * @param activity the specified activity
     */
    public void push(Activity activity) {

        if(activity != null){
            if(!isExist(activity)){
                mActivityStack.push(activity);
            } else {
                Log.e(TAG,"already added the same activity " + activity);
            }
        }

        else {
            Log.e(TAG,"activity is null");
        }

    }

    /**
     * get the activity with the specified class without removing it.
     * @param cls the specified class.
     * @return the activity with the specified class.
     */
    public Activity getActivity(Class<?> cls) {
        int size = mActivityStack.size();
        for(int i = 0; i < size; i++) {
            Activity activity = mActivityStack.get(i);
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
        Activity targetActivity = this.getActivity(cls);
        if (targetActivity != null){
            Activity activity = mActivityStack.peek();
            while (activity != targetActivity){
                activity.finish();
                mActivityStack.pop();
                activity = mActivityStack.peek();
            }
        }else {
            Log.d(TAG,cls.getSimpleName() + " instance not found in the manager.");
        }
    }


    /**
     * return true if the manager contains no activities.
     * @return true or false
     */
    public boolean isEmpty() {
        return mActivityStack.isEmpty();
    }

    /**
     * return the number of activities in the manager.
     * @return size
     */
    public int size() {
        return mActivityStack.size();
    }


    /**
     * Removes the specified activity if it is at the top.
     */
    public void pop(Activity activity) {
        if(activity != null){
            if(isTop(activity)){
                mActivityStack.pop();
            } else {
                Log.e(TAG,"activity not on the top");
            }
        }
        else {
            Log.e(TAG,"activity is null");
        }
    }

    /**
     * finish all activities contained in the manager.
     */
    public void clear() {
        int size = mActivityStack.size();
        for (int i = 0; i < size; i++) {
            Activity activity = mActivityStack.pop();
            activity.finish();
        }
    }

}
