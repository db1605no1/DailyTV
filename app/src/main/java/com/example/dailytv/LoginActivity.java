package com.example.dailytv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dailytv.beans.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends MyActivity{

    @Bind(R.id.toolbarlogin)
    Toolbar toolbarlogin;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.register1)
    Button register1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarlogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void login1(View view){
        final String name = username.getText().toString();
        final String pwd = password.getText().toString();
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("name", name);
        query.findObjects(this, new FindListener<User>(){
            @Override
            public void onSuccess(List<User> list){
                if(list.size() == 0){
                    Toast.makeText(LoginActivity.this, "用户名不存在，请先注册！", Toast.LENGTH_SHORT).show();
                }else if(list.size() == 1){
                    User user = list.get(0);
                    String pwd1 = user.getPwd();
                    if(pwd1.equals(pwd)){
                        Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent();
                        Bundle bundle=new Bundle();
                        bundle.putString("name",name);
                        intent.putExtras(bundle);
                        LoginActivity.this.setResult(RESULT_OK,intent);
                         LoginActivity.this.finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(int i, String s){
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register1(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }




}
