package cazaVinchucas.Categoria;

/**
 * Clase encargada de representar el comportamiento particular de una 
 * categoria de usuario experto.
 * 
 * @author ivanapr
 *
 */

public class Experto extends Categoria {	
	/**
	 * Devuelve un booleano que indica si es usuario experto.
	 * @return
	 */
	public boolean esExperto() {
		return true;
	}
	/**
	 * Actualiza la categoria del usuario
	 */
	public void recategorizar() {
		//B�sico: para aquellas personas que reci�n comienzan a participar. Un participante nuevo posee nivel b�sico.
		//Experto: son personas que durante los �ltimos 30 d�as desde la fecha actual han realizado m�s de 10 env�os y m�s de 20 revisiones.
	}

}
