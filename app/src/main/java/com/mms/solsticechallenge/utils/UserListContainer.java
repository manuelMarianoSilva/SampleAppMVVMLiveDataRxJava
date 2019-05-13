package com.mms.solsticechallenge.utils;

import com.mms.solsticechallenge.model.User;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class UserListContainer {

    public static MutableLiveData<List<User>> referenceUserList = new MutableLiveData<>();
    public static MutableLiveData<Integer> referenceNumberOfFavorites = new MutableLiveData<>();

}
