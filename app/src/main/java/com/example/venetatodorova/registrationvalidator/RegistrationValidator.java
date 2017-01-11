package com.example.venetatodorova.registrationvalidator;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

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

    private TextWatcher usernameWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            usernameWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            isUsernameValid = true;
            String username = s.toString();
            StringBuffer errorMsg = new StringBuffer();
            if (username.length() == 0) {
                errorMsg.append(activity.getString(R.string.empty_field)).append("\n");
                isUsernameValid = false;
            } else if (username.length() < activity.getResources().getInteger(R.integer.MIN_USERNAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.min_field_length),
                        activity.getString(R.string.username),
                        activity.getResources().getInteger(R.integer.MIN_USERNAME_LENGTH)))
                        .append("\n");
                isUsernameValid = false;

            } else if (username.length() > activity.getResources().getInteger(R.integer.MAX_USERNAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.max_field_length),
                        activity.getString(R.string.username),
                        activity.getResources().getInteger(R.integer.MAX_USERNAME_LENGTH)))
                        .append("\n");
                isUsernameValid = false;
            }

            if (username.matches(activity.getString(R.string.contains_special_symbol_regex))) {
                errorMsg.append(activity.getString(R.string.contains_special_symbol)).append("\n");
                isUsernameValid = false;
            }
            usernameWrapper.setError(errorMsg);
        }
    };

    private TextWatcher firstNameWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            firstNameWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer errorMsg = new StringBuffer();
            String firstName = s.toString();
            isFirstNameValid = true;

            if (firstName.length() == 0) {
                errorMsg.append(activity.getString(R.string.empty_field)).append("\n");
                isFirstNameValid = false;

            } else if (firstName.length() < activity.getResources().getInteger(R.integer.MIN_NAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.min_field_length),
                        activity.getString(R.string.first_name),
                        activity.getResources().getInteger(R.integer.MIN_NAME_LENGTH)))
                        .append("\n");
                isFirstNameValid = false;

            } else if (firstName.length() > activity.getResources().getInteger(R.integer.MAX_NAME_LENGTH)) {
                errorMsg.append(String.format(activity.getString(R.string.max_field_length),
                        activity.getString(R.string.first_name),
                        activity.getResources().getInteger(R.integer.MAX_NAME_LENGTH)))
                        .append("\n");
                isFirstNameValid = false;
            }

            if (firstName.matches(activity.getString(R.string.contains_special_symbol_regex))) {
                errorMsg.append(activity.getString(R.string.contains_special_symbol)).append("\n");
                isFirstNameValid = false;
            }

            firstNameWrapper.setError(errorMsg);
        }
    };

    private TextWatcher lastNameWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            lastNameWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();
            String lastName = s.toString();
            isLastNameValid = true;

            if (lastName.length() == 0) {
                message.append(activity.getString(R.string.empty_field)).append("\n");
                isLastNameValid = false;
            } else if (lastName.length() < activity.getResources().getInteger(R.integer.MIN_USERNAME_LENGTH)) {
                message.append(String.format(activity.getString(R.string.min_field_length),
                        activity.getString(R.string.last_name),
                        activity.getResources().getInteger(R.integer.MIN_USERNAME_LENGTH)))
                        .append("\n");
                isLastNameValid = false;

            } else if (lastName.length() > activity.getResources().getInteger(R.integer.MAX_USERNAME_LENGTH)) {
                message.append(String.format(activity.getString(R.string.max_field_length),
                        activity.getString(R.string.last_name),
                        activity.getResources().getInteger(R.integer.MAX_USERNAME_LENGTH)))
                        .append("\n");
                isLastNameValid = false;
            }

            if (lastName.matches(activity.getString(R.string.contains_special_symbol_regex))) {
                message.append(activity.getString(R.string.contains_special_symbol)).append("\n");
                isLastNameValid = false;
            }
            lastNameWrapper.setError(message);
        }
    };

    private TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            emailWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();
            isEmailValid = true;
            String email = s.toString();
            if (!email.contains("@")
                    || email.indexOf("@") <= 1
                    || email.indexOf("@") == email.length() - 1
                    || !email.contains(".")
                    || email.lastIndexOf(".") < email.indexOf("@")) {
                message.append(activity.getString(R.string.invalid_email)).append("\n");
                isEmailValid = false;
            }
            emailWrapper.setError(message);
        }
    };

    private TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            passwordWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();
            String password = s.toString();
            RegistrationValidator.this.password = password;
            isPasswordValid = true;

            if (password.length() > activity.getResources().getInteger(R.integer.MAX_PASSWORD_LENGTH)) {
                message.append(String.format(activity.getString(R.string.max_field_length),
                        activity.getString(R.string.password),
                        activity.getResources().getInteger(R.integer.MAX_PASSWORD_LENGTH)))
                        .append("\n");
                isPasswordValid = false;

            } else if (password.length() < activity.getResources().getInteger(R.integer.MIN_PASSWORD_LENGTH)) {
                message.append(String.format(activity.getString(R.string.min_field_length),
                        activity.getString(R.string.password),
                        activity.getResources().getInteger(R.integer.MIN_PASSWORD_LENGTH)))
                        .append("\n");
                isPasswordValid = false;
            }

            if (password.equals(password.toLowerCase())) {
                message.append(activity.getString(R.string.missing_capital_letter)).append("\n");
                isPasswordValid = false;
            }

            if (!password.matches(activity.getString(R.string.contains_number_regex))) {
                message.append(activity.getString(R.string.missing_number)).append("\n");
                isPasswordValid = false;
            }

            if (!s.toString().matches(activity.getString(R.string.contains_special_symbol_regex))) {
                message.append(activity.getString(R.string.missing_special_symbol)).append("\n");
                isPasswordValid = false;
            }
            passwordWrapper.setError(message);
        }
    };

    private TextWatcher passwordConfirmWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            passwordConfirmWrapper.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            StringBuffer message = new StringBuffer();
            String passwordConfirm = s.toString();
            if (!passwordConfirm.equals(password)) {
                message.append(activity.getString(R.string.not_matching_passwords)).append("\n");
                isPasswordConfirmValid = false;
            }
            passwordConfirmWrapper.setError(message);
        }
    };
}
