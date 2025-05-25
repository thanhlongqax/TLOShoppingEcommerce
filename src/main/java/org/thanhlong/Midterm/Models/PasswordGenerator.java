package org.thanhlong.Midterm.Models;

import java.security.SecureRandom;

import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "@";

    public static String generateRandomPassword(int length, boolean includeUppercase, boolean includeDigits, boolean includeSpecialChars) {
        StringBuilder password = new StringBuilder();

        // Define the character set based on user preferences
        String characterSet = LOWERCASE;
        if (includeUppercase) {
            characterSet += UPPERCASE;
        }
        if (includeDigits) {
            characterSet += DIGITS;
        }
        if (includeSpecialChars) {
            characterSet += SPECIAL_CHARACTERS;
        }

        SecureRandom random = new SecureRandom();

        // Generate the password
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            password.append(characterSet.charAt(randomIndex));
        }

        return password.toString();
    }
}
