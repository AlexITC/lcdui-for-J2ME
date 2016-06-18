/*
 * @Interface: SoftkeyListener.java
 * @Author: Alexis Hernandez
 * @Version: 1.0
 * @Date: 24/07/2012
 * @Description: Infterfas utilizada para la recepcion de eventos de Softkey
 *
**/

package	com.alx.lcdui;

import javax.microedition.lcdui.Displayable;

public abstract interface SoftkeyListener	{
	public abstract void softkeyAction(Softkey sk, Frame d);
}




