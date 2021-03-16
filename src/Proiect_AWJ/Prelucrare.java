package Proiect_AWJ;

public class Prelucrare extends Abstract {
	
	private int width; //variabila de tip static int
	private int height; //variabila de tip static int
      
	public void setWidth(int width) {
		this.width = width; //setarea campului width
	}
	
	public int getWidth() {
		return this.width; //returnarea campului width
	}
	
	public  void setHeight(int height) {
		this.height = height; //setarea campului height
	}
	
	public int getHeight() {
		return this.height; //returnarea campului height
	}
	
	public void setPerimeter(int width, int heigth) { //metoda care adauga in ambele campuri
		this.width = width; 
		this.height = heigth;
	}
	public void setPerimeter(int latura) { //metoda care adauga aceeasi valoare in ambele campuri pentru a arata polimofismul
		this.height = latura;
		this.width = latura;
	}
	
}
