package main;

import java.math.BigInteger;
import java.util.ArrayList;

public class TimingAttack {
	private StringBuilder b;
	private ArrayList<BigInteger> list;
	private BigInteger largest;
	char correct; 

	public TimingAttack() {
		largest = BigInteger.ZERO;
		list = new ArrayList<BigInteger>();

	}

	public synchronized void addTooList(BigInteger i) {
		list.add(i);

	}

	public synchronized void clearList() {
		list.clear();
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

	public void getMax() {
		System.out.println(correct);
	}
	

}
