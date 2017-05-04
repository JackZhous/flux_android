package com.jackzhous.boxing.stores;

import com.jack.retrofitrxjava2.model.UserBean;
import com.jack.retrofitrxjava2.response.HttpResponse;
import com.jackzhous.boxing.actions.Action;
import com.jackzhous.boxing.utils.Constant;

/**
 * Created by jackzhous on 2017/5/4.
 */

public class LoginStore extends Store<UserBean> {

    UserBean bean;

    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChanges.LoginStore();
    }

    @Override
    public void onAction(Action action) {
        if(Constant.ACTION_USERINFGO_TYPE.equals(action.getType())){
            HttpResponse response = (HttpResponse) action.getData();
            isSuccess = (response.getCode() == Constant.HTTP_SUCCESS);
            bean = (UserBean) response.getDetail();
            errorMsg = response.getMessage();

            emitStoreChange();
        }else if(Constant.ACTION_LOGIN_TYPE.equals(action.getType())){
            HttpResponse response = (HttpResponse) action.getData();
            isSuccess = false;
            errorMsg = response.getMessage();

            emitStoreChange();
        }
    }
}
