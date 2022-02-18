package com.pages;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class compare {

	public static void main(String[] args) {

		
		String A[] = {"Tejaswini", "Tejas"};
		String B[] = {"Tejas"};


		List <String> a =Arrays.asList(A);
		List <String> b =Arrays.asList(B);
			
	

		Set<String> s = new HashSet<String>(a);
		
		s.removeAll(b);
		System.out.println(s);


	

	}

}
