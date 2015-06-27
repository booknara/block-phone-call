package com.github.booknara.blockphonecall.telephony;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.github.booknara.blockphonecall.R;
import com.github.booknara.blockphonecall.util.PrefsUtil;

public final class BlockCallsTelephoneStateListener extends PhoneStateListener {
    private static final String TAG = BlockCallsTelephoneStateListener.class.getName();

    private final Context context;

    public BlockCallsTelephoneStateListener(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String number) {
        super.onCallStateChanged(state, number);

        boolean blockCall = PrefsUtil.getBoolean(context, R.string.pref_block_call);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                Log.i(TAG, "CALL_STATE_IDLE");
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                Log.i(TAG, "CALL_STATE_RINGING");
                if(!blockCall)
                    return;

                TelephonyHelper.tryEndCall(context);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                Log.i(TAG, "CALL_STATE_OFFHOOK");
                break;
            default:
                break;
        }
    }
}
