package com.jang.kaunter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

// Jang Kaunter APK V1.1: buka app kaunter (GitHub Pages) dalam WebView, kunci landscape, skrin tak padam,
// skrin penuh (sorok bar status & butang navigasi; leret dari tepi untuk tunjuk sekejap).
// Isi app update sendiri bila index.html di repo berubah (tak perlu APK baru).
public class MainActivity extends Activity {
    private static final String URL = "https://aeoniters-dev.github.io/jang-app/";
    private WebView w;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // skrin kaunter tak tidur
        getWindow().setStatusBarColor(Color.parseColor("#141210"));
        if (Build.VERSION.SDK_INT >= 28) {   // guna kawasan takuk kamera juga
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        w = new WebView(this);
        w.setBackgroundColor(Color.parseColor("#F4EEE6"));
        setContentView(w);
        WebSettings s = w.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);
        w.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView v, WebResourceRequest r, WebResourceError e) {
                if (r.isForMainFrame()) {
                    v.loadDataWithBaseURL(null,
                        "<html><body style='background:#141210;color:#F5EFE3;font-family:sans-serif;padding:32px'>"
                        + "<h2 style='color:#D4A64A'>Jang</h2><p>Tiada sambungan internet.</p>"
                        + "<button style='padding:14px 22px;font-size:16px' onclick=\"location.href='" + URL + "'\">CUBA LAGI</button>"
                        + "</body></html>", "text/html", "utf-8", null);
                }
            }
        });
        if (b != null) w.restoreState(b); else w.loadUrl(URL);
        skrinPenuh();
    }

    private void skrinPenuh() {
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) skrinPenuh();   // sorok semula lepas papan kekunci / leret
    }

    @Override
    protected void onSaveInstanceState(Bundle o) { super.onSaveInstanceState(o); w.saveState(o); }

    @Override
    public void onBackPressed() { if (w.canGoBack()) w.goBack(); }  // butang Back tak tutup app kaunter
}
