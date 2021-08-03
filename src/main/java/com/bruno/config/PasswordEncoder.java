package com.bruno.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	public static void main(String[] args) {
		
		System.out.println("Senha encriptada:");
		System.out.print(new BCryptPasswordEncoder().encode("senhateste2"));
	}
}

