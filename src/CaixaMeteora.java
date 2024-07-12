package projeto.banco.linkin.park;

import java.util.Scanner;
import static projeto.banco.linkin.park.BancoLinkinPark.*;
import static projeto.banco.linkin.park.Usuario.*;

public class CaixaMeteora{
    public static BancoLinkinPark banco;
    public static String cpf, senha;
    public static void main(String[] args) throws InterruptedException{
        banco = new BancoLinkinPark();
        while(true){
            Scanner entrada = new Scanner(System.in);
            while(true){
                limparTela();
                Thread.sleep(1000);
                System.out.println("-=-+-=-=-=-=-=-=-<-=-=-=-=-=-=-=-+-=-=-");
                Thread.sleep(500);
                System.out.println("     Caixa Eletronico 24h METEORA      ");
                Thread.sleep(500);
                System.out.println("-=-+-=-=-=-=-=-=->-=-=-=-=-=-=-=-+-=-=-");
                Thread.sleep(500);
                System.out.println("[1]Login\n[2]sair ");
                int resposta = entrada.nextInt();
                entrada.nextLine();
                switch(resposta){
                    case 1 -> {
                        while(true){
                            System.out.print("Digite o seu CPF (Somente numeros): ");
                            cpf = entrada.nextLine();
                            if(!isNumeric(cpf)){
                                System.out.println("Digite SOMENTE NUMEROS!!!");
                            }else if(cpf.length() != 11){
                                System.out.println("-> Digite o CPF Completo <- ");
                            }else{
                                while(true){
                                    System.out.print("Digite a senha (4 digitos): ");
                                    senha = entrada.nextLine();
                                    if(!isNumeric(senha)){
                                        System.out.println("Digite SOMENTE NUMEROS!!!");
                                    }else if(senha.length() != 4){
                                        System.out.println("Senha de 4 digitos somente.");
                                    }else if(senha.length() == 4){
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        if(validarLogin()){
                            while (true){
                                resposta = 0;
                                limparTela();
                                Thread.sleep(1000);
                                System.out.println("-=-+-=-=-=-=-=-=-<-=-=-=-=-=-=-=-+-=-=-");
                                Thread.sleep(500);
                                System.out.println("     Caixa Eletronico 24h METEORA      ");
                                Thread.sleep(500);
                                System.out.println("-=-+-=-=-=-=-=-=->-=-=-=-=-=-=-=-+-=-=-");
                                Thread.sleep(500);
                                System.out.println("""
                                                                           [1]Saque
                                                                           [2]Deposito
                                                                           [3]Transferencia
                                                                           [4]Consultar saldo
                                                                           [5]Encerrar sessao""");
                                resposta = entrada.nextInt();
                                switch(resposta){
                                    case 1 -> {
                                        int saque = 0;
                                        System.out.println("Quanto voce gostaria de sacar? ");
                                        saque = entrada.nextInt();
                                        entrada.nextLine();
                                        if(saque <= getSaldo()){
                                            for(int i = 0; i < usuarios.size(); i ++){
                                                if(cpf.equals(usuarios.get(i).get(0))){
                                                    setSaldo(Double.parseDouble(usuarios.get(i).get(2)));
                                                    banco.getUsuario().sacar(saque);
                                                    usuarios.get(i).remove(2);
                                                    usuarios.get(i).add(""+getSaldo());
                                                }
                                            }

                                        }else{
                                            System.out.println("Error, seu saldo e inferior ao pedido de saque. Tente novamente.");
                                            break;
                                        }
                                        System.out.println("Saque efetuado com sucesso.");
                                        Thread.sleep(1000);
                                        System.out.println("Seu saldo e R$"+getSaldo());
                                        Thread.sleep(1000);
                                        break;
                                    }
                                    case 2 -> {
                                        int deposito = 0;
                                        System.out.println("Quanto voce gostaria de depositar? ");
                                        deposito = entrada.nextInt();
                                        entrada.nextLine();
                                        if(deposito <= 0){
                                            System.out.printf("Error: voce nao pode depositar R$%d", deposito);
                                            System.out.println();
                                        }else{
                                            for(int i = 0; i < usuarios.size(); i ++){
                                                if(cpf.equals(usuarios.get(i).get(0))){
                                                    setSaldo(Double.parseDouble(usuarios.get(i).get(2)));
                                                    banco.getUsuario().depositar(deposito);
                                                    usuarios.get(i).remove(2);
                                                    usuarios.get(i).add(""+getSaldo());
                                                }
                                            }
                                            System.out.println("Deposito efetuado com sucesso");
                                            Thread.sleep(1000);
                                            System.out.println("Seu saldo e R$"+getSaldo());
                                            Thread.sleep(1000);
                                        }
                                        break;
                                    }
                                    case 3 -> {
                                        banco.getUsuario().transferencia();
                                        Thread.sleep(1000);
                                        System.out.println("Seu saldo e R$"+getSaldo());
                                        Thread.sleep(1000);
                                        break;
                                    }
                                    case 4 -> {
                                        Thread.sleep(1000);
                                        for(int i = 0; i < usuarios.size(); i++){
                                            if(cpf.equals(usuarios.get(i).get(0))){
                                                setSaldo(Double.parseDouble(usuarios.get(i).get(2)));
                                                System.out.println("Seu saldo atual e de R$"+getSaldo());
                                            }
                                        }
                                        Thread.sleep(1000);
                                        break;
                                    }
                                    case 5 -> {
                                        Thread.sleep(1000);
                                        System.out.println("Muito Obrigado por usar o caixa Meteora :D");
                                        Thread.sleep(1000);
                                        break;
                                    }
                                    default -> {
                                        System.out.println("Nao existe esta opcao, tente novamente.");
                                        Thread.sleep(1000);
                                        break;
                                    }
                                }
                                if(resposta == 5){
                                    break;
                                }
                            }    
                        }else if(!validarLogin()){
                            System.out.println("Error. Login efetuado sem sucesso.");
                            System.out.println("Certifique-se que a senha ou CPF fora digitado corretamente.");
                        }  
                    }
                    case 2 -> {
                        Thread.sleep(1000);
                        System.out.println("Ate mais :)");
                        Thread.sleep(1000);
                    }
                    default -> System.out.println("Nao existe esta opcao, tente novamente.");
                }
                if(resposta == 2){
                    break;
                }
            }
            break;
        }
    }
    
    public static boolean validarLogin(){
        boolean cpfValido = false;
        for(int i = 0; i < BancoLinkinPark.usuarios.size(); i++){
            if(cpf.equals(BancoLinkinPark.usuarios.get(i).get(0)) && 
                    senha.equals(BancoLinkinPark.usuarios.get(i).get(1))){
                System.out.println("Login efetuado com sucesso.");
                return true;
            }else if(cpf.equals(BancoLinkinPark.usuarios.get(i).get(0)) && 
                    !senha.equals(BancoLinkinPark.usuarios.get(i).get(1))){
                return false;
            }if(!cpf.equals(BancoLinkinPark.usuarios.get(i).get(0)) && 
                    !senha.equals(BancoLinkinPark.usuarios.get(i).get(1))){
                cpfValido = false;
            }
        }
        return cpfValido;
    }
}
// Finalizado por 730n4Rd0 às 09:49 de uma segunda-feira de abril. 