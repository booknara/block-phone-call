package com.github.booknara.blockphonecall.telephony;

@SuppressWarnings("unused")
public final class TelephonyStub {

    public void dial(String number) {}

    public void call(String callingPackage, String number) {}

    public boolean endCall() {
        return false;
    }

    public void answerRingingCall() {}

    public void silenceRinger() {}

    public boolean isOffhook() {
        return false;
    }

    public boolean isRinging() {
        return false;
    }

    public boolean isIdle() {
        return false;
    }

    public boolean isRadioOn() {
        return false;
    }
}
