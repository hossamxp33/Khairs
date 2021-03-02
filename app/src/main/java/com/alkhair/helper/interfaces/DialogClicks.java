package com.alkhair.helper.interfaces;

/**
 * Created by Hossam on 11/19/2020.
 */
public interface DialogClicks {
    int okChose = 1;
    int cancelChose = 2;
    int third = 3;

    void onChose(int chose);
}
