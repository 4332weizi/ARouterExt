package io.auxo.arouter.ext.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/app/destination")
public class DestinationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSend;
    private EditText etResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        btnSend = findViewById(R.id.destination_send);
        etResult = findViewById(R.id.destination_result);

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("result", etResult.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}
