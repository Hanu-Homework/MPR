package hanu.mpr.myteam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        // refs
        Button btnBrowser = view.findViewById(R.id.btnBrowser);
        Button btnWeb = view.findViewById(R.id.btnWeb);
        Button btnMaps = view.findViewById(R.id.btnMaps);
        Button btnREST = view.findViewById(R.id.btnREST);
        Button btnImageDownload = view.findViewById(R.id.btnImageDownload);

        // events
        btnBrowser.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
        btnMaps.setOnClickListener(this);
        btnREST.setOnClickListener(this);
        btnImageDownload.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        assert manager != null;
        Fragment fragment;

        switch(v.getId()) {
            case R.id.btnBrowser:
                String url = "https://en.wikipedia.org/wiki/Tic-tac-toe";
                launchBrowser(url);
                break;

            case R.id.btnWeb:
                fragment = new WebFragment();

                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack("fragment_menu")
                        .commit();

                break;

            case R.id.btnMaps:
                break;

            case R.id.btnImageDownload:
                fragment = new ImageDownloadFragment();

                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack("fragment_menu")
                        .commit();
                break;

            case R.id.btnREST:
                fragment = new RESTFragment();

                manager.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .addToBackStack("fragment_menu")
                        .commit();
                break;
        }
    }

    private void launchBrowser(String url) {
        Uri uri = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(browserIntent);
    }
}