package model.services;

public class DataFormatter {
	public static String formatCPF(String cpf) {
		StringBuilder sb = new StringBuilder();
		sb.append(cpf.substring(0, 3) + "."); // 000.
		sb.append(cpf.substring(3, 6) + "."); // 000.
		sb.append(cpf.substring(6, 9) + "-" + cpf.substring(9, 11)); // 000-00;
		return cpf = sb.toString();
	}
	
	public static String formatClientName(String name) {
		return "";
	}
	
	public static String formatPhoneNumber(Integer phoneNumber){
		return "";
	}
	
}
