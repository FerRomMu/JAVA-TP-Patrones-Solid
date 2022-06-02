package cazaVinchucas;

public class Basico extends Categoria {

	public Basico() {
	}
	/**
	 * Devuelve un booleano que indica si es usuario experto.
	 * @return
	 */
	public boolean esExperto() {
		return false;
	}
	/**
	 * Devuelve un booleano que indica si es usuario especialista.
	 * @return
	 */
	public boolean esEspecialista() {
		return false;
	}
	/**
	 * Actualiza la categoria del usuario
	 */
	public void recategorizar() {
		//B�sico: para aquellas personas que reci�n comienzan a participar. Un participante nuevo posee nivel b�sico.
		//Experto: son personas que durante los �ltimos 30 d�as desde la fecha actual han realizado m�s de 10 env�os y m�s de 20 revisiones.
	}

}
