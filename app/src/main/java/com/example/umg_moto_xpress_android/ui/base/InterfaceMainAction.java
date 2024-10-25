package com.example.umg_moto_xpress_android.ui.base;

import android.view.View;


public interface InterfaceMainAction {
    void hiddenShowNavbar(boolean isHidden);
    void loadingShow(boolean isShow);
    void logout(View.OnClickListener listener);
}
