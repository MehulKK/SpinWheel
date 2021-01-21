package com.macv.mwheel;


public interface WheelListener {
    void onOpen();
    void onClose();
    void onReward(int pos);
    void onStart();
    void onError();
}
