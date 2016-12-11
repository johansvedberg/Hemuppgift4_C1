package main;

import java.math.BigInteger;
import java.util.ArrayList;

public class TimingAttack {

	private BigInteger largest;
	char correct;

	public TimingAttack() {
		largest = BigInteger.ZERO;

	}

	public synchronized void clearList() {

		largest = BigInteger.ZERO;

	}

	public synchronized void setCorrect(char c) {
		correct = c;
	}

	public synchronized boolean isMax(BigInteger i) {
		if (i.max(largest) == i) {
			return true;
		} else {
			return false;
		}

	}

	public synchronized String getMax() {
		return Character.toString(correct);
	}

}
