/*
 * @Class: Softkey.java
 * @Author: Alexis Hernandez
 * @Version: 1.2
 * @Date: 28/07/2012
 * @Description:
 *	Clase creada para el manejo de comandos con tecla de accion derecha y tecla de accion izquierda
 *	Se puede definir el color del texto cuando este el softkey presionado o estatico
 *	Se puede detectar el softkey con su identificador (id)
 *
**/
package	com.alx.lcdui;

public class Softkey	{
	/**
	 * Constantes que definen el tipo de softkey
	**/
	public static final int LEFT	= -6;
	public static final int CENTER	= -5;
	public static final int RIGHT	= -7;
	
	/****/
	
	/**
	 * Define si el softkey esta presionado
	**/
	public boolean pressed;
	/**
	 * Define si el softkey esta visible
	**/
	public boolean visible;
	/**
	 * Color de la etiqueta
	**/
	public int foreground;
	/**
	 * Color de la etiqueta cuando esta presionado el softkey
	**/
	public int shadow;
	/**
	 * Etiqueta del softkey
	**/
	private String label;
	/**
	 * Tipo de softkey
	**/
	private int type;
	/**
	 * Identificador del softkey
	**/
	private int id;
	/**
	 * Contador de softkeys creados
	**/
	private static int softkeys = 0;
	
	/****/
	
	/**
	 * Constructor(Etiqueta, tipo, color de texto, color de sombra)
	**/
	public Softkey(String txt, int t, int id)	{
		label = txt;
		type = t;
		this.id = id;
		foreground = 0;	//Negro
		shadow = 0xC0C0C0;	//gris
		pressed = false;
		visible = true;
		synchronized (this)	{	softkeys++;	}
	}
	/**
	 * Metodos que obtienen los atributos del softkey
	**/
	public String getLabel()	{	return	label;	}
	public int getType()	{	return	type;	};
	public int getID()	{	return	id;	};
	//
}




