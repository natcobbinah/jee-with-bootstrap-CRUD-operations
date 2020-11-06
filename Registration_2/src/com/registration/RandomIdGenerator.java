package com.registration;

import java.util.Random;

public class RandomIdGenerator {
	
	public static int getId() {
		Random rd = new Random();
		int y = rd.nextInt(100000);
		return y;
		
	}

}
