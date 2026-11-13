package com.lizi.mathquest;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 安卓套壳主页面：全屏 WebView 加载 assets 目录下的游戏网页。
 * 游戏完全离线运行，无需联网权限。
 */
public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);   // 游戏脚本必须开启
        settings.setDomStorageEnabled(true);   // 本地存储
        settings.setAllowFileAccess(true);

        // 返回键优先在网页历史内回退
        webView.setWebViewClient(new android.webkit.WebViewClient());
        webView.loadUrl("file:///android_asset/index.html");

        setContentView(webView);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
