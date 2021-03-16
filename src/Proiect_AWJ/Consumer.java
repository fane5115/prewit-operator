package Proiect_AWJ;

public class Consumer extends Thread {
	private Buffer buffer;
	
	Consumer( Buffer buffer ) {
		this.buffer = buffer;
	}
	
	public void run() {
		
		int width = Prewitt.inImage.getWidth();
		int height = Prewitt.inImage.getHeight();
		
		for (int i = 0; i < 4; i++) {
			// se imparte in 4 imaginea
			
			int[][] pixelMatrix = new int[width][height]; // se declara o matrice in care vom stoca sfertul de imagine primit
			
			pixelMatrix = buffer.get();// se ia sfertul curent din Buffer
			
			for( int k = 0; k < width; k++ ) 
				for( int j = height/4 * i; j < height/4 * (i+1); j++ ) 
					Prewitt.afterImage.setRGB(k, j, pixelMatrix[k][j]);
					// se seteaza pixelul curent in imaginea ce va fi trimisa spre procesare
			
			System.out.println("Consumatorul a preluat sfertul cu numarul " + (i + 1) + " al imaginii.");
			try {
				sleep(1000);// se executa o secventa de sleep inainte de a trimite urmatorul sfert
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		
	}
	
}
