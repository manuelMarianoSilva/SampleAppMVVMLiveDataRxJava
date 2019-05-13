package com.mms.solsticechallenge.views.detail_activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.mms.solsticechallenge.R;
import com.mms.solsticechallenge.model.Address;
import com.mms.solsticechallenge.model.User;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class DetailActivityViewModel extends ViewModel {

    public MutableLiveData<String> profilePicURL = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> company = new MutableLiveData<>();
    public MutableLiveData<String> homePhone = new MutableLiveData<>();
    public MutableLiveData<String> mobilePhone = new MutableLiveData<>();
    public MutableLiveData<String> workPhone = new MutableLiveData<>();
    public MutableLiveData<String> address = new MutableLiveData<>();
    public MutableLiveData<String> birthdate = new MutableLiveData<>();
    public MutableLiveData<String> email  = new MutableLiveData<>();
    MutableLiveData<Boolean> isFavorite = new MutableLiveData<>();


    public void setUser(User user) {
        profilePicURL.setValue(user.getSmallImageURL());
        name.setValue(user.getName());
        company.setValue(user.getCompanyName());
        homePhone.setValue(user.getPhone().getHome());
        mobilePhone.setValue(user.getPhone().getMobile());
        workPhone.setValue(user.getPhone().getWork());
        address.setValue(parseAddress(user.getAddress()));
        birthdate.setValue(user.getBirthdate());
        email.setValue(user.getEmailAddress());
        isFavorite.setValue(user.getIsFavorite());
    }

    private String parseAddress(Address address) {
        return address.getStreet() + "\n"
                + address.getCity() + ", "
                + address.getState() + " "
                + address.getZipCode() + ", "
                + address.getCountry();
    }

    public void changeFavoriteStatus(){
        isFavorite.setValue(!isFavorite.getValue());
    }

    @BindingAdapter("android:profilePicURL")
    public static void loadImage (View view, String URL){
        ImageView imageView = (ImageView) view;
        Picasso.get().load(URL).into(imageView, new Callback() {
            @Override
            public void onSuccess() {}

            @Override
            public void onError(Exception e) {
                Log.e("PicassoError", "on detail view: ", e );
                ((ImageView) view).setImageResource(R.drawable.ic_person_light_grey_24dp);
            }
        });
    }
}
