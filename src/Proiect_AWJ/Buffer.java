package Proiect_AWJ;

public class Buffer {
	
	private int[][] pixelMatrix; //declaram o matrice de tip int
	private boolean busy = false; //declaram un flag pentru a vedea daca buffer-ul este sau nu ocupat
	
	public synchronized int[][] get() {
		while( !busy ) { //cat timp bufferul nu este ocupat asteapta
			try {
				wait();
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
		busy = false;
		notifyAll();
		return this.pixelMatrix;
	}
	
	public synchronized void put( int[][] pixelMatrix ) {
		while( busy ) { //cat timp bufferul este ocupat bufferul asteapta
			try {
				wait();
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
		this.pixelMatrix = pixelMatrix;
		busy = true;
		notifyAll();
	}
	
}
