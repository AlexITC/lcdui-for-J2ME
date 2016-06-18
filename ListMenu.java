/*
 * @Class: Panel.java
 * @Author: Alexis Hernandez
 * @Version: 2.0
 * @Date: 28/04/2012
 * @Description: 
 * 		Lista simple en pantalla completa con opcion 'ok' y 'atras'
 *
**/
package	com.alx.lcdui;

import javax.microedition.lcdui.*;

public class ListMenu	extends	Frame	{
	/**
	 * Elemento iluminado
	**/
	private int currentItem;
	/**
	 * Numero de elementos
	**/
	private int size;
	/**
	 * Elementos
	**/
	private String items [];
	
	private int BACKGROUND_COLOR;
	private int FOREGROUND_COLOR;
	private int FOCUS_COLOR;
	
	/**
	 * Constructor que un String que sera el titulo de la lista
	**/
	public ListMenu(String title)	{
		super(title,true);
		items = new String [8];
		currentItem = size = 0;
		//
		//
		FOREGROUND_COLOR = 0;
		BACKGROUND_COLOR = 0xFFFFFF;
		FOCUS_COLOR = 0xC0C0C0;
	}
	
	/**
	 * 
	**/
	public void append(String s)	{
		items[size++] = s;
		update();
	}
	/**
	 * Constructor que un String que sera el titulo de la lista
	**/
	public int getFocusedItemIndex()	{
		return	currentItem;
	}
	public void setFocusedItemIndex(int i)	{
		currentItem = i;
		update();
	}

	public void show(Display d)	{
		currentItem = 0;
		super.show(d);
	}
	/*
	 * Dibuja la lista
	**/
	public void paintMe(Graphics g) 	{
		//marco
		g.setColor(FOREGROUND_COLOR);
		g.drawRect( 0, 0, getWidth(), getHeight() );
		//fondo
		g.setColor(BACKGROUND_COLOR);
		g.fillRect( 1, 1, getWidth() -1, getHeight() -1 );
		//
		//Foco
		if	( size == 0 )	return;
		g.setColor(FOCUS_COLOR);
		g.fillRect( 4, 5 + (20*currentItem), getWidth() -2, 15 );
		//elementos
		g.setColor(FOREGROUND_COLOR);
		for	(int i=0;	i<size;	i++)	{
			g.drawString( items[i], 5, 5 + (20*i), g.TOP | g.LEFT );
		}
		
	}
	/*
	 * Al presionar una tecla
	**/
	public void keyRepeated(int keyCode)	{
		super.keyRepeated(keyCode);
		keyPressed(keyCode);
	}
	public void keyPressed(int keyCode)	{
		super.keyPressed(keyCode);
	//	System.out.println("Se presiono una tecla " + keyCode);
		switch	( getGameAction(keyCode) )	{
			//arriva
			case KEY_NUM2:
			case UP:	{
				if	( currentItem==0 )	currentItem = size - 1;
				else	currentItem --;
				break;
			}	//abajo
			case KEY_NUM8:
			case DOWN:	{
				if	( currentItem==size - 1 )	currentItem = 0;
				else	currentItem ++;
				break;
			}	default:	return;
		}	update();
	}
}







