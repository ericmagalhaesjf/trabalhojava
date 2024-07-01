import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    Scanner scanner = new Scanner(System.in);
    Aluno aluno = new Aluno();

    public void insertaluno(List<String[]> lista) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO aluno(Nome, Idade) VALUES(?, ?)")) {
            for (String[] s : lista) {
                insertStatement.setString(1, s[0]);
                insertStatement.setInt(2, Integer.parseInt(s[1]));
                insertStatement.executeUpdate();
            }
            connection.commit();
            System.out.println("aluno inseridos.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    void adiconardados(){
        List<String[]> lista = new ArrayList<>();
        Dados.connect();
        Dados.createTable();
        while(true) {
            String[] nome ={"", ""};
            System.out.println("Digite o nome do aluno");
            nome[0] = scanner.nextLine();
            //saída do loop
            if (nome[0].equalsIgnoreCase("fim")) {
                break;
            }
            System.out.println("CPF do aluno ");
            nome[1] = scanner.nextLine();
            lista.add(nome);
        }
        Dados.insertaluno(lista);
        Dados.close();
    }


    void imprimirMenu(){
        System.out.println("Escolha uma opção:");
        System.out.println("1- Inserir nome e sobrenome");
        System.out.println("2- Mostrar aluno maiores de idade");
        System.out.println("3- Sinalizar menores de idade");
        System.out.println("4- Remover menores de idade");
        System.out.println("5- Sair");
    }
}
