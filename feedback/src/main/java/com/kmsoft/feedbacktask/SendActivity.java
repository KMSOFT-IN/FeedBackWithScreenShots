package com.kmsoft.feedbacktask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class SendActivity extends AppCompatActivity {
    ImageView sendImage;
    EditText feedback, email;
    Button send;
    File imageFIle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

//        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
//        StrictMode.setVmPolicy(builder.build());

        sendImage = findViewById(R.id.sendImage);
        feedback = findViewById(R.id.feedback);
        email = findViewById(R.id.email);
        send = findViewById(R.id.send);

        imageFIle = (File) getIntent().getSerializableExtra("ImageFIle");
//        Uri uri = Uri.fromFile(imageFIle);
        Uri uri = FileProvider.getUriForFile(this, "com.kmsoft.provider", imageFIle);
        sendImage.setImageURI(uri);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = feedback.getText().toString();
                String sendEmail = email.getText().toString();

                if (!TextUtils.isEmpty(sendEmail)) {

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    String[] recipients = {sendEmail};
                    intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                    intent.putExtra(Intent.EXTRA_TEXT, body);
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setPackage("com.google.android.gm");
                    startActivity(Intent.createChooser(intent, "Send mail"));
                    finish();
                }

            }
        });

    }
}