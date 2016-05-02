package com.blue.frame.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;

import com.blue.frame.log.LogDebugUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author
 *         <p>
 *         <p>
 *         Copyright: Copyright (c) 2015
 * @version V1.0
 * @ClassName: ActivitiesManager
 * @Description: Activities 堆栈管理工具类
 * @date Jul 6, 2015 4:25:58 PM
 */
public class ActivitiesManager {

    private static final String TAG = "ActivitiesManager";

    private static Stack<Activity> mActivityStack;
    private static final String APP_NAME = "com.chinanetcenter.cloudtv";

    private ActivitiesManager() {
    }

    private static class AcvitityManagerHolder {
        private static final ActivitiesManager instance = new ActivitiesManager();
    }

    public static ActivitiesManager getActivitiesManager() {
        return AcvitityManagerHolder.instance;
    }

    /**
     * pushActivity()
     *
     * @return void 返回类型
     * @throws
     * @Title: pushActivity
     * @Description: 添加Activity到堆栈
     */
    public void pushActivity(Activity activity) {
        if (null == mActivityStack) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
        if (LogDebugUtil.isAppDebug) {
            LogDebugUtil.i(TAG, "pushActivity-->" + activity.getClass().getSimpleName());
        }
    }

    /**
     * getCurrentActivity()
     *
     * @return Activity 返回类型
     * @throws
     * @Title: getCurrentActivity
     * @Description: 获取当前Activity（堆栈中最后一个压力的）
     */
    public Activity getCurrentActivity() {
        Activity activity = null;
        try {
            activity = mActivityStack.lastElement();
        } catch (Exception e) {
            return null;
        }
        return activity;
    }

    /**
     * @return void
     * @throws
     * @Title: popActivity
     * @Description: 移除当前Activity（堆栈中最后一个压入的）
     */
    public void popActivity() {
        Activity activity = mActivityStack.lastElement();
        if (null != activity) {
            popActivity(activity);
        }
    }

    /**
     * @return void
     * @throws
     * @Title: popActivity
     * @Description: 移除指定的Activity
     */
    public void popActivity(Activity activity) {
        if (LogDebugUtil.isAppDebug) {
            LogDebugUtil.i(TAG, "popActivity-->" + activity.getClass().getSimpleName());
        }
        activity.finish();
        mActivityStack.remove(activity);
        activity = null;
    }

    /**
     * @return void
     * @throws
     * @Title: popAllActivities
     * @Description: 移除所有Activities
     */
    public void popAllActivities() {
        while (!mActivityStack.isEmpty()) {
            Activity activity = getCurrentActivity();
            if (null == activity) {
                break;
            }
            popActivity(activity);
        }
        mActivityStack.clear();
    }

    /**
     * @return void
     * @throws
     * @Title: popSpecialActivity
     * @Description: 移除指定的Activity
     */
    public void popSpecialActivity(Class<?> cls) {
        try {
            Iterator<Activity> iterator = mActivityStack.iterator();
            Activity activity = null;
            while (iterator.hasNext()) {
                activity = iterator.next();
                if (null == activity) {
                    break;
                }
                if (activity.getClass().equals(cls)) {
                    popActivity(activity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @throws
     * @Title: popSpecialActivity
     * @Description: 移除指定的Activity
     */
    public Activity getActivity(Class<?> cls) {
        Activity activity = null;
        try {
            Iterator<Activity> iterator = mActivityStack.iterator();
            while (iterator.hasNext()) {
                activity = iterator.next();
                if (null == activity) {
                    break;
                }
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity;
    }

    /**
     * @return int
     * @throws
     * @Title: getStackSize
     * @Description: 获取堆栈大小
     */
    public int getStackSize() {
        return mActivityStack.size();
    }

    /**
     * @return void
     * @throws
     * @Title: peekActivity
     * @Description: 打印Activity堆栈信息
     */
    public void peekActivity() {
        for (Activity activity : mActivityStack) {
            if (null == activity) {
                break;
            }
            LogDebugUtil.i(TAG, "peekActivity()-->" + activity.getClass().getSimpleName());
        }
    }

    /**
     * @return void
     * @throws
     * @Title: Exit
     * @Description: 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void Exit(Context context, Boolean isBackground) {
        try {
            popAllActivities();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 注意，如果您有后台程序运行，请不要支持此句子
            if (!isBackground) {
                System.exit(0);
            }
        }
    }

    public static Drawable getAppIcon(Context mContext, String packageName)
            throws NameNotFoundException {
        PackageManager manager = mContext.getPackageManager();
        Drawable drawable = manager.getApplicationIcon(packageName);
        return drawable;
    }

    public static String getTopActivityName(Context context) {
        String topActivityClassName = null;
        ActivityManager activityManager = (ActivityManager) (context
                .getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningTaskInfo> runningTaskInfos = activityManager
                .getRunningTasks(1);
        if (runningTaskInfos != null) {
            ComponentName f = runningTaskInfos.get(0).topActivity;
            topActivityClassName = f.getPackageName();
        }
        return topActivityClassName;
    }

    public static boolean isTopActivity(Context context) {
        if (APP_NAME.equals(getTopActivityName(context))) {
            return true;
        }
        return false;
    }
}
