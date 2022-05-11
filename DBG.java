package com.asint.tools;

import java.lang.Thread;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class DBG {
	
	protected static StackTraceElement[] _tEle = Thread.currentThread().getStackTrace();
	protected static String _txt;
	
	private static void setStr() {
		_txt = "\r\nON " +
				Thread.currentThread().getName() + "(" + 
				Thread.currentThread().getId() + ") AT " +
				_tEle[2].getClassName() + "." + 
				_tEle[2].getMethodName() + ":" + 
				_tEle[2].getLineNumber() +  " >>>> \r\n";
	}

	public static void ln(String ...args) {
		
		if (args != null && args.length > 0) {
			setStr();
			
			for (String arg: args) {
				_txt += arg + " : ";
			}
			_txt += "\r\n";
			
			System.err.println(_txt);
			return;
		}
	}
	
	public static void obj(Object obj) {
		
		if (obj != null && obj instanceof Object) {
			setStr();
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				_txt += mapper.writeValueAsString(obj) + "\r\n";
				
				System.err.println(_txt);
			} catch (JsonProcessingException e) {
				_txt += "Could not print!";
				
				System.err.println(_txt);
				e.printStackTrace();
			}
		}
	}
	
	public static void lst(List<?> list) {
		
		// TODO: Add support for ?, other than java standard types
		if (list != null && list.size() > 0) {
			setStr();
			
			_txt += Arrays.toString(list.toArray());
			
			System.err.println(_txt);
		}
	}
}
