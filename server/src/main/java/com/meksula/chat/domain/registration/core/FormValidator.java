package com.meksula.chat.domain.registration.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author
 * Karol Meksuła
 * 20-07-2018
 * */

public class FormValidator {
    private ChatUserForm chatUserForm;

    private final String USERNAME_PATTERN = "[0-9a-zA-Z]{6,25}";
    private final String EMAIL_PATTERN = ".+@{1}.+\\.[a-z]{2,}";

    public boolean access(ChatUserForm chatUserForm) {
        this.chatUserForm = chatUserForm;

        return usernameMatch() && passwordMatch() && emailMatch();
    }

    private boolean usernameMatch() {
        return checkValue(chatUserForm.getUsername(), USERNAME_PATTERN);
    }

    private boolean passwordMatch() {
        return usernameMatch();
    }

    private boolean emailMatch() {
        return checkValue(chatUserForm.getEmail(), EMAIL_PATTERN);
    }

    private boolean checkValue(final String toCheck, final String pattern) {
        Pattern compilePattern = Pattern.compile(pattern);
        Matcher matcher = compilePattern.matcher(toCheck);
        return matcher.matches();
    }

}
