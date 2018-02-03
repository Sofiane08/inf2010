import java.awt.PageAttributes.ColorType;

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
		// complï¿½ter
		for(int row = 0; row < this.height; row++)
			for(int col = 0; col < this.width; col++)
				imageData[row][col].Negative();
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// complï¿½ter
		for(int row = 0; row < this.height; row++)
			for(int col = 0; col < this.width; col++)
				imageData[row][col].toBWPixel();	
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// complï¿½ter
		for(int row = 0; row < this.height; row++)
			for(int col = 0; col < this.width; col++)
				imageData[row][col].toGrayPixel();
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// complï¿½ter
		for(int row = 0; row < this.height; row++)
			for(int col = 0; col < this.width; col++)
				imageData[row][col].toColorPixel();
	}
	
	public void convertToTransparentImage()
	{
		// complï¿½ter
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
		// complï¿½ter
		double cos = Math.cos(angleRadian);
		double sin = Math.sin(angleRadian);
		
		AbstractPixel[][] imageDataTemp = new AbstractPixel[height][width];
 		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				double oldX = cos*col + sin*row + (x - x * cos - y*sin);
				double oldY = -1*sin*col + cos*row + (y - y*cos + x*sin);
				
		        if (!(oldX >= width || oldX < 0 || oldY < 0 || oldY >= height - 1)) {
		        	imageDataTemp[row][col] = imageData[(int)oldY][(int)oldX];
		        }
		        else {	//On créé un pixel blanc du bon type
		        	if(imageType == ImageType.BW){
		        		imageDataTemp[row][col] = new BWPixel();
		        	}
		        	else if(imageType == ImageType.Gray){
		        		imageDataTemp[row][col] = new GrayPixel();
		        	}
		        	else if(imageType == ImageType.Color){
		        		imageDataTemp[row][col] = new ColorPixel();
		        	}
		        	else if(imageType == ImageType.Transparent){
		        		imageDataTemp[row][col] = new TransparentPixel();
		        	}
		        }
			}
		}
		
		imageData = imageDataTemp;
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
		
		// complï¿½ter
		
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// complï¿½ter
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
		// complï¿½ter		
		
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// complï¿½ter		
		
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
		
		// complï¿½ter
		
	}
}
