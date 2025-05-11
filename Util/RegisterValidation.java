package Util;

public static class RegisterValidation {

	public static boolean isValidPassword(String password) {
		if(password.length() >= 8 && password.length() <= 30) {
            return true;
        } else {
            System.out.println("Password length must be between 8 and 30 characters");
            return false;
        }
	}

	public static boolean isValidEmail(String email) {
		if(email.matches("[^@]+@[^@]+\\.[^@][^\\.]+")) {
            return true;
        } else {
            System.out.println("Email must be in the format 'email@domain.domain' (Example: hi@email.com)");
            return false;
        }
	}

}
