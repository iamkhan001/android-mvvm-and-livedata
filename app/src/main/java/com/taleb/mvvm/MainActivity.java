package com.taleb.mvvm;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView tvName,tvEmail,tvResult;
        final EditText editNo1,editNo2;
        final Button btnAdd;

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvResult = findViewById(R.id.tvResult);

        editNo1 = findViewById(R.id.editNo1);
        editNo2 = findViewById(R.id.editNo2);
        btnAdd = findViewById(R.id.btnAdd);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.getUser().observe(this, new Observer<UserModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(@Nullable UserModel userModel) {
                if (userModel==null){
                    return;
                }

                tvName.setText(userModel.getName());
                tvEmail.setText(userModel.getEmail());
                tvResult.setText(userModel.getResult()+"");

                editNo1.setText(userModel.getNum1()+"");
                editNo2.setText(userModel.getNum2()+"");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n1 = editNo1.getText().toString().trim();
                String n2 = editNo2.getText().toString().trim();

                if (n1.length()<1||n2.length()<1){
                    Toast.makeText(MainActivity.this,"Enter Valid Numbers",Toast.LENGTH_SHORT).show();
                    return;
                }

                viewModel.setNum(Integer.parseInt(n1),Integer.parseInt(n2));
            }
        });

    }
}
