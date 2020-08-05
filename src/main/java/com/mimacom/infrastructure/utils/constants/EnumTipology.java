package com.mimacom.infrastructure.utils.constants;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum EnumTipology {
	
	PERSONAL("PERSONAL"),
	PROFESSIONAL("PROFESSIONAL");
	
	private String value;
	
	public static List<String> getListEnumTipology(){
		List<String> listTipologyTask= new ArrayList<>();
		int count=0;		
		EnumTipology[] values = values();		
		while (count<values.length){
			listTipologyTask.add(values[count].getValue());
			count++;
		}		
		return listTipologyTask;
	}
}
