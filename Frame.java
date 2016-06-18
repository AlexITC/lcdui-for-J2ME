/*
 * @Class: Frame.java
 * @Author: Alexis Hernandez
 * @Version: 2.0
 * @Date: 24/07/2012
 * @Description: 
 *	Clase enfocada a la cracion de Marcos
 *	Donde cada marco puede contener o no softkeys de izquierda y derecha posicion
 *	Puedes definir si quieres los softkeys visible o dibujar en toda la pantalla
 *
**/
package	com.alx.lcdui;

import javax.microedition.lcdui.*;

public abstract class Frame	extends	Canvas	{
	/**
	 * Constante que define el tamaño de la altura de la zona para softkeys
	**/
	public static final int SOFTKEY_HEIGHT = 20;
	public static final int TITLE_HEIGHT = 20;
	/**
	 * Apuntador a las zonas del dispositivo
	**/
	private Image zoneSoftkey;
	private Image zoneTitle;
	private Image zoneFrame;
	
	//
	
	/**
	 * Titulo
	**/
	private String title;
	/**
	 * Color del titulo
	**/
	private int titleForeground = 0;
	/**
	 * Color de fondo para la zona que contiene el titulo
	**/
	private int titleBackground = 0xFFFFFF;
	/**
	 * Apuntador a softkey (left,right)
	**/
	private Softkey leftSoftkey;
	private Softkey rightSoftkey;
	private Softkey centerSoftkey;
	/**
	 * Color de fondo para la zona softkey
	**/
	private int softkeyBackground;
	/**
	 * Define si la zona softkeys estara visible
	**/
	private boolean SOFTKEYS_VISIBLE;
	/**
	 * Apuntador al objeto escuchador de los softkeys
	**/
	private SoftkeyListener listener;
	
	//
	
	/**
	 * Constructor(titulo, mostrar zona softkey)
	**/
	public Frame(String t, boolean showSK)	{
		setFullScreenMode(true);
		softkeyBackground = 0xFFFFFF;
		SOFTKEYS_VISIBLE = showSK;
		//
		int xSize = super.getWidth(), ySize = SOFTKEY_HEIGHT;
		int aux = 0;
		//zona softkeys
		zoneSoftkey = Image.createImage(xSize,ySize);
		//zona titulo
		if	(t != null)	{
			zoneTitle = Image.createImage(xSize,ySize);
			aux += TITLE_HEIGHT;
			title = t;
			drawTitle();
		}	if	(showSK)	aux += TITLE_HEIGHT;
		//zona frame
		ySize = super.getHeight() - aux;
		zoneFrame = Image.createImage(xSize,ySize);
	}
	public void show(Display d)	{
		repaint();
		d.setCurrent(this);
	}
	/**
	 * Obtienen las dimensiones disponibles para dibujar
	**/
	public int getHeight()	{
		return	zoneFrame.getHeight();
	}
	public int getWidth()	{
		return	zoneFrame.getWidth();
	}
	/**
	 * Actualizar dimensiones
	**/
	public void update()	{
		update( 0, 0, getWidth(), super.getHeight() );
	}
	public void update(int x, int y, int sizeX, int sizeY)	{
		if	( title!=null )	y += TITLE_HEIGHT;
		repaint(x,y,sizeX,sizeY);
	}
	/**
	 * Añade el escuchador de los softkeys
	**/
	public void addSoftkeyListener(SoftkeyListener skl)	{	listener = skl;	}
	/**
	 * Añade un softkey al frame
	**/
	public void addSoftkey(Softkey sk)	{
		switch	(sk.getType())	{
			case Softkey.LEFT:	{
				leftSoftkey = sk;
				break;
			}	case Softkey.RIGHT:	{
				rightSoftkey = sk;
				break;
			}	default:	centerSoftkey = sk;;
		}	drawSoftkeys();
	}
	/**
	 * Definen el color de los softkeys
	**/
	public void setSoftkeyBackground(int c)	{	softkeyBackground = c;	}
	public void setSoftkeyForeground(int c)	{
		if	( leftSoftkey!=null )	 leftSoftkey.foreground = c;
		if	( rightSoftkey!=null )	 rightSoftkey.foreground = c;
	}
	public void setSoftkeyShadow(int c)	{
		if	( leftSoftkey!=null )	 leftSoftkey.shadow = c;
		if	( rightSoftkey!=null )	 rightSoftkey.shadow = c;
	}
	/**
	 * Dibujar softkeys
	**/
	private void drawSoftkeys()	{
		Graphics g = zoneSoftkey.getGraphics();
		int x = 0, y = 0;
		int xSize = zoneSoftkey.getWidth(), ySize = zoneSoftkey.getHeight();
		String txt;
		//fondo
		if	(SOFTKEYS_VISIBLE)	{
			g.setColor(0);
			g.drawRect( x, y, xSize, ySize );
			g.setColor(softkeyBackground);
			g.fillRect( 1+x, 1+y, xSize-1, ySize-1 );
		//	System.out.println("softkey zone was draw");
		}
		//left
		if	( leftSoftkey!=null	&&	leftSoftkey.visible )	{
			if	( leftSoftkey.pressed )	g.setColor( leftSoftkey.shadow );
			else	g.setColor( leftSoftkey.foreground );
			txt = leftSoftkey.getLabel();
			g.drawString( txt, 2+x, y, g.TOP  | g.LEFT );
		//	System.out.println("left was draw");
		}
		//right
		if	( rightSoftkey!=null	&&	rightSoftkey.visible )	{
			if	( rightSoftkey.pressed )	g.setColor( rightSoftkey.shadow );
			else	g.setColor( rightSoftkey.foreground );
			txt = rightSoftkey.getLabel();
			g.drawString( txt, xSize-2, y, g.TOP  | g.RIGHT );
		//	System.out.println("right was draw");
		}	repaint( x,  super.getHeight() - SOFTKEY_HEIGHT, xSize, ySize );
	}
	
