package utils;

import java.util.Random;


public class RandomGenerator {


	public static String getRandomString(String pre) {
		StringBuilder user = new StringBuilder(pre);
		Random random = new Random();

		for (int i = 0; i < 5; i++) {
			int numeroAleatorio = random.nextInt(10);
			user.append(numeroAleatorio);
		}

		return user.toString();
	}

	public static String getRandomEmail(String user) {
		StringBuilder email = new StringBuilder(user);
		Random random = new Random();

		String[] domains = { "gmail.com", "yahoo.com", "outlook.com", "example.com" };
		String randomDomain = domains[random.nextInt(domains.length)];

		// Combine the prefix and domain to form the email
		email.append("@").append(randomDomain);

		return email.toString();
	}

}
