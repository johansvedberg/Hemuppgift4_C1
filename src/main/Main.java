package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		String name = "Kalle";
		String grade = "5";

		TimingAttack attack = new TimingAttack();
		ArrayList<Thread> nbrOfThreads = new ArrayList<Thread>();
		char[] alphabet = "abcdef0123456789".toCharArray();
		System.out.println("Attack underway....");
		StringBuilder b = new StringBuilder();

		for (int j = 0; j < 20; j++) {

			for (int i = 0; i < alphabet.length; i++) {
				Thread t = new Thread(new Processor(attack, alphabet[i], b, name, grade));
				t.start();
				nbrOfThreads.add(t);
			}
			for (Thread t : nbrOfThreads) {
				try {
					t.join();

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
			
			nbrOfThreads.clear();
			
			System.out.print(attack.getMax());
			b.append(attack.getMax());

			attack.clearList();

		}

	}

}
