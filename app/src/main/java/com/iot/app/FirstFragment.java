package com.iot.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.amplifyframework.core.Amplify;
import com.iot.app.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;


    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonLogin;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        editTextPassword = binding.editTextTextPassword;
        editTextUsername = binding.editTextUsername;
        buttonLogin = binding.buttonLogin;
        buttonLogin.setOnClickListener(this::onLogin);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onLogin(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        if (validateData(username, password))
            Amplify.Auth.signIn(username, password,
                    result -> {
                        if (result.isSignedIn()) {
//                            new Database().pairUserDevice("1234567890");

                            makeToast("Login successful");
                            Log.d("AuthQuickstart", "Sign in succeeded");
                        }
                    },
                    error -> {
                        makeToast("Login failed");
                        Log.e("AuthQuickstart", error.toString());
                    });
    }

    private boolean validateData(String username, String password) {
        if (username.isEmpty()) {
            editTextUsername.setError("Please enter username");
            editTextUsername.requestFocus();
            return false;
        } else if (password.isEmpty()) {
            editTextPassword.setError("Please enter password");
            editTextPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void makeToast(String message) {
        Thread thread = new Thread(() -> getActivity().runOnUiThread(
                () -> Toast.makeText(
                        getActivity(), message, Toast.LENGTH_LONG).show()
        ));
        thread.start();
    }

}