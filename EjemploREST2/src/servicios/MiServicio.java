package servicios;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

@Path("/miservicio")
public class MiServicio {

	@GET
	@Path("/hola")
	@Produces(MediaType.APPLICATION_JSON)
	public Response diHola() {
		String cadena = "hola!!!";
		return Response.ok(response("Hola", "", String.valueOf(cadena)), MediaType.APPLICATION_JSON).build();
	}

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
