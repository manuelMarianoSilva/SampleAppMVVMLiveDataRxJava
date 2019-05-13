package com.mms.solsticechallenge.repositories;

import android.util.Pair;

import com.mms.solsticechallenge.model.User;
import com.mms.solsticechallenge.networking.UserListDAO;
import com.mms.solsticechallenge.utils.ListClassifier;
import com.mms.solsticechallenge.utils.UserListContainer;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UserRepositoryImpl implements UserRepository {
    private UserListDAO userListDAO;

    @Inject
    public UserRepositoryImpl(UserListDAO userListDAO) {
        this.userListDAO = userListDAO;
    }

    @Override
    public Observable<Pair<List<User>, Integer>> fetchUserListFromWS(){

        //TODO add connectivity check

        Observable<List<User>> userListObs = userListDAO
                .fetchListOfUsersFromWS()
                .map(ListClassifier::sortListAlphabetically);

        Observable<Integer> numberOfFavoritesObs = userListObs
                .map(ListClassifier::getNumberOfFavorites);

        return Observable.zip(userListObs, numberOfFavoritesObs, Pair::new);
    }

    @Override
    public void resetUserList() {
        if (UserListContainer.referenceUserList.getValue() != null){
            List<User> newlySortedUsers = ListClassifier.sortListAlphabetically(Objects.requireNonNull(UserListContainer.referenceUserList.getValue()));
            Integer newNumberOfFavs = ListClassifier.getNumberOfFavorites(Objects.requireNonNull(UserListContainer.referenceUserList.getValue()));

            UserListContainer.referenceUserList.setValue(newlySortedUsers);
            UserListContainer.referenceNumberOfFavorites.setValue(newNumberOfFavs);
        }
    }
}
