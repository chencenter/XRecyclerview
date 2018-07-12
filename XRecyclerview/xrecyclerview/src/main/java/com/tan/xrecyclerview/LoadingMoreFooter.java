package com.tan.xrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tan.xrecyclerview.progressindicator.AVLoadingIndicatorView;

/**
 * Created by Administrator on 2018/7/12 0012.
 */

public class LoadingMoreFooter extends LinearLayout{

    private SimpleViewSwitcher progressCon;
    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;

    private TextView mText;
    private String loadingHint;
    private String noMoreHint;
    private String loadingDoneHint;

    private AVLoadingIndicatorView progressView;
    public LoadingMoreFooter(Context context) {
        super(context);
        initView();
    }

    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadingMoreFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
         initView();
    }

    public void destroy(){
        progressCon = null;
        if(progressView != null){
            progressView.destroy();
            progressView = null;
        }
    }

    public void setLoadingHint(String hint) {
        if (TextUtils.isEmpty(hint)){
            loadingHint = (String)getContext().getText(R.string.listview_loading);
        }else {
            loadingHint = hint;
        }

    }

    public void setNoMoreHint(String hint) {
        if (TextUtils.isEmpty(hint)){
            noMoreHint = (String)getContext().getText(R.string.nomore_loading);
        }else {
            noMoreHint = hint;
        }

    }

    public void setLoadingDoneHint(String hint) {
        if (TextUtils.isEmpty(hint)){
            loadingDoneHint = (String)getContext().getText(R.string.loading_done);
        }else {
            loadingDoneHint = hint;
        }

    }

    public void initView(){
        setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.listview_footer, this);
        progressCon = view.findViewById(R.id.listview_foot_progress);
        mText = view.findViewById(R.id.listview_foot_more);
        progressView = new  AVLoadingIndicatorView(this.getContext());
        progressView.setIndicatorColor(0xffB5B5B5);
        progressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader);
        progressCon.setView(progressView);


//        setGravity(Gravity.CENTER);
//        setLayoutParams(new RecyclerView.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        progressCon = new SimpleViewSwitcher(getContext());
//        progressCon.setLayoutParams(new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//        progressView = new  AVLoadingIndicatorView(this.getContext());
//        progressView.setIndicatorColor(0xffB5B5B5);
//        progressView.setIndicatorId(ProgressStyle.BallSpinFadeLoader);
//        progressCon.setView(progressView);
//
//        addView(progressCon);
//        mText = new TextView(getContext());
//        mText.setText(getContext().getString(R.string.listview_loading));
//
//        if(loadingHint == null || loadingHint.equals("")){
//            loadingHint = (String)getContext().getText(R.string.listview_loading);
//        }
//        if(noMoreHint == null || noMoreHint.equals("")){
//            noMoreHint = (String)getContext().getText(R.string.nomore_loading);
//        }
//        if(loadingDoneHint == null || loadingDoneHint.equals("")){
//            loadingDoneHint = (String)getContext().getText(R.string.loading_done);
//        }
//
//        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins( (int)getResources().getDimension(R.dimen.textandiconmargin),0,0,0 );
//
//        mText.setLayoutParams(layoutParams);
//        addView(mText);
    }

    public void setProgressStyle(int style) {
        if(style == ProgressStyle.SysProgress){
            progressCon.setView(new ProgressBar(getContext(), null, android.R.attr.progressBarStyle));
        }else{
            progressView = new AVLoadingIndicatorView(this.getContext());
            progressView.setIndicatorColor(0xffB5B5B5);
            progressView.setIndicatorId(style);
            progressCon.setView(progressView);
        }
    }

    public void  setState(int state) {
        switch(state) {
            case STATE_LOADING:
                progressCon.setVisibility(View.VISIBLE);
                mText.setText(loadingHint);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                mText.setText(loadingDoneHint);
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                mText.setText(noMoreHint);
                progressCon.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
        }
    }

}
