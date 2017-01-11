package com.example.venetatodorova.registrationvalidator;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

class RegistrationValidator {
    private boolean isUsernameValid;
    private boolean isFirstNameValid;
    private boolean isLastNameValid;
    private boolean isEmailValid;
    private boolean isPasswordValid;
    private boolean isPasswordConfirmValid;

    private TextInputLayout usernameWrapper;
    private TextInputLayout firstNameWrapper;
    private TextInputLayout lastNameWrapper;
    private TextInputLayout emailWrapper;
    private TextInputLayout passwordWrapper;
    private TextInputLayout passwordConfirmWrapper;

    private String password;
    private Activity activity;

    RegistrationValidator(Activity activity) {
        this.activity = activity;

        usernameWrapper = (TextInputLayout) activity.findViewById(R.id.usernameWrapper);
        firstNameWrapper = (TextInputLayout) activity.findViewById(R.id.firstNameWrapper);
        lastNameWrapper = (TextInputLayout) activity.findViewById(R.id.lastNameWrapper);
        emailWrapper = (TextInputLayout) activity.findViewById(R.id.emailWrapper);
        passwordWrapper = (TextInputLayout) activity.findViewById(R.id.passwordWrapper);
        passwordConfirmWrapper = (TextInputLayout) activity.findViewById(R.id.passwordConfirmWrapper);

        usernameWrapper.setHint(activity.getString(R.string.username));
        firstNameWrapper.setHint(activity.getString(R.string.first_name));
        lastNameWrapper.setHint(activity.getString(R.string.last_name));
        emailWrapper.setHint(activity.getString(R.string.email));
        passwordWrapper.setHint(activity.getString(R.string.password));
        passwordConfirmWrapper.setHint(activity.getString(R.string.password_confirm));

        usernameWrapper.getEditText().addTextChangedListener(usernameWatcher);
        firstNameWrapper.getEditText().addTextChangedListener(firstNameWatcher);
        lastNameWrapper.getEditText().addTextChangedListener(lastNameWatcher);
        emailWrapper.getEditText().addTextChangedListener(emailWatcher);
        passwordWrapper.getEditText().addTextChangedListener(passwordWatcher);
        passwordConfirmWrapper.getEditText().addTextChangedListener(passwordConfirmWatcher);
    }

    boolean isRegistrationValid() {
        return isUsernameValid
                && isFirstNameValid
                && isLastNameValid
                && isEmailValid
                && isPasswordValid
                && isPasswordConfirmValid;
    }

