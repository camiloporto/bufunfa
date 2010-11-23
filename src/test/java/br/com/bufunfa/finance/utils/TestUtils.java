package br.com.bufunfa.finance.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import br.com.bufunfa.finance.conta.Lancamento;

public class TestUtils {
	
	/*
	 * Fazer maven nao da erro ao executar essa classe como teste
	 */
	@Test
	public void foo() {
		
	}
	
	public static Date parseDate(String dateStr) throws ParseException {
		DateFormat df = DateFormat.getDateInstance();
		return df.parse(dateStr);
	}
	
	public static Date createDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();
	}
	
	public static Lancamento createLancamento(double qtde, Date dataEfetivacao, Date dataRegistro, String descricao) {
		Lancamento l = new Lancamento();
		l.setDataEfetivacao(dataEfetivacao);
		l.setDataRegistro(dataRegistro);
		l.setDescricao(descricao);
		l.setQuantidade(new BigDecimal(qtde));
		
		return l;
	}

}
