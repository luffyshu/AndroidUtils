package com.luffy.androidutils.library;

import android.app.Activity;

import java.util.Stack;

/**
 * public class BaseActivity extends AppCompatActivity {
 * private ActivityManagerUtil activityManagerUtil;
 * protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.activity_base);
 * activityManagerUtil = ActivityManagerUtil.getInstance();
 * activityManagerUtil.pushOneActivity(this);
 * }
 * protected void onDestroy() {
 * super.onDestroy();
 * activityManagerUtil.popOneActivity(this);
 * }
 * }
 */
public class ActivityManagerUtils {

    private static ActivityManagerUtils instance;

    private Stack<Activity> activityStack = new Stack<>();

    public static ActivityManagerUtils getInstance() {
        if (instance == null) {
            instance = new ActivityManagerUtils();
        }
        return instance;
    }

    /**
     * 将activity压入栈中
     *
     * @param actvity activity
     */
    public void pushOneActivity(Activity actvity) {
        activityStack.add(actvity);
    }

    /**
     * 移除一个activity
     *
     * @param activity activity
     */
    public void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activityStack.remove(activity);
                activity.finish();
            }
        }
    }

    /**
     * 获取栈顶的activity，先进后出原则
     *
     * @return 栈顶的activity
     */
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    /**
     * 关闭指定的Activity
     *
     * @param activity activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 关闭activity
     */
    public void finishAllActivity() {
        try {
            for (int i = 0; i < activityStack.size(); i++) {
                if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishAllActivity();
            //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            System.exit(0);
            //从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
