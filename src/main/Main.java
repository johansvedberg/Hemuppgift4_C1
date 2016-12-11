package main;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		TimingAttack attack = new TimingAttack();
		ArrayList<Thread> nbrOfThreads = new ArrayList<Thread>();
		char[] alphabet = "abcdef0123456789".toCharArray();
		System.out.println("Attack underway....");
		StringBuilder b = new StringBuilder();

		for (int j = 0; j < 20; j++) {

			for (int i = 0; i < alphabet.length; i++) {
				Thread t = new Thread(new Processor(attack, alphabet[i], b));
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
			
			System.out.print(attack.getMax());
			b.append(attack.getMax());
			
			attack.clearList();

		}

	}

}
