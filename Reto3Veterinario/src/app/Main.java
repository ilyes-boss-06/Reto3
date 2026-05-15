package app;

import dao.TratamientoDAO;
import modelo.Tratamiento;

public class Main {

	public static void main(String[] args) {
		
		TratamientoDAO tradao = new TratamientoDAO();
		
		for (Tratamiento tradlist: tradao.obtenerPorIdVeterinario(2)) {
			System.out.println(tradlist);
		}

	}

}
