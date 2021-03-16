package Proiect_AWJ;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Color;

public class Prewitt extends Prelucrare {
	
	public static BufferedImage inImage, afterImage, outImage; //declarare imagine intrare, imaginea dupa thread si imagine de iesire
	private static int[][] pixelMatrix = new int[3][3]; //declarare matrice pentru imagine
	private static int inImgWidth; //latime imagine intrare
	private static int inImgHeight; //lungime imagine intrare
	
	public static void main(String[] args) {
		
		Calcul_Gradient aux = new Calcul_Gradient(); //variabila pentru a calcula gradientul
		
		
		try {
			File inputFile = new File( args[0] ); //memorarea caii imaginii
			
			long timpCitire1 = System.currentTimeMillis();
			inImage = ImageIO.read(inputFile); // memorarea imaginii
			long timpCitire2 = System.currentTimeMillis(); 	
			//Se calculeaza timpul necesar citirii imaginii
			System.out.print("Timpul necesar citirii imaginii este de:  " + (double)(timpCitire2 - timpCitire1)/ 1000	 + " secunde\n");

			inImgWidth = inImage.getWidth(); //memorarea latimii 
			inImgHeight = inImage.getHeight(); //memorarea lungimii
			
			afterImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(), inImage.getType()); 
			
			Buffer buffer = new Buffer(); // am declarat un obiect de tip buffer
			Producer producer = new Producer(buffer, inImage); // am declarat un obiect de tip producer
			Consumer consumer = new Consumer(buffer); // am declarat un obiect de tip consumer
			
			producer.start(); // incepem thread-ul pentru producer
			consumer.start(); // incepem thread-ul pentru consumer
			
			try {
				consumer.join(); // unim thread-ul curent cu cel al consumer-ului pentru a nu continua executia
								// main-ului pana consumerul nu isi termina propria executie
			} catch (InterruptedException e) {
				e.printStackTrace(); // nu s-a reusit unirea celor doua thread-uri
			}
			
			outImage = new BufferedImage( inImgWidth, inImgHeight, BufferedImage.TYPE_INT_RGB );//construim o imagine de afisare cu dimensiunile celei initiale
			long startTime = System.currentTimeMillis();
			//parcurgem imaginea
			for( int i = 1; i < inImgWidth - 1; i++ ) {
				for( int j = 1; j < inImgHeight - 1; j++ ) {
					//punem in matricea pixelMatrix valorile RGB pentru fiecare pixel
					
					pixelMatrix[0][0]=new Color(afterImage.getRGB(i-1, j-1)).getRed();
			        pixelMatrix[0][1]=new Color(afterImage.getRGB(i-1,j)).getRed();
			        pixelMatrix[0][2]=new Color(afterImage.getRGB(i-1,j+1)).getRed();
			        pixelMatrix[1][0]=new Color(afterImage.getRGB(i,j-1)).getRed();
			        pixelMatrix[1][2]=new Color(afterImage.getRGB(i,j+1)).getRed();
			        pixelMatrix[2][0]=new Color(afterImage.getRGB(i+1,j-1)).getRed();
			        pixelMatrix[2][1]=new Color(afterImage.getRGB(i+1,j)).getRed();
			        pixelMatrix[2][2]=new Color(afterImage.getRGB(i+1,j+1)).getRed();
			        
			        int margine = (int) aux.gradient(pixelMatrix); //calculam marginea
			        outImage.setRGB(i, j, ( margine << 16 | margine << 8 | margine ));
				}
			}
			long stopTime = System.currentTimeMillis();
	        System.out.println("Timpul de executie este " + (double)(stopTime - startTime)/ 1000 + " secunde\n");
			
	        File outFile = new File( args[1] );
			
	        long start = System.currentTimeMillis();
			ImageIO.write(outImage, "bmp", outFile); //scriem imaginea
			long stop = System.currentTimeMillis(); 	
			//Se calculeaza timpul necesar scrierii imaginii
			System.out.print("Timpul necesar scrierii imaginii este de:  " + (double)(stop - start)/ 1000 + " secunde\n");
		
		} catch( IOException e ) {
			System.err.println(e);
		}

	}

}
