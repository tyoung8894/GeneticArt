package ppic.util;

import java.util.Random;

public class PredictablyRandom extends Random {
		int index = 0;
		double[] presets;
		
		public PredictablyRandom() {
			presets = new double[10];
			double d = 0;
			for(int i = 0; i< presets.length; i++) {
				presets[i] = d;
				d += 0.1;
			}
		}
		
		public double nextDouble() {
			double toReturn = presets[index];
			index = (index + 1) % presets.length;
			return toReturn;
		}
		
		public int nextInt(int bound) {
			double rand = nextDouble();
		    return (int)(rand * bound);	    
		}
}
