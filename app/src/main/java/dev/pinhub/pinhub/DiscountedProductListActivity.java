package dev.pinhub.pinhub;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dev.pinhub.pinhub.fragments.DiscountedItemFragment;
import dev.pinhub.pinhub.storage.client.DiscountDbCallback;
import dev.pinhub.pinhub.storage.client.DiscountDbHelper;
import dev.pinhub.pinhub.storage.client.DiscountDbHelperDummy;
import dev.pinhub.pinhub.storage.client.models.DiscountedItem;
import dev.pinhub.pinhub.models.DiscountedItemViewModel;

public class DiscountedProductListActivity extends AppCompatActivity implements DiscountedItemFragment.OnListFragmentInteractionListener {

    private DiscountedItemViewModel viewModel;
    private DiscountDbHelper discountDbHelper;

    public DiscountedProductListActivity()
    {
        this.discountDbHelper = new DiscountDbHelperDummy();
    }

    public DiscountedProductListActivity(DiscountDbHelper discountDbHelper)
    {
        this.discountDbHelper = discountDbHelper;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discounted_product_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewModel = ViewModelProviders.of(this).get(DiscountedItemViewModel.class);

        Bundle bundle = getIntent().getExtras();
        addDataById(bundle.getInt("storeId"));
    }

    public void onDiscountedItemsFragmentInteraction(DiscountedItem item){
        Toast.makeText(this, item.getName(), Toast.LENGTH_SHORT).show();
    }


    // TODO: Remove after real data is used
    private void addDataById(int storeId) {
        discountDbHelper.getDiscountByStoreId(storeId, new DiscountDbCallback() {
            @Override
            public void onCompleteList(List<DiscountedItem> discountedItems) {
                viewModel.setDiscountList(discountedItems);
            }

            @Override
            public void onCompleteCount(int discountedItemCount) { }
        });
    }
}
