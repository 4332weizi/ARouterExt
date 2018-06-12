package io.auxo.arouter.ext.sample;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.auxo.arouter.ext.launcher.ARouterExt;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, getContentFragment())
                .commit();
    }

    private Fragment getContentFragment() {
        return (Fragment) ARouterExt.build("/app/content")
                .navigation();
    }
}
