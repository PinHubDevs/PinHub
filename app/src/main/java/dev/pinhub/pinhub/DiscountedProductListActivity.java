package dev.pinhub.pinhub;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.pinhub.pinhub.fragments.DiscountedItemFragment;
import dev.pinhub.pinhub.models.DiscountedItem;
import dev.pinhub.pinhub.models.DiscountedItemViewModel;

public class DiscountedProductListActivity extends AppCompatActivity implements DiscountedItemFragment.OnListFragmentInteractionListener {

    private DiscountedItemViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discounted_product_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(DiscountedItemViewModel.class);
        addDummyData();
    }

    public void onListFragmentInteraction(DiscountedItem item){
        Toast.makeText(this, item.getName(), Toast.LENGTH_SHORT).show();
    }


    // TODO: Remove after real data is used
    private void addDummyData() {
        List<DiscountedItem> items = new ArrayList<>();

        for(int i = 0; i < 35; i++){
            items.add(new DiscountedItem("Duona " + i, "Jore", R.drawable.iki_logo, 3.99, 15));
        }

        viewModel.setDiscountList(items);
    }
}
