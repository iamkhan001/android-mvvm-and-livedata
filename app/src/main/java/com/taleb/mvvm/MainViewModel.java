package com.taleb.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<UserModel> user;


    public LiveData<UserModel> getUser() {
        if (user == null) {
            prepareUser();
        }
        return user;
    }

    private void prepareUser() {
        UserModel model = new UserModel();
        model.setName("Imran Khan");
        model.setEmail("Iamkhan007@outlook.com");
        model.setNum1(0);
        model.setNum2(0);
        model.setResult(0);

        user = new MutableLiveData<>();
        user.setValue(model);
    }


    void setNum(int num1,int num2){
        UserModel model = user.getValue();
        if (model != null) {
            model.setNum1(num1);
            model.setNum2(num2);
            model.setResult(num1+num2);
            user.postValue(model);
        }
    }

}
