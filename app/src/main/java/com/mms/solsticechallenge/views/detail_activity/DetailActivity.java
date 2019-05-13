package com.mms.solsticechallenge.views.detail_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mms.solsticechallenge.R;
import com.mms.solsticechallenge.databinding.ActivityDetailBinding;
import com.mms.solsticechallenge.model.User;
import com.mms.solsticechallenge.root.ChallengeApp;
import com.mms.solsticechallenge.utils.UserListContainer;
import com.mms.solsticechallenge.views.main_activity.MainActivity;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class DetailActivity extends AppCompatActivity {

    public static final String ADAPTER_POSITION = "adapter_position";
    private ActivityDetailBinding binding;
    private int adapterPosition;
    private DetailActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChallengeApp.getApplicationComponent(this).inject(this);
        checkStatus();
        bindView();
        getClickedUser();
        observeViewmodel();
    }

    private void checkStatus() {
        if (UserListContainer.referenceUserList == null)
            startActivity(new Intent(this, MainActivity.class));
    }

    private void getClickedUser() {
        adapterPosition = Objects.requireNonNull(getIntent().getExtras()).getInt(ADAPTER_POSITION);
        UserListContainer.referenceUserList.observe(this, users ->
                viewModel.setUser(users.get(adapterPosition)));
    }

    private void bindView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        viewModel = ViewModelProviders.of(this).get(DetailActivityViewModel.class);
        binding.backButton.setOnClickListener(v -> onBackPressed());
        binding.setViewmodel(viewModel);
    }

    private void observeViewmodel(){

        viewModel.name.observe(this, name ->
                binding.contactNameOnDetailScreen.setVisibility(name == null ? View.GONE : View.VISIBLE));

        viewModel.company.observe(this, company ->
                binding.companyNameOnDetailScreen.setVisibility(company == null ? View.GONE : View.VISIBLE));

        viewModel.homePhone.observe(this, homePhone ->
                binding.homePhone.setVisibility(homePhone == null ? View.GONE : View.VISIBLE));

        viewModel.mobilePhone.observe(this, mobile ->
                binding.mobilePhone.setVisibility(mobile == null ? View.GONE : View.VISIBLE));

        viewModel.workPhone.observe(this, workPhone ->
                binding.workPhone.setVisibility(workPhone == null ? View.GONE : View.VISIBLE));

        viewModel.address.observe(this, address ->
                binding.address.setVisibility(address == null ? View.GONE : View.VISIBLE));

        viewModel.birthdate.observe(this, birthdate ->
                binding.birthdate.setVisibility(birthdate == null ? View.GONE : View.VISIBLE));

        viewModel.email.observe(this, email ->
                binding.email.setVisibility(email == null ? View.GONE : View.VISIBLE));

        viewModel.isFavorite.observe(this, isFavorite -> {
                changeFavoriteMark(isFavorite);
                changeReferenceUserList(isFavorite);
        });
    }

    private void changeReferenceUserList(Boolean isFavorite) {
        UserListContainer.referenceUserList.observe(this, users -> {
            users.get(adapterPosition).setIsFavorite(isFavorite);
        });
    }

    private void changeFavoriteMark(Boolean isFavorite) {
        binding.greyStar.setVisibility(isFavorite ? View.GONE : View.VISIBLE);
        binding.animationFavourite.setVisibility(isFavorite ? View.VISIBLE : View.INVISIBLE);
        if (isFavorite) binding.animationFavourite.playAnimation();
    }
}
