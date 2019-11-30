package com.example.mongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class URL {

	public static String urlDecode(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static Instant convertDate(String date, Instant instant) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return sdf.parse(date).toInstant();
		} catch (ParseException e) {
			return instant;
		}
	}
}
