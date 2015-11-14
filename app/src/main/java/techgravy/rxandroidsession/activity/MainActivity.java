package techgravy.rxandroidsession.activity;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techgravy.rxandroidsession.R;
import techgravy.rxandroidsession.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.coordinator)
    CoordinatorLayout coordinator;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    MainFragment mainFragment;
    private View dialogView;
    private AlertDialog.Builder builder;
    private AlertDialog searchDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();
        init();

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private void init() {
        mainFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, mainFragment);
        transaction.commit();

    }

    @OnClick(R.id.fab)
    void fabOnClick() {
        /* On Clicking the fab button , the search dialog should appear
            , enter a valid query (artist name) , handle errors
            submit the query to fragment for fetching and displaying the results.
            */
    }


}
