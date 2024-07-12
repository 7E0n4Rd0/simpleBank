package projeto.banco.linkin.park;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BancoLinkinPark{
    public static ArrayList<ArrayList<String>> usuarios = new ArrayList<>();
    Scanner entrada = new Scanner(System.in);
    public int resposta;
    private Usuario usuario;
    BancoLinkinPark() throws InterruptedException{
        while(true){
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("            Banco LINKIN PARK          ");
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("[1]Criar conta nova\n[2]Sair");
            resposta = entrada.nextInt();
            entrada.nextLine();
            if(resposta == 1){ // Caso o usário queira criar uma nova conta
                while(true){
                    usuario = new Usuario();
                    System.out.print("Digite o seu CPF (Somente numeros): ");
                    usuario.setCpfString(entrada.nextLine());
                    if(!isNumeric(usuario.getCpfString())){
                        System.out.println("Digite SOMENTE NUMEROS!!!");
                    }else if(usuario.getCpfString().length() != 11){
                        System.out.println("-> Digite o CPF Completo <- ");
                    }else{
                        while (true){
                            System.out.print("Digite a senha (4 digitos): ");
                            usuario.setSenhaString(entrada.nextLine());
                            if(!isNumeric(usuario.getSenhaString())){
                                System.out.println("Digite SOMENTE NUMEROS!!!");
                            }else if(usuario.getSenhaString().length() != 4){
                                System.out.println("Cadastra a senha de 4 digitos somente.");
                            }else{
                                usuario.dados.add(usuario.getCpfString());
                                usuario.dados.add(usuario.getSenhaString());
                                usuario.setSaldo(0);
                                usuario.dados.add(""+usuario.getSaldo());
                                break;
                            }
                        }
                        if(!Validador()){
                            break;
                        }else{
                            cadastraUsuario();
                            System.out.println("Usuario cadastrado com sucesso.");
                        }
                        Thread.sleep(2000);
                        limparTela();  
                    resposta = 0;
                    break;
                    }
                }
            }else if(resposta == 2){ // Caso o usuário (Gerente) queira sair da sessão.
                break;
            }else{
                System.out.println("Nao existe esta opcao");
            }
        }
    }
    // Metodos criados
    public final static void limparTela(){ 
        //Obs.: este método não "funciona" no terminal do netbeans, ele funciona no terminal em si.
        try{
            final String os = System.getProperty("os.name");
            if(os.contains("Windows 11")){
                new ProcessBuilder("cmd", "/c","cls").inheritIO().start().waitFor();
            }
        }catch (final IOException e){
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, e);
        }catch (InterruptedException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean isNumeric(String str){ //Verifica se na String possuí somente números
        return str != null && str.matches("[0-9.]+");
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    private void cadastraUsuario(){ //Cadastra o usuário ao sistema do banco.
        usuarios.add((ArrayList<String>) usuario.dados.clone());
        usuario.dados.clear();
        usuario.setCpfString(null);
        usuario.setSenhaString(null);
    }
    
    private boolean Validador(){ //Valida se a conta é existente ou não.
        boolean cadastrar = true;
        for(int i = 0; i < usuarios.size(); i++){
            if(usuario.getCpfString().equals(usuarios.get(i).get(0))){
                System.out.println("Este CPF ja foi cadastrado.");
                cadastrar = false;
            }
        }
        return cadastrar;
    }
    
}
