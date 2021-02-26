package ejemplo.rest;

import java.math.BigInteger;
import java.util.Random;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

//Llamada:
//http://localhost:8080/EjemploRest/rest/generadornumeros/rnd



	@Path("/generadornumeros")
	public class GeneradorNumeros {

		@GET
		@Path("/rnd")	
		@Produces(MediaType.APPLICATION_JSON)
		/**
		 * genera y retorna un numero aleatorio
		 * 
		 * @return Response
		 */
		public Response generateRndNumber() {
			Random rnd = new Random();
			return Response
					.ok(response("Numero Aleatorio", "", String.valueOf(rnd.nextDouble())), MediaType.APPLICATION_JSON)
					.build();
		}

		@GET
		@Path("/fibo/{value}")
		@Produces(MediaType.APPLICATION_JSON)
		/**
		 * Sucesion de fibonacci
		 * 
		 * @param value
		 *            numero entero
		 * @return Response
		 */
		public Response getFibo(@PathParam("value") int value) {
			if (value <= 0) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(response("Fibonacci", String.valueOf(value), "El numero debe ser mayor que cero"))
						.build();
			}
			int fibo1 = 1;
			int fibo2 = 1;
			int aux = 1;
			String cadena = "1";
			for (int i = 2; i <= value; i++) {
				fibo2 += aux;
				aux = fibo1;
				fibo1 = fibo2;
				cadena += " " + aux;
			}
			return Response.ok(response("Fibonacci", String.valueOf(value), cadena), MediaType.APPLICATION_JSON)
					.build();
		}
		
		@GET
		@Path("/fibomax/{value}")
		@Produces(MediaType.APPLICATION_JSON)
		/**
		 * Sucesion de fibonacci
		 * 
		 * @param value
		 *            numero entero
		 * @return Response
		 */
		public Response getFiboMax(@PathParam("value") int value) {
			if (value <= 0) {
				return Response.status(Response.Status.BAD_REQUEST)
						.entity(response("Fibonacci", String.valueOf(value), "El numero debe ser mayor que cero"))
						.build();
			}
			BigInteger fibo1 = new BigInteger("1");
			BigInteger fibo2 = new BigInteger("1");
			BigInteger aux = new BigInteger("1");
			String cadena = "1";
			for (int i = 2; i <= value; i++) {
				fibo2 = fibo2.add(aux);
				aux = new BigInteger(fibo1.toByteArray());
				fibo1 = new BigInteger(fibo2.toByteArray());
				cadena += " " + aux;
			}
			return Response.ok(response("Fibonacci", String.valueOf(value), cadena), MediaType.APPLICATION_JSON)
					.build();
		}

		/**
		 * metodo privado para dar formato al JSON de respuesta
		 * 
		 * @param operation
		 *            Operacion que se realiza en el APIREST
		 * @param paramater
		 *            parametro de entrada
		 * @param result
		 *            resultado de la operacion realizada
		 * @return String Respuesta en formato JSON
		 */
		private String response(String operation, String parameter, String result) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("operation", operation);
				obj.put("parameter", parameter);
				obj.put("result", result);
				return obj.toString(4);
			} catch (JSONException ex) {
				System.err.println("JSONException: " + ex.getMessage());
			}
			return "";
		}

	}