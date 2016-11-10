/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.dlna.html5testlauncher;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/*
 * MainActivity class that loads MainFragment
 */
public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    static final private String TEST_RUNNER_URL = "http://web-platform.test:8000/tools/runner/tests.html";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = (WebView)findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new HtmlTestWebViewClient());
        webView.loadUrl(TEST_RUNNER_URL);

        /*
        Button button = (Button)findViewById(R.id.system_browser_button);
        if(null != button) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Uri uri = Uri.parse(TEST_RUNNER_URL);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        */
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
