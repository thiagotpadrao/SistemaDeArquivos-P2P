import java.io.*;
import java.net.*;

import utils.Arquivo;

public class Eachare {
    public static void main(String[] args) {

        String identificador;
        String[] partes;
        String ip = null;
        String porta = null;
        String CaminhoArquivo;
        String nomeDiretorio;

        //armazena os argumentos
        identificador = args[1];
        CaminhoArquivo = args[2];
        nomeDiretorio = args[3];
        partes = identificador.split(":");
        ip = partes[0];
        porta = partes[1];

        Arquivo vizinhos = new Arquivo(CaminhoArquivo);
        vizinhos.adicionaPeer("identificador");
        
        //conexao de server
        try (ServerSocket servidorSocket = new ServerSocket(Integer.parseInt(porta))) {
            System.out.println("Servidor TCP iniciado na porta " + porta);

            while (true) {
                System.out.println("Aguardando conexão de cliente...");
                Socket socketCliente = servidorSocket.accept();
                System.out.println("Cliente conectado: " + socketCliente.getInetAddress());

                // Fluxo de entrada e saída
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter saida = new PrintWriter(socketCliente.getOutputStream(), true);

                // Receber mensagem do cliente
                String mensagemRecebida = entrada.readLine();
                System.out.println("Mensagem recebida: " + mensagemRecebida);

                // Responder ao cliente
                String resposta = "Mensagem recebida com sucesso!";
                saida.println(resposta);
                System.out.println("Resposta enviada ao cliente.");

                // Fechar conexão com o cliente
                socketCliente.close();
                System.out.println("Conexão com cliente encerrada.\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

