package Proiect_AWJ;

import java.awt.image.BufferedImage;

public class Producer extends Thread {
	
	private Buffer buffer; //variabila de tip buffer
	private BufferedImage Image; //variabila de tip image
	
	Producer( Buffer buffer, BufferedImage Image ) {
		this.buffer = buffer;
		this.Image = Image;
	}
	
	public void run() {
		int width = Image.getWidth(); 
		int height = Image.getHeight();
		
		for( int i = 0; i < 4; i++ ) {
			//imparitm imaginea in 4
			int[][] pixelMatrix = new int[width][height]; //declaram o matrice pentru a o trimite la consumer
			
			for( int k = 0; k < width; k++ ) 
				for( int j = height/4 * i; j < height/4 * (i+1); j++ ) 
					pixelMatrix[k][j] = Image.getRGB(k,j); //adaugam in matrice cate un sfert
			buffer.put(pixelMatrix); //adaugam matricea in buffer pentru a o prelucra consumerul
			
			System.out.println("Producatorul a pus sfertul cu numarul " + (i + 1) + " al imaginii.");
			try {
				sleep(1000);
			} catch( InterruptedException e ) {
				System.out.println(e);
			}
		}
		
	}
	
}
