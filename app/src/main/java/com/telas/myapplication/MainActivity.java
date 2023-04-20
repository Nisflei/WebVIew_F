package com.telas.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView firefox;
    ProgressBar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //verificar conexao de rede

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected()) {
            ((ImageView) findViewById(R.id.rede)).setVisibility(View.VISIBLE);
        } else {

            firefox = (WebView) findViewById(R.id.firefox);
            barra = (ProgressBar) findViewById(R.id.progressBar);

            firefox.getSettings().setJavaScriptEnabled(true);

            barra.setVisibility(View.INVISIBLE);

            firefox.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    barra.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    barra.setVisibility(View.INVISIBLE);
                }
            });


            firefox.loadUrl("https://institutojef.org.br/escolas/tech");

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && firefox.canGoBack()){
            firefox.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}