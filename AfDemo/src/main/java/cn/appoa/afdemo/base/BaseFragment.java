package cn.appoa.afdemo.base;


import cn.appoa.afbase.fragment.AfFragment;
import cn.appoa.afbase.presenter.BasePresenter;

/**
 * Fragment基类
 *
 * @param <P>
 */
public abstract class BaseFragment<P extends BasePresenter> extends AfFragment<P> {

}
