package com.renard.rjnetworkdemo.Fragment.news.list;

import com.orhanobut.logger.Logger;
import com.renard.rjnetwork.Base.BasePresenter;
import com.renard.rjnetworkdemo.adapter.item.NewsMultiItem;
import com.renard.rjnetworkdemo.api.NewsUtils;
import com.renard.rjnetworkdemo.api.RetrofitService;
import com.renard.rjnetworkdemo.api.bean.NewsInfo;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by Riven_rabbit on 2018/11/29
 */
public class NewsListPresenter implements BasePresenter {
    private NewsListView mView;
    private String mNewsId;

    private int mPage = 0;

    public NewsListPresenter(NewsListView view, String newsId) {
        this.mView = view;
        this.mNewsId = newsId;
    }

    @Override
    public void getData(final boolean isRefresh) {
        RetrofitService.getNewsList(mNewsId, mPage)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (!isRefresh) {
                            mView.showLoading();
                        }
                    }
                })
                .filter(new Func1<NewsInfo, Boolean>() {
                    @Override
                    public Boolean call(NewsInfo newsBean) {
                        if (NewsUtils.isAbNews(newsBean)) {
                            mView.loadAdData(newsBean);
                        }
                        return !NewsUtils.isAbNews(newsBean);
                    }
                })
                .compose(mTransformer)
                .subscribe(new Subscriber<List<NewsMultiItem>>() {
                    @Override
                    public void onCompleted() {
                        Logger.w("onCompleted " + isRefresh);
                        if (isRefresh) {
                            mView.finishRefresh();
                        } else {
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString() + " " + isRefresh);
                        if (isRefresh) {
                            mView.finishRefresh();
                            // 可以提示对应的信息，但不更新界面
//                            ToastUtils.showToast("刷新失败提示什么根据实际情况");
                        } else {
                            mView.showNetError();
                        }
                    }

                    @Override
                    public void onNext(List<NewsMultiItem> newsMultiItems) {
                        mView.loadData(newsMultiItems);
                        mPage++;
                    }
                });
    }

    @Override
    public void getMoreData() {
        RetrofitService.getNewsList(mNewsId, mPage)
                .compose(mTransformer)
                .subscribe(new Subscriber<List<NewsMultiItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.toString());
                        mView.loadNoData();
                    }

                    @Override
                    public void onNext(List<NewsMultiItem> newsList) {
                        mView.loadMoreData(newsList);
                        mPage++;
                    }
                });
    }

    /**
     * 统一变换
     */
    private Observable.Transformer<NewsInfo, List<NewsMultiItem>> mTransformer = new Observable.Transformer<NewsInfo, List<NewsMultiItem>>() {
        @Override
        public Observable<List<NewsMultiItem>> call(Observable<NewsInfo> newsInfoObservable) {
            return newsInfoObservable
                    .map(new Func1<NewsInfo, NewsMultiItem>() {
                        @Override
                        public NewsMultiItem call(NewsInfo newsBean) {
                            if (NewsUtils.isNewsPhotoSet(newsBean.getSkipType())) {
                                return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_PHOTO_SET, newsBean);
                            }
                            return new NewsMultiItem(NewsMultiItem.ITEM_TYPE_NORMAL, newsBean);
                        }
                    })
                    .toList()
                    .compose(mView.<List<NewsMultiItem>>bindToLife());
        }
    };
}
