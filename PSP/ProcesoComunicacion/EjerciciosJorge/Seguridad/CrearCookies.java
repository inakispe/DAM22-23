package Seguridad;

import java.io.*;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CrearCookies {

	/*
	 * Crear primero una cookie
	 */
	public static void saveCookie(String cookieName, String value, int maxAge, HttpResponse response) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
	/*
	 * Aqui vamos a mandar nuestro cookie y ver si nos devuelve una cookie igual
	 */
	public static String getCookieValue(String cookieName, HttpRequest request) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			int i = 0;
			boolean cookieExists = false;
			while (!cookieExists && i < cookies.length) {
				if (cookies[i].getName().equals(cookieName)) {
					cookieExists = true;
					value = cookies[i].getValue();
				} else {
					i++;
				}
			}
		}
		return value;
	}
}
