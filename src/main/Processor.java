package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Processor implements Runnable {
	private TimingAttack attack;
	private char c;
	private StringBuilder b;
	private String name, grade;

	public Processor(TimingAttack attack, char c, StringBuilder b, String name, String grade) {
		this.c = c;
		this.attack = attack;
		this.b = b;
		this.name = name;
		this.grade = grade;
	}

	@Override
	public void run() {
		URL url = null;

		URLConnection yc = null;

		StringBuilder a = null;

		BigInteger duration = BigInteger.ZERO;
		long startTime = System.nanoTime();
		try {
			url = new URL("https://eitn41.eit.lth.se:3119/ha4/addgrade.php?name=" + name + "&grade=" + grade
					+ "&signature=" + b.toString() + c);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int j = 0; j < 50; j++) {

			try {

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
		duration = BigInteger.valueOf((endTime - startTime) / 50);
		// System.out.println(duration);

		if (attack.isMax(duration)) {
			attack.setCorrect(c);
		}

		// attack.addTooList(duration);

	}

}
