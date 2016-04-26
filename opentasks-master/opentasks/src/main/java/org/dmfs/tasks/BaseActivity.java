package org.dmfs.tasks;

import java.util.TimeZone;

import org.dmfs.provider.tasks.TaskContract;
import org.dmfs.provider.tasks.TaskContract.Tasks;
import org.dmfs.tasks.model.ContentSet;
import org.dmfs.tasks.utils.ActionBarActivity;
import org.dmfs.tasks.utils.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;

import me.kiip.sdk.Kiip;
import me.kiip.sdk.KiipFragmentCompat;
import me.kiip.sdk.Poptart;

public class BaseActivity extends AppCompatActivity {
    private final static String KIIP_TAG = "kiip_fragment_tag";

    private KiipFragmentCompat mKiipFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create or re-use KiipFragment.
        if (savedInstanceState != null) {
            mKiipFragment = (KiipFragmentCompat) getSupportFragmentManager().findFragmentByTag(KIIP_TAG);
        } else {
            mKiipFragment = new KiipFragmentCompat();
            getSupportFragmentManager().beginTransaction().add(mKiipFragment, KIIP_TAG).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Kiip.getInstance().startSession(new Kiip.Callback() {
            @Override
            public void onFailed(Kiip kiip, Exception exception) {
                // handle failure
            }

            @Override
            public void onFinished(Kiip kiip, Poptart poptart) {
                onPoptart(poptart);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Kiip.getInstance().endSession(new Kiip.Callback() {
            @Override
            public void onFailed(Kiip kiip, Exception exception) {
                // handle failure
            }

            @Override
            public void onFinished(Kiip kiip, Poptart poptart) {
                onPoptart(poptart);
            }
        });
    }

    public void onPoptart(Poptart poptart) {
        mKiipFragment.showPoptart(poptart);
    }
}