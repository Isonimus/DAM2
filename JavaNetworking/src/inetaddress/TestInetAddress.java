package inetaddress;
import java.net.*;

/**
 * CLASE DE TESTEO DE INETADDRESS
 * (RED)
 * @author Iker Laforga
 *
 */
public class TestInetAddress {
	
	InetAddress address1 = null;
	
	TestInetAddress(){
		
	}
	
	/**
	 * DEVUELVE LA IP ASOCIADA AL HOSTNAME
	 * PASADO POR PARÁMETRO
	 * @param hostName String
	 */
	public void getIPByName(String hostName) {
		
		try {
			
			address1 = InetAddress.getByName(hostName);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(address1.getHostAddress());
	}
	
	/**
	 * DEVUELVE EL HOSTNAME ASOCIADO A LA IP
	 * PASADA POR PARÁMETRO
	 * @param ip String
	 */
	public void getHostFromIP(String ip) {
		
		try {
			
			address1 = InetAddress.getByName(ip);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(address1.getHostName());
	}
	
	/**
	 * DEVUELVE TODAS LAS IPS ASOCIADAS
	 * AL HOSTNAME PASADO POR PARÁMETRO
	 * @param hostName
	 */
	public void getAssociatedIPs(String hostName) {
		
		InetAddress[] host = null;
		
		try {
			
			host = InetAddress.getAllByName(hostName);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		for (InetAddress addr : host) {
			
		    System.out.println(addr.getHostAddress());
		}
	}
	
	/**
	 * DEVUELVE LA IP DE LOCALHOST, Y EL NOMBRE DEL 
	 * EQUIPO
	 */
	public void getLocalHostIP() {
		
		InetAddress localhost = null;
		
		try {
			
			localhost = InetAddress.getLocalHost();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(localhost);
	}

	public static void main(String[] args) {
		
		TestInetAddress t = new TestInetAddress();
		
		t.getIPByName("www.codejava.net");
		t.getHostFromIP("8.8.8.8");
		t.getAssociatedIPs("google.com");
		t.getLocalHostIP();
	}
}
