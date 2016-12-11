package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TimingAttack {

	public TimingAttack() {

		URL url;
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		URLConnection yc;

		StringBuilder a = null;



		

		char valid = 0;

		long longestTime = 0;

		for (int i = 0; i < alphabet.length; i++) {

			long duration = 0;
			long startTime = System.nanoTime();

			for (int j = 0; j < 20; j++) {

				try {
					url = new URL("https://eitn41.eit.lth.se:3119/ha4/addgrade.php?name=Kalle&grade=5&signature=6"
							+ alphabet[i]);
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
			duration = (endTime - startTime) / 20;

			if (duration > longestTime) {
				longestTime = duration;
				valid = alphabet[i];
			}

		}

		System.out.println(valid);
	}



	public void startAttack() {

	}
}
