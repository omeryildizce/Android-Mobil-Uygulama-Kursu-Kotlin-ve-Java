package com.omeryildizce.tabbedfragment.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {
        private MutableLiveData<String> nameInput = new MutableLiveData<>();

    public MutableLiveData<String> getNameInput() {
        return nameInput;
    }

    public void setName(String name) {
        this.nameInput.setValue(name);
    }
    public LiveData<String> getName(){
        return nameInput;
    }
}