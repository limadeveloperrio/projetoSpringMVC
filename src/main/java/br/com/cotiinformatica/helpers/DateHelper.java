package br.com.cotiinformatica.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

	// método para receber uma string no formato yyyy-MM-dd
	// e converte-la para um tipo java.util.Date
	public static Date parseDate(String data) {

		// receber a data no formato yyyy-MM-dd
		int ano = Integer.parseInt(data.substring(0, 4));
		int mes = Integer.parseInt(data.substring(5, 7));
		int dia = Integer.parseInt(data.substring(8, 10));

		return new GregorianCalendar(ano, mes - 1, dia).getTime();
	}

	// método para receber um tipo java.util.Date e converte-lo
	// para string no formato que será gravado no MYSQL
	public static String toString(Date data) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(data);
	}

	// método para retornar uma data formatada
	public static String format(Date data, String pattern) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(data);
	}
}
