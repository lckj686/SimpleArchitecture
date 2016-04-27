package com.simple.architecture.frame.log.crash;

import android.content.Context;

import com.simple.architecture.frame.log.log4.LoggerUtil;
import com.simple.architecture.frame.utils.ActivitiesManager;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 自定义的 异常处理类 , 实现了 UncaughtExceptionHandler接口
 */
public class CustCrashHandler implements UncaughtExceptionHandler {

	private static CustCrashHandler crashHandler;
	private Context context;

	private CustCrashHandler() {
	}

	public static synchronized CustCrashHandler getInstance() {
		if (crashHandler != null) {
			return crashHandler;
		} else {
			crashHandler = new CustCrashHandler();
			return crashHandler;
		}
	}

	public void init(Context context) {
		this.context = context;
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	// 发生异常时，需要把activity栈清空。
	public void uncaughtException(Thread arg0, Throwable throwable) {
		LoggerUtil.f("应用异常退出", throwable);


//		ActivityStack.getActivityStack().clearActivity();
		ActivitiesManager.getActivitiesManager().popAllActivities();
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

}
