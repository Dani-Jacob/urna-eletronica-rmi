package utfpr.daniel.eleicaoRMI;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;


public interface Urna extends Remote {
    
    Object contabilizarNovosVotos(Map<String, Integer> map) throws RemoteException;
    
    //String imprimirVotos(HashMap map) throws RemoteException;
    
}
