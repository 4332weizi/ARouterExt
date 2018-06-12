package io.auxo.arouter.ext.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import io.auxo.arouter.ext.launcher.ARouterExt;

@Route(path = "/app/content")
public class ContentFragment extends Fragment implements View.OnClickListener {

    private final int REQUEST_RESULT = 1;

    private Button btnStart;
    private TextView tvResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_content, container, false);

        tvResult = view.findViewById(R.id.content_result);
        btnStart = view.findViewById(R.id.content_start);

        btnStart.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                tvResult.setText(data.getStringExtra("result"));
            } else {
                tvResult.setText("");
            }
        }
    }

    @Override
    public void onClick(View v) {
        ARouterExt.build("/app/destination")
                .navigation(this, REQUEST_RESULT);
    }
}
