package hanu.mpr.tut09;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


public class BookmarksFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        assert container != null;

        View view = container.findViewById(R.id.vnexpressImageView);

        view.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = new BookmarksFragment();
        FragmentActivity fragmentActivity = getActivity();

        assert fragmentActivity != null;

        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

        Bundle bundle = new Bundle();

        String url;

        switch (v.getId()) {
            case R.id.mediumImageView:
                url = "";
                break;
            case R.id.baomoiImageView:
                url = "";
                break;
            case R.id.vnexpressImageView:
                url = "";
                break;
            case R.id.bluezoneImageView:
                url = "";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

        bundle.putString("url", url);
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}