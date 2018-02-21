package com.matias.service.mapper;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import com.matias.service.error.ServiceException;

public class DnaMapperTest {
	
	@Test
	public void testAplanar_impares() throws ServiceException {
		DnaMapper mapper = new DnaMapper();
		assertEquals(mapper.aplanar((String[]) Arrays.asList("A","A").toArray()), "A,A"); 
		assertEquals(mapper.aplanar((String[]) Arrays.asList("AAA","AAA","AAA").toArray()), "AAA,AAA,AAA"); 
	}
	
	@Test
	public void testAplanar_pares() throws ServiceException {
		DnaMapper mapper = new DnaMapper();
		assertEquals(mapper.aplanar((String[]) Arrays.asList("").toArray()), ""); 
		assertEquals(mapper.aplanar((String[]) Arrays.asList("AA","AA").toArray()), "AA,AA"); 
	}
	
	@Test
	public void testAplanar_mezclados() throws ServiceException {
		DnaMapper mapper = new DnaMapper();
		assertEquals(mapper.aplanar((String[]) Arrays.asList("").toArray()), ""); 
		assertEquals(mapper.aplanar((String[]) Arrays.asList("A","BB").toArray()), "A,BB"); 
		assertEquals(mapper.aplanar((String[]) Arrays.asList("A","","BBB").toArray()), "A,,BBB"); 
	}

}
