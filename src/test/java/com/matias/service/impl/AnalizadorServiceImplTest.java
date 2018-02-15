package com.matias.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.matias.dao.DnaDAO;
import com.matias.dao.error.DBException;
import com.matias.dao.impl.DnaHumanoDAOImpl;
import com.matias.dao.impl.DnaMutanteDAOImpl;
import com.matias.service.AnalizadorService;
import com.matias.service.dto.DnaBean;
import com.matias.service.error.ServiceException;
import com.matias.service.type.TipoDna;

public class AnalizadorServiceImplTest {
	
	@Mock
	private List<DnaDAO> dnaDaos;
	
	@Mock
	private DnaHumanoDAOImpl dnaHumanoDao;
	
	@Mock
	private DnaMutanteDAOImpl dnaMutanteDao;

	@InjectMocks
	private AnalizadorService analizadorService = new AnalizadorServiceImpl();
	
	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(dnaDaos.stream()).thenReturn(Arrays.asList(dnaHumanoDao,dnaMutanteDao).stream());
		when(dnaHumanoDao.support()).thenReturn(TipoDna.HUMANO);
		when(dnaMutanteDao.support()).thenReturn(TipoDna.MUTANTE);
	}
	
	@Test
	public void testEsMutante_ConAdnHumano_callSave() throws ServiceException, DBException {
		analizadorService.esMutante(new DnaBean(new String[0]));
		verify(dnaHumanoDao, times(1)).save(any());
	}
	
	@Test(expectedExceptions = ServiceException.class)
	public void testTipoDna_ConAdnNulo_retornaException() throws ServiceException {
		analizadorService.tipoDna(new DnaBean(null));
	}
	
	@Test(expectedExceptions = ServiceException.class)
	public void testTipoDna_conTodoNulo_retornaException() throws ServiceException {
		analizadorService.tipoDna(null);
	}
	
	@Test
	public void testTipoDna_ConAdnMutante_retornaMutante() throws ServiceException {
		
		String[] dnaMutante1 = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		assertEquals(analizadorService.tipoDna(new DnaBean(dnaMutante1)),TipoDna.MUTANTE);
		
		String[] dnaMutanteVertical = {"ATGA","CAGA","TTTA","ACAA"};
		assertEquals(analizadorService.tipoDna(new DnaBean(dnaMutanteVertical)),TipoDna.MUTANTE);
		
		String[] dnaMutanteHorizontal = {"ATGA","CAGA","TTTC","AAAA"};
		assertEquals(analizadorService.tipoDna(new DnaBean(dnaMutanteHorizontal)),TipoDna.MUTANTE);
		
		String[] dnaMutanteDiagonal = {"ATGA","CAGA","TTAC","AATA"};
		assertEquals(analizadorService.tipoDna(new DnaBean(dnaMutanteDiagonal)),TipoDna.MUTANTE);
		
		String[] dnaMutanteDiagonal2 = {"TTGA","CAAA","TACC","AATA"};
		assertEquals(analizadorService.tipoDna(new DnaBean(dnaMutanteDiagonal2)),TipoDna.MUTANTE);
	}
	
	@Test
	public void testTipoDna_ConAdnHumano_retornaHumano() throws ServiceException {
		String[] dnaHumanoMenor4 = {"ATG","CAG","TTT"};
		assertEquals(analizadorService.tipoDna(new DnaBean(dnaHumanoMenor4)),TipoDna.HUMANO);
		
		String[] dnaHumano = {"ATGA","CAGA","TTAC","AATC"};
		assertEquals(analizadorService.tipoDna(new DnaBean(dnaHumano)),TipoDna.HUMANO);
	}


}
