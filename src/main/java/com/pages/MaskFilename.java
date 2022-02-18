package com.pages;

import org.apache.commons.codec.binary.Base64;

public class MaskFilename {


	public static void main(String[] args) {

		String s = "Aasv";
		byte[] encode = 	Base64.encodeBase64(s.getBytes());
		System.out.println(new String(encode));


	}



}
