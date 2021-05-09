package hanu.mpr.myteam;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);

        // refs
        WebView webView = view.findViewById(R.id.webView);
        Button btnExit = view.findViewById(R.id.btnExit);

        // events
        String url = "https://en.wikipedia.org/wiki/Tic-tac-toe";
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });

        return view;
    }
}