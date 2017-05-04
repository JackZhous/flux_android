package com.jackzhous.boxing.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jack.retrofitrxjava2.manger.LoadDialogContr;
import com.jack.retrofitrxjava2.util.JLog;
import com.jack.retrofitrxjava2.view.LoadDialog;
import com.jackzhous.boxing.R;
import com.jackzhous.boxing.actions.LoginActionCreator;
import com.jackzhous.boxing.dispatcher.Dispatcher;
import com.jackzhous.boxing.encrypt.MD5Encrypt;
import com.jackzhous.boxing.stores.LoginStore;
import com.jackzhous.boxing.stores.Store;
import com.jackzhous.boxing.stores.StoreChanges;
import com.jackzhous.boxing.utils.Constant;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jackzhous on 2017/5/4.
 */

public class LoginActivity extends Activity {

    private LoginActionCreator loginAction = new LoginActionCreator();
    private LoginStore         loginStore  = new LoginStore();
    @BindView(R.id.loginresult)
    TextView textView;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.passwd)
    EditText passwd;

    private LoadDialogContr dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Dispatcher.get().register(loginStore);

        dialog = new LoadDialogContr(this);
    }


    @OnClick(R.id.btn)
    public void onClick(View view){


        String un = username.getText().toString();
        String pw = passwd.getText().toString();
        if(TextUtils.isEmpty(un) || TextUtils.isEmpty(pw)){
            return;
        }
        dialog.show();
        loginAction.actionCreate(Constant.ACTION_LOGIN_TYPE, "18200130442", "888888");
    }


    @Override
    protected void onResume() {
        super.onResume();
        loginStore.register(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        loginStore.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        Dispatcher.get().unregister(loginStore);
    }


    @Subscribe
    public void onLoginStore(StoreChanges.LoginStore event){
        dialog.hide();
        if(loginStore.isSuccess()){
            JLog.i("j_tag", new Gson().toJson(loginStore.getData()));
        }else {
            textView.setText(loginStore.getErrorMsg());
        }
    }

}
