package dev.pinhub.pinhub;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.List;

import dev.pinhub.pinhub.fragments.DiscountedItemFragment;
import dev.pinhub.pinhub.storage.client.DiscountClientCallback;
import dev.pinhub.pinhub.storage.client.DiscountClientHelper;
import dev.pinhub.pinhub.storage.client.DiscountClientHelperDummy;
import dev.pinhub.pinhub.storage.client.models.DiscountedItem;
import dev.pinhub.pinhub.models.DiscountedItemViewModel;

public class DiscountedProductListActivity extends AppCompatActivity implements DiscountedItemFragment.OnListFragmentInteractionListener {

    private DiscountedItemViewModel viewModel;
    private DiscountClientHelper discountClientHelper;

    public DiscountedProductListActivity()
    {
        this.discountClientHelper = new DiscountClientHelperDummy();
    }

    public DiscountedProductListActivity(DiscountClientHelper discountClientHelper)
    {
        this.discountClientHelper = discountClientHelper;
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
        discountClientHelper.getDiscountByStoreId(storeId, new DiscountClientCallback() {
            @Override
            public void onCompleteList(List<DiscountedItem> discountedItems) {
                viewModel.setDiscountList(discountedItems);
            }

            @Override
            public void onCompleteCount(int discountedItemCount) { }
        });
    }
}
