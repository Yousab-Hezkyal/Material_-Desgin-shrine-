package com.google.codelabs.mdc.java.shrine;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.shr_login_fragment, container, false);

        // Username edit text
        final TextInputLayout usernameTextInput = view.findViewById(R.id.username_text_input);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text);

        // password edit text
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        // Next button
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        // cancel button
        MaterialButton cancelButton = view.findViewById(R.id.cancel_button);

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUsernameValid(usernameEditText.getText())){
                    // error on edit text
                    usernameEditText.setError(getString(R.string.shr_error_username));
                } else if (!isPasswordValid(passwordEditText.getText())) {
                    // error on input text
                    passwordTextInput.setError(getString(R.string.shr_error_password));
                } else {
                    // Navigate to the next Fragment
                    ((NavigationHost) getActivity()).navigateTo(new ProductGridFragment(), false);
                }
            }
        });

        // clear text fields if click cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameEditText.setText(null);
                passwordEditText.setText(null);
                usernameEditText.setError(null);
                passwordTextInput.setError(null);
            }
        });

        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });

        // Clear the error once more than 8 characters are typed.
        usernameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isUsernameValid(usernameEditText.getText())) {
                    usernameTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });

        return view;
    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }

    // "isUsername" from "Navigate to the next Fragment" section method goes here
    private boolean isUsernameValid(@Nullable Editable text) {
        return text != null && text.length() >= 4;
    }
}
