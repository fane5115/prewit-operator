package Proiect_AWJ;

public class Calcul_Gradient implements Gradient {

	@Override
	public double gradient(int[][] pixelMatrix) {
		
		int gx=(pixelMatrix[0][0]*(-1))+(pixelMatrix[0][1]*(-1))+(pixelMatrix[0][2]*(-1))+(pixelMatrix[2][0]*1)+(pixelMatrix[2][1]*1)+(pixelMatrix[2][2]*1);//masca orizontala
		int gy=(pixelMatrix[0][0]*(-1))+(pixelMatrix[0][2]*1)+(pixelMatrix[1][0]*(-1))+(pixelMatrix[1][2]*1)+(pixelMatrix[2][0]*(-1))+(pixelMatrix[2][2]*1);//masca vertical
	    
		double gradient = Math.sqrt(Math.pow(gy, 2)+Math.pow(gx, 2)); //calcularea gradientului
	    
	   	return gradient;
	}
	
}
