package cazaVinchucas.muestras;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Clasificacion;
import cazaVinchucas.Opinion;
import cazaVinchucas.Ubicacion;
import cazaVinchucas.Categoria.Usuario;

class MuestraTestCase {
	private Muestra m;                        // SUT
	private Opinion op2, op3, op4, op5;       // DOC opiniones
	private Usuario ub1, ub2, ue1, ue2, ue3;  // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private Ubicacion dummyUbicacion;     
	

	@BeforeEach
	void setUp() throws Exception {
		
		// mocks de usuario
		ub1 = mock(Usuario.class); ub2 = mock(Usuario.class); ue1 = mock(Usuario.class); 
		ue2 = mock(Usuario.class); ue3 = mock(Usuario.class);
		
		// mocks de opinion 
		op2 = mock(Opinion.class); op3 = mock(Opinion.class); op4 = mock(Opinion.class);
		op5 = mock(Opinion.class);
		
		// retornos de opiniones a getUsuario
        when(op2.getUsuario()).thenReturn(ub2); when(op3.getUsuario()).thenReturn(ue1); 
        when(op4.getUsuario()).thenReturn(ue2); when(op5.getUsuario()).thenReturn(ue3);
		
		// retornos de opiniones a getValor 
		when(op2.getValor()).thenReturn(Clasificacion.VINCHUCA);
		when(op3.getValor()).thenReturn(Clasificacion.PHTIACHINCHE);
		when(op4.getValor()).thenReturn(Clasificacion.PHTIACHINCHE);
		when(op5.getValor()).thenReturn(Clasificacion.POCOCLARA);
		
		// retornos de opiniones a getValor
		when(op2.getUsuario()).thenReturn(ub2); when(op3.getUsuario()).thenReturn(ue1); 
		when(op4.getUsuario()).thenReturn(ue2); when(op5.getUsuario()).thenReturn(ue3); 
		
		// retornos de usuarios para esExperto
		when(ub1.esExperto()).thenReturn(false); when(ub2.esExperto()).thenReturn(false);
	    when(ue1.esExperto()).thenReturn(true);  when(ue2.esExperto()).thenReturn(true);
	    when(ue3.esExperto()).thenReturn(true);
		
		// la muestra se inicia con la opinion de ub1 y su opinion op1.
		m = new Muestra(dummyUbicacion, ub1, "chinche.jpg", Clasificacion.CHINCHEFOLIADA);
	}

	@Test // addOpinion
	void addOpinionAgregaLaOpinionALaMuestra(){
		m.addOpinion(op2);
		assertTrue(m.getOpiniones().contains(op2));
	}
	
	@Test //agregarOpinion
	void agregarOpinionDelegaASuEstadoYAgregaLaOpinion() throws Exception {
		m.agregarOpinion(op2);
		assertTrue(m.getOpiniones().contains(op2));
	}
	
	@Test //agregarOpinion
	void agregarOpinionNoAgregaLaOpinionDeUsuarioQueYaOpino() throws Exception {
		assertEquals(1, m.getOpiniones().size());
		m.agregarOpinion(op2);
		assertEquals(2, m.getOpiniones().size()); // agrega el elemento
		// cuando lo vuelvo a agregar tira exception
		assertThrows(Exception.class, () -> m.agregarOpinion(op2));
	}
	
	@Test //hayOpinionDe
	void hayOpinionDe() {
		assertTrue(m.hayOpinionDe(ub1));
		assertFalse(m.hayOpinionDe(ub2));
	}
	
	@Test //getResultado
	void getResultadoDevuelvePHTIACHINCHE() throws Exception{
		assertEquals(Clasificacion.NINGUNA, m.getResultado());
		m.agregarOpinion(op3); m.agregarOpinion(op4);
		assertEquals(Clasificacion.PHTIACHINCHE, m.getResultado());
	}
	
	
	@Test //setResultado
	void setResultadoLePasoVINCHUCA() {
		assertEquals(Clasificacion.NINGUNA, m.getResultado());
		m.setResultado(Clasificacion.VINCHUCA);
		assertEquals(Clasificacion.VINCHUCA, m.getResultado());
	}
	
	@Test //getUltimoResultado
	void getUltimoResultadoDevuelvePOCOCLARA() throws Exception {
		m.agregarOpinion(op2); m.agregarOpinion(op5);
		assertEquals(Clasificacion.POCOCLARA, m.getUltimoResultado());
	}
	
	@Test //agregarOpinion
	void getOpiniones() throws Exception {
		m.agregarOpinion(op2); m.agregarOpinion(op3);
		ArrayList<Opinion> mismasOpiniones = new ArrayList<Opinion>();
		mismasOpiniones.add(m.getOpiniones().get(0));
		mismasOpiniones.add(op2);
		
		assertFalse(mismasOpiniones.containsAll(m.getOpiniones())); //faltaria op4
		
		mismasOpiniones.add(op3); // se agrega op4
		
		assertTrue(mismasOpiniones.containsAll(m.getOpiniones())); // son iguales
		
	}
	
	@Test //getUbicacion
	void getUbicacion() {
		assertEquals(dummyUbicacion, m.getUbicacion());
	}
	
	@Test //getUsuario
	void getUSuario() {
		assertEquals(ub1, m.getUsuario());
	}
	
	@Test //getFoto
	void getFoto() {
		assertEquals("chinche.jpg", m.getFoto());
	}
	
	@Test //esVerificada
	void esVerificada() throws Exception {
		// todavía no es verificada
		assertFalse(m.esVerificada());
		// ambas opiniones PHTIACHINCHE dejan la muestra verificada.
		m.agregarOpinion(op3); m.agregarOpinion(op4);
		assertTrue(m.esVerificada());
	}
}