    private TextWatcher usernameWatcher = registerTextWatcher(new OnTextChanged() {
        @Override
        public void onTextChanged(String newText) {
            isUsernameValid = true;
            StringBuffer errorMsg = new StringBuffer();
            if (newText.length() == 0) {
                errorMsg.append(activity.getString(R.string.empty_field)).append("\n");
                isUsernameValid = false;
            } else if (newText.length() < activity.getResources().getInteger(R.integer.MIN_USERNAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.min_field_length), activity.getString(R.string.username), activity.getResources().getInteger(R.integer.MIN_USERNAME_LENGTH)))
                        .append("\n");
                isUsernameValid = false;

            } else if (newText.length() > activity.getResources().getInteger(R.integer.MAX_USERNAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.max_field_length), activity.getString(R.string.username), activity.getResources().getInteger(R.integer.MAX_USERNAME_LENGTH)))
                        .append("\n");
                isUsernameValid = false;
            }

            if (newText.matches(activity.getString(R.string.contains_special_symbol_regex))) {
                errorMsg.append(activity.getString(R.string.contains_special_symbol)).append("\n");
                isUsernameValid = false;
            }
            usernameWrapper.setError(errorMsg);
        }
    });

    private TextWatcher firstNameWatcher = registerTextWatcher(new OnTextChanged() {
        @Override
        public void onTextChanged(String newText) {
            StringBuffer errorMsg = new StringBuffer();
            isFirstNameValid = true;

            if (newText.length() == 0) {
                errorMsg.append(activity.getString(R.string.empty_field)).append("\n");
                isFirstNameValid = false;

            } else if (newText.length() < activity.getResources().getInteger(R.integer.MIN_NAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.min_field_length), activity.getString(R.string.first_name), activity.getResources().getInteger(R.integer.MIN_NAME_LENGTH)))
                        .append("\n");
                isFirstNameValid = false;

            } else if (newText.length() > activity.getResources().getInteger(R.integer.MAX_NAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.max_field_length), activity.getString(R.string.first_name), activity.getResources().getInteger(R.integer.MAX_NAME_LENGTH)))
                        .append("\n");
                isFirstNameValid = false;
            }

            if (newText.matches(activity.getString(R.string.contains_special_symbol_regex))) {
                errorMsg.append(activity.getString(R.string.contains_special_symbol)).append("\n");
                isFirstNameValid = false;
            }

            firstNameWrapper.setError(errorMsg);
        }
    });

    private TextWatcher lastNameWatcher = registerTextWatcher(new OnTextChanged() {
        @Override
        public void onTextChanged(String newText) {
            StringBuffer errorMsg = new StringBuffer();
            isLastNameValid = true;

            if (newText.length() == 0) {
                errorMsg.append(activity.getString(R.string.empty_field)).append("\n");
                isLastNameValid = false;
            } else if (newText.length() < activity.getResources().getInteger(R.integer.MIN_USERNAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.min_field_length), activity.getString(R.string.last_name), activity.getResources().getInteger(R.integer.MIN_USERNAME_LENGTH)))
                        .append("\n");
                isLastNameValid = false;

            } else if (newText.length() > activity.getResources().getInteger(R.integer.MAX_USERNAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.max_field_length), activity.getString(R.string.last_name), activity.getResources().getInteger(R.integer.MAX_USERNAME_LENGTH)))
                        .append("\n");
                isLastNameValid = false;
            }

            if (newText.matches(activity.getString(R.string.contains_special_symbol_regex))) {
                errorMsg.append(activity.getString(R.string.contains_special_symbol)).append("\n");
                isLastNameValid = false;
            }
            lastNameWrapper.setError(errorMsg);
        }
    });

    private TextWatcher emailWatcher = registerTextWatcher(new OnTextChanged() {
        @Override
        public void onTextChanged(String newText) {
            StringBuffer errorMsg = new StringBuffer();
            isEmailValid = true;
            if (!newText.matches(activity.getString(R.string.email_regex))) {
                errorMsg.append(activity.getString(R.string.invalid_email)).append("\n");
                isEmailValid = false;
            }
            emailWrapper.setError(errorMsg);
        }
    });

    private TextWatcher passwordWatcher = registerTextWatcher(new OnTextChanged() {
        @Override
        public void onTextChanged(String newText) {
            StringBuffer errorMsg = new StringBuffer();
            RegistrationValidator.this.password = newText;
            isPasswordValid = true;

            if (newText.length() == 0) {
                errorMsg.append(activity.getString(R.string.empty_field)).append("\n");
                isPasswordValid = false;
            } else {
                if (newText.length() > activity.getResources().getInteger(R.integer.MAX_PASSWORD_LENGTH)) {
                    errorMsg.append(String.format(activity.getString(R.string.max_field_length), activity.getString(R.string.password), activity.getResources().getInteger(R.integer.MAX_PASSWORD_LENGTH)))
                            .append("\n");
                    isPasswordValid = false;

                } else if (newText.length() < activity.getResources().getInteger(R.integer.MIN_PASSWORD_LENGTH)) {
                    errorMsg.append(String.format(activity.getString(R.string.min_field_length), activity.getString(R.string.password), activity.getResources().getInteger(R.integer.MIN_PASSWORD_LENGTH)))
                            .append("\n");
                    isPasswordValid = false;
                }
                if (newText.equals(newText.toLowerCase())) {
                    errorMsg.append(activity.getString(R.string.missing_capital_letter)).append("\n");
                    isPasswordValid = false;
                }

                if (!newText.matches(activity.getString(R.string.contains_number_regex))) {
                    errorMsg.append(activity.getString(R.string.missing_number)).append("\n");
                    isPasswordValid = false;
                }

                if (!newText.matches(activity.getString(R.string.contains_special_symbol_regex))) {
                    errorMsg.append(activity.getString(R.string.missing_special_symbol)).append("\n");
                    isPasswordValid = false;
                }
            }
            passwordWrapper.setError(errorMsg);

        }
    });

    private TextWatcher passwordConfirmWatcher =  registerTextWatcher(new OnTextChanged() {
        @Override
        public void onTextChanged(String newText) {
            StringBuffer errorMsg = new StringBuffer();
            if (!newText.equals(password)) {
                errorMsg.append(activity.getString(R.string.not_matching_passwords)).append("\n");
                isPasswordConfirmValid = false;
            }
            passwordConfirmWrapper.setError(errorMsg);
        }
    });

    private TextWatcher registerTextWatcher(final OnTextChanged onTextChanged) {
        final TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextChanged.onTextChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        return watcher;
    }

    private interface OnTextChanged {
        void onTextChanged(String newText);
    }
}
