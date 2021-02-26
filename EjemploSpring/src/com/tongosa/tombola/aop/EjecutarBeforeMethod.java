package com.tongosa.tombola.aop;

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;

public class EjecutarBeforeMethod implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target) throws Throwable {
		String nombreMetodo = method.getName();
		if (nombreMetodo.equals("conectar")) {
			System.out.println("Ejecutado antes del método " + nombreMetodo);
			System.out.println("EJECUTANDO UN ASPECTO");
		}
		if (nombreMetodo.equals("desconectar")) {
			System.out.println("Ejecutado antes del método " + nombreMetodo);
			System.out.println("EJECUTANDO UN ASPECTO");
		}
	}
}
