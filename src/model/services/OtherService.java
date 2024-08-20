package model.services;

import java.util.Scanner;

import application.UI;
import model.entities.Agency;
import model.excpetion.InvalidDataException;

public abstract class OtherService {
	public static boolean isNumber(String string){
		try {
			Integer.parseInt(string);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
}