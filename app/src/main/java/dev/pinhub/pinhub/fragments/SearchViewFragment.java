package dev.pinhub.pinhub.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.SearchView;

import dev.pinhub.pinhub.R;

public class SearchViewFragment extends Fragment {
    public SearchViewFragment() {
    }

    public static SearchViewFragment newInstance() {
        return new SearchViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_searchview, container, false);

        GridLayout gridLayout = rootView.findViewById(R.id.grid_layout);

        for(int x = 0; x < 10; x++) {
            Button bt = new Button(getActivity());
            final String text = "Bread" + x;
            bt.setText(text);
            bt.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    SearchView search = rootView.findViewById(R.id.search_bar);
                    search.setQuery(text, false);
                }
            });
            gridLayout.addView(bt);
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
