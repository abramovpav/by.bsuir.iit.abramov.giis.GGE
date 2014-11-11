package by.bsuir.iit.abramov.giis.GGE.utils;

public class Matrix {
	private double matrix[][];
	private int width;
	private int height;
	
	public Matrix(double matrix[][], int width, int height) {
		this.matrix = matrix;
		this.width = width;
		this.height = height;
	}
	
	public Matrix(Matrix matrix) {
		this.matrix = matrix.getMatrix();
		this.height = matrix.getHeight();
		this.width = matrix.getWidth();
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public double[][] getMatrix() {
		return matrix;
	}
	
	public Matrix mult(Matrix in_matrix) {
		double matrix2[][] = in_matrix.getMatrix();
		int width = in_matrix.getWidth();
		int height = this.height;
		int r = this.width;
		double result[][] = new double[height][];
		for (int i = 0; i < height; i++) {
			result[i] = new double[width];
		}
		
		for (int i = 0; i< height; i++) {
			for (int j = 0; j < width; j++) {
				double sum = 0;
				for (int k = 0; k < r; k++) {
					sum += this.matrix[i][k] * matrix2[k][j];
				}
				result[i][j] = sum;
			}
		}
		
		return new Matrix(result, width, height);
	}
	
	public void set(int row, int col, double value) {
		matrix[row][col] = value;
	}
}
