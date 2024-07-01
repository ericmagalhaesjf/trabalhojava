import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Dados {
        private static final String URL = ;
        private Connection connection;

        public void connect() {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(URL);
                connection.setAutoCommit(false);
                System.out.println("Conexão realizada! Banco de dados: " + URL);
            } catch (ClassNotFoundException e) {
                System.out.println("Driver JDBC do SQLite não encontrado: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Erro ao conectar: " + e.getMessage());
            }
        }

        public void createTable() {
            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS aluno (ID INTEGER PRIMARY KEY, Nome VARCHAR, Idade INTEGER)");
                connection.commit();
                System.out.println("Tabela criada ou já existe.");
            } catch (SQLException e) {
                System.out.println("Erro ao criar tabela: " + e.getMessage());
            }
        }

        public void close() {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexão fechada.");
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }

        public void queryaluno() {
            try (PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM Clientes WHERE Idade >= 18 ")) {
                ResultSet resultSet = selectStatement.executeQuery();
                System.out.println("aluno:");
                while (resultSet.next()) {
                    int nome = resultSet.getInt("CPF");
                    String nome = resultSet.getString("Nome");
                    System.out.println("Nome: " + nome + ", CPF: " + CPF);
                }
            } catch (SQLException e) {
                System.out.println("Erro ao consultar aluno: " + e.getMessage());
            }
        }

        public void atualizarMenores() {
            try (PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM Clientes WHERE Idade < 18")) {
                ResultSet resultSet = selectStatement.executeQuery();
                System.out.println("aluno:");
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String nome = resultSet.getString("Nome");
                    nome = nome.concat("(menor)");
                    updatealuno(id, nome);
                }
            } catch (SQLException e) {
                System.out.println("Erro ao consultar aluno: " + e.getMessage());
            }
        }

        public void updatealuno(int id, String novoNome) {
            try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE Clientes SET Nome = ? WHERE ID = ?")) {
                updateStatement.setString(1, novoNome);
                updateStatement.setInt(2, id);
                updateStatement.executeUpdate();
                connection.commit();
                System.out.println("Aluno atualizado.");
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            }
        }
    }

