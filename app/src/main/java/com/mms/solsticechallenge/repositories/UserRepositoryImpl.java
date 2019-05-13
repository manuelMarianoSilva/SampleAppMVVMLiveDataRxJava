package com.mms.solsticechallenge.repositories;

import android.util.Pair;

import com.mms.solsticechallenge.model.User;
import com.mms.solsticechallenge.networking.UserListDAO;
import com.mms.solsticechallenge.utils.UserListContainer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
                .map(this::sortListAlphabetically);

        Observable<Integer> numberOfFavoritesObs = userListObs
                .map(this::getNumberOfFavorites);

        return Observable.zip(userListObs, numberOfFavoritesObs, Pair::new);
    }

    @Override
    public Pair<List<User>, Integer> refreshUserList() {
        List<User> newlySortedUsers = sortListAlphabetically(Objects.requireNonNull(UserListContainer.referenceUserList.getValue()));
        Integer newNumberOfFavs = getNumberOfFavorites(Objects.requireNonNull(UserListContainer.referenceUserList.getValue()));
        return new Pair<>(newlySortedUsers, newNumberOfFavs);
    }

    private List<User> sortListAlphabetically(List<User> users){
        return users.stream()
                .sorted((user1, user2) -> user1.getName().compareTo(user2.getName()))
                .sorted((user1, user2) -> user2.getIsFavorite().compareTo(user1.getIsFavorite()))
                .collect(Collectors.toList());
    }

    private Integer getNumberOfFavorites(List<User> users) {
        return (int)users.stream()
                .filter(User::getIsFavorite)
                .count();
    }
}
