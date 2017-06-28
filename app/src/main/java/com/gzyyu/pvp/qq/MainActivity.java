package com.gzyyu.pvp.qq;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.requestFocusFromTouch();
        /*切记要添加这一行代码，因为Android中的WebView默认是不响应JavaScript控件的，
        如果不加下面这一行，就会出现一个很奇怪的问题，网上也有很多人都在问，
        为什么加载的网页链接可以点击并跳转，但是按钮点了却没反应。
        因为很多网页的控件是通过js编写的，所以要添加下面一行代码以便响应JS控件。*/
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);//设置WebView是否使用预览模式加载界面。
        webSettings.setJavaScriptEnabled(true);//设置WebView是否允许执行JavaScript脚本，默认false，不允许
        webSettings.setBuiltInZoomControls(true);//设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        webSettings.setSupportZoom(true);//设置WebView是否支持使用屏幕控件或手势进行缩放，默认是true，支持缩放。
        webView.loadUrl("http://pvp.qq.com/cp/a20161115tyf/page1.shtml");
        webView.setWebViewClient(new WebViewClient(){
            //网页加载开始时调用，显示加载提示旋转进度条
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "页面正在加载,请稍后......",Toast.LENGTH_SHORT).show();
            }

            //网页加载完成时调用，隐藏加载提示旋转进度条
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "页面加载完成.",Toast.LENGTH_SHORT).show();
            }

            //网页加载失败时调用，隐藏加载提示旋转进度条
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "网页加载失败,请检查网络",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
