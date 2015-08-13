package com.lance.autoloadlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

/** 自动加载的ListView，和SwipeRefreshLayout配合效果更佳哦
 *  A customed ListView that could auto load more data,and it work well with the SwipeRefreshLayout
 * Created by Lance on 2015/8/2.
 * email: liangshicong@niot.cn
 */
public class AutoLoadListView extends ListView implements AbsListView.OnScrollListener{

    private IonLoadMore onLoadMore;
    private Context mContext;
    private ProgressBar progressBar;

    public interface IonLoadMore{
        public void onLoadMore();
    }

    /**
     * should call the method before setAdapter(),because of the footview should add before setAdapter()
     * @param onLoadMore
     * @param context
     */
    public void setOnLoadmoreListener(IonLoadMore onLoadMore,Context context) {
        this.onLoadMore = onLoadMore;
        this.mContext = context;
        this.setOnScrollListener(this);
        this.setHeaderDividersEnabled(true);
        LinearLayout layout_loading = new LinearLayout(context);
        layout_loading.setGravity(Gravity.CENTER);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        layout_loading.setLayoutParams(params);
        progressBar = new ProgressBar(context);
        layout_loading.addView(progressBar);
        this.addFooterView(layout_loading);
    }

    //show the loading progressBar or not
    public void setLoading(boolean show){
        if(show){
            progressBar.setVisibility(VISIBLE);
        }else{
            progressBar.setVisibility(GONE);
        }
    }

   boolean isMoreThanOnePages;
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState == OnScrollListener.SCROLL_STATE_IDLE){ //when not scrolling
            if(view.getLastVisiblePosition() == (view.getCount()-1)){
                if(isMoreThanOnePages){//when more than one page
                    //show the loading progress
                    progressBar.setVisibility(VISIBLE);
                    setSelection(getBottom());//
                    onLoadMore.onLoadMore();//call back
                }else{
                    progressBar.setVisibility(GONE);//when there is to less data,hide the loading progressBar
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(totalItemCount > visibleItemCount){
            isMoreThanOnePages = true;//more than one page data
        }
    }

    public AutoLoadListView(Context context) {
        super(context);
    }

    public AutoLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoLoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
