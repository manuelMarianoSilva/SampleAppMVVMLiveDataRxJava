package com.mms.solsticechallenge.utils;

import com.mms.solsticechallenge.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class ListClassifier {

    public static List<User> sortListAlphabetically(List<User> users){
        return users.stream()
                .sorted((user1, user2) -> user1.getName().compareTo(user2.getName()))
                .sorted((user1, user2) -> user2.getIsFavorite().compareTo(user1.getIsFavorite()))
                .collect(Collectors.toList());
    }

    public static Integer getNumberOfFavorites(List<User> users) {
        return (int)users.stream()
                .filter(User::getIsFavorite)
                .count();
    }
}
