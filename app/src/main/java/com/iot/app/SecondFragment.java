package com.iot.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.iot.app.databinding.FragmentSecondBinding;

import java.util.regex.Pattern;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    Pattern patternPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    Pattern patternEmail = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    EditText editTextEmail;
    EditText editTextUsername;
    EditText editTextPassword;
    Button buttonRegister;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        editTextEmail = binding.editTextEmail;
        editTextUsername = binding.editTextUsernameRegister;
        editTextPassword = binding.editTextPasswordRegister;
        buttonRegister = binding.buttonRegister;
        buttonRegister.setOnClickListener(this::onRegister);

    }

    private void onRegister(View view) {
        String email = editTextEmail.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (validateData(email, username, password)) {
            AuthSignUpOptions options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(), email)
                    .build();

            Amplify.Auth.signUp(
                    username,
                    password,
                    options,
                    onSuccess -> {
                        makeToast("Registration successful");
                        Log.i("AuthQuickstart", "Result: " + onSuccess);
                        NavHostFragment.findNavController(SecondFragment.this)
                                .navigate(R.id.action_SecondFragment_to_FirstFragment);
                    },
                    error -> {
                        makeToast(error.getMessage());
                        Log.e("AuthQuickstart", error.toString());
                    });

        }

    }

    //password requirements
    //REQUIRES_LOWERCASE
    //REQUIRES_UPPERCASE
    //REQUIRES_NUMBERS
    //REQUIRES_SYMBOLS
    private boolean validateData(String email, String username, String password) {
        //Todo: add email validation
        if(!patternEmail.matcher(email).matches()) {
            editTextEmail.setError("Please enter email");
            editTextEmail.requestFocus();
        }

        if (username.isEmpty()) {
            editTextUsername.setError("Please enter username");
            editTextUsername.requestFocus();
        }
        if (!patternPassword.matcher(password).matches()) {
            editTextPassword.setError("Lowercase, Uppercase, Number, Symbol, 8 characters");
            makeToast("Password does not meet requirements");
            editTextPassword.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void makeToast(String message) {
        Thread thread = new Thread(() -> getActivity().runOnUiThread(
                () -> Toast.makeText(
                        getActivity(), message, Toast.LENGTH_LONG).show()
        ));
        thread.start();
    }

}