	//
	
	/**
	 * Si se presiona un softkey, cambia su estado a presionado
	**/
	protected void keyRepeated(int keyCode)	{
	//	keyPressed(keyCode);
	}
	protected void keyPressed(int keyCode)	{
		if	( Softkey.LEFT==keyCode	&&	leftSoftkey!=null )	leftSoftkey.pressed = true;
		else if	( Softkey.RIGHT==keyCode	&&	rightSoftkey!=null )	rightSoftkey.pressed = true;
		else if	( Softkey.CENTER==keyCode	&&	centerSoftkey!=null )	centerSoftkey.pressed = true;
		else	return;
		drawSoftkeys();
	}
	/**
	 * Si se solto un softkey, libera su estado presionado y manda un llamado al escuchador de softkey
	**/
	protected void keyReleased(int keyCode)	{
		if	( Softkey.LEFT==keyCode	&&	leftSoftkey!=null )	{
			doAction(leftSoftkey);
		}	else if	( Softkey.RIGHT==keyCode	&&	rightSoftkey!=null )	{
			doAction(rightSoftkey);
		}	else if	( Softkey.CENTER==keyCode	&&	centerSoftkey!=null )	{
			doAction(centerSoftkey);
		}	else	return;
		drawSoftkeys();
	}
	/**
	 * Cambia el estado presionado a liberado y llama al eschador de softkey
	**/
	private void doAction(Softkey sk)	{
	//	System.out.println("doing an action");
		sk.pressed = false;
		if	( listener!=null )	listener.softkeyAction(sk,this);;
	}
	
	//
	/**
	 * Metodos para la zona del titulo
	**/
	public String getTitle()	{	return	title;	}
	public void setTitleBackground(int c)	{	titleBackground = c;	}
	public void setTitleForeground(int c)	{	titleForeground = c;	}
	private void drawTitle()	{
		if	(title == null)	return;
		Graphics g = zoneTitle.getGraphics();
		int x = 0, y = 0;
		int xSize = zoneTitle.getWidth(), ySize = zoneTitle.getHeight();
		g.setColor(0);
		g.drawRect( x, y, xSize, ySize );
		g.setColor(titleBackground);
		g.fillRect( 1+x, 1+y, xSize-1, ySize-1 );
		g.setColor(titleForeground);
		g.drawString( title, x, y, g.TOP | g.LEFT );
	//	System.out.println("title zone was draw");
	}
	/**
	 * paint
	**/
	protected void paint(Graphics g)	{
		int x, y, xSize, ySize;
		x = y = 0;
		xSize = super.getWidth();
		//dibujar titulo
		if	( title!=null )	{
			ySize = TITLE_HEIGHT;
			drawTitle();
			g.drawImage( zoneTitle, x, y, g.TOP | g.LEFT );
			y += zoneTitle.getHeight();
		}
		//dibujar frame
		paintMe( zoneFrame.getGraphics() );
		if	( zoneFrame!=null )	{
			g.drawImage( zoneFrame, x, y, g.TOP | g.LEFT );
			y += zoneFrame.getHeight();
		}
		//dibujar softkeys
		if	( zoneSoftkey!=null )	{
			g.drawImage( zoneSoftkey, x, y, g.TOP | g.LEFT );
		}
	}
	/**
	 *
	**/
//	public Graphics getGraphics()	{	return	zoneFrame.getGraphics();	}
	/**
	 * Este metodo debe escribirse en al clase hija, este metodo sera llamado para redibujar
	**/
	protected void paintMe(Graphics g)	{}
}




