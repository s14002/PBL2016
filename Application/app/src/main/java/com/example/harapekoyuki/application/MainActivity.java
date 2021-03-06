package com.example.harapekoyuki.application;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//自分自身のクラスに「OnClickListener」インタフェースを実装
//implements View.OnClickListener
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyHelper myHelper;

    private Button button_create;
    private Button button_lineup;
    private Button button_orderStatus;
    private Button button_member_change;
    private Button button_member_delete;
    private Button button_login;
    private EditText memberIdEditText;
    private EditText passwordEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // MyHelperオブジェクトを作り、フィールドにセット
        myHelper = new MyHelper(this);


        /**メニュー画面にあるボタンを取得している
         * 変数名 = (Button)findViewById(R.id.id名);
         変数名.setOnClickListener(this);

         */
        button_create = (Button) findViewById(R.id.button_create);
        button_create.setOnClickListener(this);

        button_lineup = (Button) findViewById(R.id.button_lineup);
        button_lineup.setOnClickListener(this);

        button_orderStatus = (Button) findViewById(R.id.button_orderStatus);
        button_orderStatus.setOnClickListener(this);

        button_member_change = (Button) findViewById(R.id.button_member_change);
        button_member_change.setOnClickListener(this);

        button_member_delete = (Button) findViewById(R.id.button_member_delete);
        button_member_delete.setOnClickListener(this);

        button_login = (Button)findViewById(R.id.button_login);
        button_login.setOnClickListener(this);


    }

    public void onClick(View v) {

        //Intentを変数宣言
        Intent intent;

        /**ボタンを押すと各Activityに遷移する
         *if (v.getId() == R.id.id名){
         intent = new Intent(this, 遷移先ファイル名.class);
         startActivityForResult(intent, 0);
         }
         */

        if (v.getId() == R.id.button_create) {
            intent = new Intent(this, MemberRegistration.class);
            startActivityForResult(intent, 0);

        } else if (v.getId() == R.id.button_lineup) {
            intent = new Intent(this, Lineup.class);
            startActivityForResult(intent, 1);

        } else if (v.getId() == R.id.button_orderStatus) {
            intent = new Intent(this, OrderStatus.class);
            startActivityForResult(intent, 2);

        } else if (v.getId() == R.id.button_member_change) {
            intent = new Intent(this, MemberChange.class);
            startActivityForResult(intent, 3);

        } else if (v.getId() == R.id.button_member_delete) {
            intent = new Intent(this, MemberDelete.class);
            startActivityForResult(intent, 4);

        } else if (v.getId() == R.id.button_login) {
            memberIdEditText = new EditText(MainActivity.this);
            memberIdEditText.setText(R.string.text_memberId);
            passwordEditText = new EditText(MainActivity.this);
            passwordEditText.setText(R.string.text_password);
            LoginDialog dialogFragment = LoginDialog
                    .newInstance(R.string.text_login, R.string.text_memberId, R.string.text_password);
            dialogFragment.setOnLoginClickListener(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, memberIdEditText.getText().toString() + "が入力されました", Toast.LENGTH_SHORT).show();
                }
            });
            dialogFragment.setPasswordEditText(memberIdEditText);
            dialogFragment.show(getSupportFragmentManager(), "dialog_fragment");
        }

    }

}
