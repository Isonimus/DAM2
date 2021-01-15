/**
 * Calculadora.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package calculadora;

public interface Calculadora extends java.rmi.Remote {
    public int sumar(int a, int b) throws java.rmi.RemoteException;
    public int restar(int a, int b) throws java.rmi.RemoteException;
    public int multiplicar(int a, int b) throws java.rmi.RemoteException;
    public float dividir(int a, int b) throws java.rmi.RemoteException;
}
