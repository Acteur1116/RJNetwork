package com.renard.rjnetworkdemo.Fragment.channel;


/**
 * Created by long on 2016/12/15.
 * 频道 Presenter 接口
 */
public interface IChannelPresenter<T> extends LocalPresenter<T> {

    /**
     * 交换
     * @param fromPos
     * @param toPos
     */
    void swap(int fromPos, int toPos);
}
