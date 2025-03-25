package utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {
    private String caminhoArquivo;

    public Arquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public void adicionaPeer(String texto) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            escritor.write(texto);
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}

