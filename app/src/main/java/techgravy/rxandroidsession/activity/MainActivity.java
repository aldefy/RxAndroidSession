package techgravy.rxandroidsession.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    void fabOnClick(){
        dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_edit_one, null);
        ((EditText) dialogView.findViewById(R.id.messageEditText)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (((EditText) dialogView.findViewById(R.id.messageEditText)).getText().length() < 2) {
                        Toast.makeText(MainActivity.this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    mainFragment.fetchData(((EditText) dialogView.findViewById(R.id.messageEditText)).getText().toString());
                    searchDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
        builder = new AlertDialog.Builder(MainActivity.this, R.style.AppCompatAlertDialogStyle)
                .setView(dialogView);
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (((EditText) dialogView.findViewById(R.id.messageEditText)).getText().length() < 2) {
                    Toast.makeText(MainActivity.this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                    return;
                }
                mainFragment.fetchData(((EditText) dialogView.findViewById(R.id.messageEditText)).getText().toString());

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                searchDialog.dismiss();
            }
        });
        searchDialog = builder.create();
        searchDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });

        searchDialog.setCanceledOnTouchOutside(true);
        searchDialog.show();


        //Grab the window of the dialog, and change the width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = searchDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }




}
