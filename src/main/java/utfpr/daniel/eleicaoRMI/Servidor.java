/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utfpr.daniel.eleicaoRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class Servidor implements Urna{
    
    private static Map<String, Integer> mapVotos = new HashMap<>();

    //Contrutor
    private Servidor(){};
    
    
        @Override
    public Object contabilizarNovosVotos(Map<String, Integer> map) throws RemoteException {

        Map<String, Integer> modifiedMap = new HashMap<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String modifiedKey = entry.getKey().trim().toUpperCase();
            modifiedMap.put(modifiedKey, entry.getValue());
        }

        // Junta os novos votos com a contagem, somando os value
        modifiedMap.forEach((key, value)
                -> mapVotos.merge(key, value, Integer::sum)
        );

        //Entra objeto, sai objeto
        return modifiedMap;
    }

    private static String imprimirVotos(Map<String, Integer> map){
        // Usando StringBuilder para armazenar a saída em uma String
        StringBuilder sb = new StringBuilder();

        // Itera sobre o mapa e adiciona cada chave-valor à StringBuilder
        map.forEach((key, value) -> sb.append(key).append(" -> ").append(value).append("\n"));

        // Converte o StringBuilder para String
        String result = sb.toString();

        // Agora a saída está armazenada na variável `result`
        System.out.println("Resultado das eleições até o momento:");
        System.out.println(result);
        return result;
    }
    
    
    //Main
    public static void main(String[] args) {
        try {

            Servidor server = new Servidor();

            Urna stub = (Urna) UnicastRemoteObject.exportObject(server, 0);

            Registry registro = LocateRegistry.createRegistry(1099);

            registro.rebind("contabilizarVotos", stub);

            System.out.println("Servidor Urna RMI está pronto!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true){
            try { 
                Thread.sleep(5000);
                imprimirVotos(mapVotos);
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    
    
}
