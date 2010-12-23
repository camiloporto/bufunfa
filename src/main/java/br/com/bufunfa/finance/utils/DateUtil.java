/**
 * 
 */
package br.com.bufunfa.finance.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe utilitaria para algumas operacoes
 * de datas
 * 
 * @author camilo
 *
 */
public class DateUtil {
	
	public final static Date MINIMUM_DATE;
	
	
	static {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.MONTH, c.getMinimum(Calendar.MONTH));
		c.set(Calendar.YEAR, c.getMinimum(Calendar.YEAR));
		MINIMUM_DATE = c.getTime();
	}
	
	/**
	 * Retorna a data informada subtraida de 1 dia
	 * @param date a data
	 * @return data informada subtraida de 1 dia
	 */
	public static Date getDiaAnterior(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		c.add(Calendar.DAY_OF_MONTH, -1);
		
		return c.getTime();
	}
	
	/**
	 * Atribui aos valores de hora, minuto, segundo
	 * e milisegundo de uma data o valor minimo
	 * possivel para eles
	 * @param date a data
	 * @return data com valores de hora, minuto, segundo, e 
	 * milisegundo minimos
	 */
	public static Date minimizeDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		c.set(Calendar.HOUR_OF_DAY, c.getMinimum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.getMinimum(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.getMinimum(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, c.getMinimum(Calendar.MILLISECOND));
		
		return c.getTime();
	}
	
	/**
	 * Atribui aos valores de hora, minuto, segundo
	 * e milisegundo de uma data o valor minimo
	 * possivel para eles
	 * @param date a data
	 * @return data com valores de hora, minuto, segundo, e 
	 * milisegundo minimos
	 */
	public static Date maximizeDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		c.set(Calendar.HOUR_OF_DAY, c.getMaximum(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, c.getMaximum(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.getMaximum(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, c.getMaximum(Calendar.MILLISECOND));
		
		return c.getTime();
	}
	
	/**
	 * Verifica se duas datas sao iguais, 
	 * comparando apenas os atributos dia mes e ano
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isDayMonthYearEqual(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		
		return c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH) &&
				c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
				c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
	}

}
