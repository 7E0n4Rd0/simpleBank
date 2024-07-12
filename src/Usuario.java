package projeto.banco.linkin.park;

import java.util.ArrayList;
import java.util.Scanner;
import static projeto.banco.linkin.park.BancoLinkinPark.*;
import static projeto.banco.linkin.park.CaixaMeteora.*;

public class Usuario{
    Scanner entrada = new Scanner(System.in);
    private String cpfString, senhaString;
    ArrayList<String> dados;
    private static double saldo;
    public int valor;
    Usuario(){
        this.dados = new ArrayList<>();
    }
    // Getters & Setters
    public String getCpfString() {
        return cpfString;
    }

    public void setCpfString(String cpfString) {
        this.cpfString = cpfString;
    }

    public String getSenhaString() {
        return senhaString;
    }

    public void setSenhaString(String senhaString) {
        this.senhaString = senhaString;
    }

    public static double getSaldo() {
        return saldo;
        
    }

    public static void setSaldo(double saldo) {
        Usuario.saldo = saldo;
    }
    // Metodos para o usuário
    public void sacar(int saque){
        Usuario.setSaldo(Usuario.getSaldo() - saque);
    }
    
    public void depositar(int deposito){
        Usuario.setSaldo(Usuario.getSaldo() + deposito);
    }
    
    public void transferencia() throws InterruptedException{
        String cpfconta;
        while(true){
            System.out.println("Digite o CPF da pessoa a quem deseja fazer a transferencia (somente numeros): ");
            cpfconta = entrada.nextLine();
            if(!isNumeric(cpfconta)){
                System.out.println("Digite SOMENTE NUMEROS!!!");
            }else if(cpfconta.length() != 11){
                System.out.println("-> Digite o CPF Completo <- ");
            }else{
                for(int i = 0; i < usuarios.size(); i++){  
                    if(cpfconta.equals(usuarios.get(i).get(0))){
                        System.out.println("Digite o valor para a transferencia: ");
                        double valor = entrada.nextInt();
                        entrada.nextLine();
                        if(valor > getSaldo()){
                            System.out.println("Voce nao pode fazer uma transferencia acima do valor de seu saldo atual: R$"+getSaldo());
                            break;
                        }else if(valor <= 0){
                            System.out.println("Nao pode fazer transferencia abaixo de R$0.00.");
                            break;
                        }else{
                            for(int j = 0; j < usuarios.size(); j ++){
                                if(cpf.equals(usuarios.get(j).get(0))){
                                    double saldoAtual = Double.parseDouble(usuarios.get(j).get(2));
                                    usuarios.get(j).remove(2);
                                    setSaldo(saldoAtual - valor);
                                    usuarios.get(j).add(""+getSaldo());
                                    saldoAtual = 0;
                                }
                            }
                            double saldoAtual = Double.parseDouble(usuarios.get(i).get(2));
                            usuarios.get(i).remove(2);
                            usuarios.get(i).add(""+(saldoAtual + valor));
                            Thread.sleep(1000);
                            System.out.println("Transferencia efetuada com sucesso.");
                        }
                    }
                    if(!cpfconta.equals(usuarios.get(i).get(0))){
                        System.out.println("Tranferencia efetuada sem sucesso, conta nao encontrada.");
                    }
                    if(cpfconta.equals(cpf)){
                        System.out.println("Voce nao pode fazer transferencia a si mesmo.");
                    }
                }
                valor = 0;
                break;
            }
        }
    }
}