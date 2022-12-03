package cn.appoa.afmvp;

import android.os.Bundle;

/**
 * 主持人基类
 */
public abstract class AfPresenter {

    public void onCreate(Bundle savedState) {

    }

    public abstract void onAttach(AfView view);

    public void onSaveInstanceState(Bundle outState) {
    }

    public abstract void onDetach();

    public void onDestroy() {

    }
}
