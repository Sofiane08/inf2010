import java.awt.PageAttributes.ColorType;

import PixelMap.ImageType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : Jacob Dorais(1879536) et Francois-Xavier Legault()
 * @date : 25-01-2018
 */
public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		// compl�ter
		for(int row = 0; row < this.height; row++)
			for(int col = 0; col < this.width; col++)
				imageData[row][col].Negative();
		
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// compl�ter
		for(int row = 0; row < this.height; row++)
			for(int col = 0; col < this.width; col++)
				imageData[row][col].toBWPixel();	
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// compl�ter
		for(int row = 0; row < this.height; row++)
			for(int col = 0; col < this.width; col++)
				imageData[row][col].toGrayPixel();
		
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// compl�ter
		for(int row = 0; row < this.height; row++)
			for(int col = 0; col < this.width; col++)
				imageData[row][col].toColorPixel();
		
	}
	
	public void convertToTransparentImage()
	{
		// compl�ter
		for(int row = 0; row < this.height; row++)
			for(int col = 0; col < this.width; col++)
				imageData[row][col].toTransparentPixel();
		
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// compl�ter
		
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		// compl�ter
		
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// compl�ter
		for(int row = row0; row < (row0 + pm.height) && row < this.height; row++)
		{
			for(int col = col0; (col < pm.width) && col < this.width; col++)
				{
					if( imageType == ImageType.BW )
						imageData[row][col] = ( pm.getPixel(row, col) ).toBWPixel();
					else if( imageType == ImageType.Gray )
						imageData[row][col] = ( pm.getPixel(row, col) ).toGrayPixel();
					else if( imageType == ImageType.Color )
					{					
						imageData[row][col] = ( pm.getPixel(row, col) ).toColorPixel();
					}
					else
					{
						imageData[row][col] = ( pm.getPixel(row, col) ).toTransparentPixel();
					}
				}
		}
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		// compl�ter		
		
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// compl�ter		
		
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		// compl�ter
		
	}
}
