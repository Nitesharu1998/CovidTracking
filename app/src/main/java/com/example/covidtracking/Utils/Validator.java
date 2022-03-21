package com.example.covidtracking.Utils;

import android.app.Activity;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static Boolean isValidEmail(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public static boolean pincodeValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_PINCODE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().length() < 6) {
            Global.showToast(activity, AppConstants.VALID_PINCODE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().trim().startsWith("0")) {
            Global.showToast(activity, AppConstants.PINCODE_SHOULD_NOT_START, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().trim().startsWith("9")) {
            Global.showToast(activity, AppConstants.PINCODE_SHOULD_NOT_START_9, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static int LongestStringSequence(String message) {
        int largestSequence = 0;
        char longestChar = '\0';
        int currentSequence = 1;
        char current = '\0';
        char next = '\0';
        try {
            for (int i = 0; i < message.length() - 1; i++) {
                current = message.charAt(i);
                next = message.charAt(i + 1);
                // If character's are in sequence , increase the counter
                if (current == next) {
                    currentSequence += 1;
                } else {
                    if (currentSequence > largestSequence) { // When sequence is
                        // completed, check if
                        // it is longest
                        largestSequence = currentSequence;
                        longestChar = current;
                    }
                    currentSequence = 1; // re-initialize counter
                }
            }
            if (currentSequence > largestSequence) { // Check if last string
                // sequence is longest
                largestSequence = currentSequence;
                longestChar = current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return largestSequence;
    }

    public static boolean isValidRemarks(String remarks) {
        if (remarks.trim().length() < 10 || remarks.trim().length() > 950)
            return false;
        return true;
    }

    public static boolean mobileNoValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_MOBILE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().length() < 10) {
            Global.showToast(activity, AppConstants.MOBILE_10_DIGITS, 0);
            editText.requestFocus();
            return false;
        }
        if (!editText.getText().toString().startsWith("9") && !editText.getText().toString().startsWith("8")
                && !editText.getText().toString().startsWith("7") && !editText.getText().toString().startsWith("6")) {
            Global.showToast(activity, AppConstants.ENTER_VALID_MOBILE, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean nameValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_NAME, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().startsWith(" ")) {
            Global.showToast(activity, AppConstants.NAME_SHOULD_NOT_START_SPACE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().length() < 2) {
            Global.showToast(activity, AppConstants.NAME_MIN_2, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean firstNameValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_FNAME, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().startsWith(" ")) {
            Global.showToast(activity, AppConstants.NAME_SHOULD_NOT_START_SPACE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().length() < 2) {
            Global.showToast(activity, AppConstants.FNAME_MIN_2, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean lastNameValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_LNAME, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().startsWith(" ")) {
            Global.showToast(activity, AppConstants.NAME_SHOULD_NOT_START_SPACE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().length() < 1) {
            Global.showToast(activity, AppConstants.LNAME_MIN_1, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean ageValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_AGE, 0);
            editText.requestFocus();
            return false;
        }
        if (Integer.parseInt(editText.getText().toString()) < 1 || Integer.parseInt(editText.getText().toString()) > 120) {
            Global.showToast(activity, AppConstants.AGE_BETWEEN_1_AND_120, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean emailValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_EMAIL, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().startsWith(" ")) {
            Global.showToast(activity, AppConstants.EMAIL_SHOULD_NOT_START_SPACE, 0);
            editText.requestFocus();
            return false;
        }
        if (!isValidEmail(editText.getText().toString().trim())) {
            Global.showToast(activity, AppConstants.VALID_EMAIL, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean addressValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_ADDRESS, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().startsWith(" ")) {
            Global.showToast(activity, AppConstants.ADDRESS_SHOULD_NOT_START_SPACE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().length() < 25) {
            Global.showToast(activity, AppConstants.ADDRESS_MIN_25, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean cityValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_CITY, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().startsWith(" ")) {
            Global.showToast(activity, AppConstants.CITY_SHOULD_NOT_START_SPACE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().length() < 2) {
            Global.showToast(activity, AppConstants.CITY_MIN_2, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean stateValidation(Activity activity, EditText editText) {
        if (Global.isNull(editText.getText().toString())) {
            Global.showToast(activity, AppConstants.ENTER_STATE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().startsWith(" ")) {
            Global.showToast(activity, AppConstants.STATE_SHOULD_NOT_START_SPACE, 0);
            editText.requestFocus();
            return false;
        }
        if (editText.getText().toString().length() < 1) {
            Global.showToast(activity, AppConstants.STATE_MIN_1, 0);
            editText.requestFocus();
            return false;
        }
        return true;
    }
}