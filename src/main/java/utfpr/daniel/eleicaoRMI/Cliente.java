package utfpr.daniel.eleicaoRMI;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    private static Map lerVotos() {
        
                Map<String, Integer> votos = new HashMap<>();
                String numeroCandidato = "";
                int quantidadeVotos = 0;
                
                while(numeroCandidato == "" || quantidadeVotos <= 0){                    
                    try {
                        System.out.print("Digite o número do candidato: ");
                        numeroCandidato = reader.readLine();
                        //Verifica se é para encerrar a aplicação
                        if("SAIR".equalsIgnoreCase(numeroCandidato)) return votos;
                        System.out.print("Digite a quantidade de votos (ou -1 para corrigir o número): ");
                        String aux = reader.readLine();
                        //Verifica se é para encerrar a aplicação
                        if("SAIR".equalsIgnoreCase(aux)) return votos;
                        quantidadeVotos = Integer.parseInt(aux);
                        if (quantidadeVotos != -1) {
                            votos.put(numeroCandidato, quantidadeVotos);
                            return votos;                            
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }

        }
        return null;
    }
    
    public static void main(String[] args) {
        
        try {
            
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            
            Urna stub = (Urna) registro.lookup("contabilizarVotos");
            System.out.println("Digite 'SAIR' (sem áspas) a qualquer momento para encerrar a aplicação");
            while(true){
                Map canditado = lerVotos(); 
                if(canditado.isEmpty()){
                    break;
                }
                stub.contabilizarNovosVotos(canditado);
                System.out.println("Voto contabilizado!");
                System.out.println("\n");
            }  
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
