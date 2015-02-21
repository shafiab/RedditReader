package com.example.shafiab.redditreader;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/**
 * Created by shafiab on 9/19/14.
 */
public class DetailFragment extends Fragment {

    String urlLink;
    View myView;
    DownloadAndShowImage downloadImage;


    void initialize (String url)
    {
        this.urlLink = url;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.detail_fragment,container,false);
        return myView;
    }

    @Override
    public void onStart() {
        ImageView bmImage= (ImageView) myView.findViewById(R.id.imageView);
        downloadImage = new DownloadAndShowImage(bmImage);

        super.onStart();
        if (urlLink.toLowerCase().contains("png")|urlLink.toLowerCase().contains("jpg")|urlLink.toLowerCase().contains("gif")|urlLink.toLowerCase().contains("jpeg"))
        {
            downloadImage.execute(urlLink);
        }
        else
        {
            WebView webview = (WebView) myView.findViewById(R.id.webview);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setWebViewClient(new AuthClient());
            webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webview.loadUrl(urlLink);



            /*Uri uri = Uri.parse(urlLink);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);*/
        }
    }
    private class AuthClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView v, String url) {
            v.clearHistory();
        }
    }
}
