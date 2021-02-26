package org.apache.taglibs.standard.examples.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

/**
 * Customers Datastore.
 *
 * @author Pierre Delisle
 */

public class Customers {

	// *********************************************************************
	// Instance variables

	private static Vector customers = new Vector();
	private static int nextSeqNo = 0;

	// *********************************************************************
	// Datastore operations

	public static void create(String lastName, String firstName, String birthDate, String line1, String line2,
			String city, String state, String zip, String country) {
		create(lastName, firstName, birthDate, line1, line2, city, state, zip, country, null, null);
	}

	/**
	 * Create new customer
	 */
	public static void create(String lastName, String firstName, String birthDate, String line1, String line2,
			String city, String state, String zip, String country, String phoneHome, String phoneCell) {
		Customer customer = new Customer(++nextSeqNo, lastName, firstName, genDate(birthDate),
				genAddress(line1, line2, city, state, zip, country), phoneHome, phoneCell);
		customers.add(customer);
	}

	/**
	 * Find all customers
	 */
	public static Collection findAll() {
		return customers;
	}

	// *********************************************************************
	// Utility methods

	private static Date genDate(String dateString) {
		DateFormat df = new SimpleDateFormat("M/d/y");
		Date date;
		try {
			date = df.parse(dateString);
		} catch (Exception ex) {
			date = null;
		}
		return date;
	}

	private static Address genAddress(String line1, String line2, String city, String state, String zip,
			String country) {
		return new Address(line1, line2, city, state, zip, country);
	}
}