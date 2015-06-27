package com.github.booknara.blockphonecall.telephony;


import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

public final class TelephonyHelper {
    private static final String TAG = TelephonyHelper.class.getName();

	public static /*com.android.internal.telephony.ITelephony*/ Object getITelephony(Context context) {
		TelephonyManager telephonymanager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

		try {
			Method method = Class.forName(telephonymanager.getClass().getName()).getDeclaredMethod("getITelephony");
			method.setAccessible(true);
			return /*(com.android.internal.telephony.ITelephony)*/ method.invoke(telephonymanager);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return new TelephonyStub();
	}

    public static boolean tryEndCall(Context context) {

        try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

                return invokeBooleanMethod(telephonyManager, telephonyManager.getClass().getName(), "endCall");
			} else {
                /*com.android.internal.telephony.ITelephony*/ Object iTelephony = getITelephony(context);

				return invokeBooleanMethod(iTelephony, "com.android.internal.telephony.ITelephony", "endCall");
			}
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return false;
    }

    private static boolean invokeBooleanMethod(Object receiver, String className, String methodName, Object... args) {

        try {
            Method method = Class.forName(className).getDeclaredMethod(methodName);
            method.setAccessible(true);
            return (boolean) method.invoke(receiver, args);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return false;
    }
}
