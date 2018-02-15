package com.matias.api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.matias.api.dto.EstadisticaDTO;

public final class ApiUtil {

	public static void setRatio(EstadisticaDTO estadistica) {
		Float ratio = 0f;
		Float cantHumanos = estadistica.getCantidadHumanos().floatValue();
		Float cantMutantes = estadistica.getCantidadMutantes().floatValue();
		if (cantHumanos <= cantMutantes && cantMutantes != 0) {
			ratio = cantHumanos / cantMutantes;
		} else if (cantHumanos > cantMutantes && cantHumanos != 0) {
			ratio =  cantMutantes / cantHumanos;
		}
		estadistica.setProporcion(round(ratio, 2));
	}
	
	private static Float round(Float value, int places) {
		if (value == 0f) {
			return value;
		}
	    BigDecimal numero = new BigDecimal(value);
	    numero = numero.setScale(places, RoundingMode.HALF_UP);
	    return numero.floatValue();
	}
	
}
