package org.dlna.html5testlauncher;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final private String LOCAL_FULL_TEST_RUNNER_URL = "http://web-platform.test:8000/tools/runner/index.html";
    static final private String LOCAL_SIMPLE_TEST_RUNNER_URL = "http://web-platform.test:8000/tools/runner/tests.html";
    static final private String W3C_TEST_RUNNER_URL = "http://w3c-test.org/tools/runner/index.html";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new HtmlTestWebViewClient());
        webView.loadUrl(LOCAL_SIMPLE_TEST_RUNNER_URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.system_browser:
                try {
                    Uri uri = Uri.parse(webView.getUrl());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.w3c_runner:
                webView.loadUrl(W3C_TEST_RUNNER_URL);
                break;
            case R.id.local_full_runner:
                webView.loadUrl(LOCAL_FULL_TEST_RUNNER_URL);
                break;
            case R.id.local_simple_runner:
                webView.loadUrl(LOCAL_SIMPLE_TEST_RUNNER_URL);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private class HtmlTestWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading (WebView view,
                                                 WebResourceRequest request)
        {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
}
