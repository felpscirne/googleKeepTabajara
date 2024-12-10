import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnotacaoDAO anotacaoDAO = new AnotacaoDAOImpl();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar anotação");
            System.out.println("2. Listar anotações");
            System.out.println("3. Alterar anotação");
            System.out.println("4. Copiar anotação");
            System.out.println("5. Excluir anotação");
            System.out.println("6. Ordenar anotações por data/hora");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    Anotacao anotacao = new Anotacao();
                    System.out.print("Título: ");
                    anotacao.setTitulo(scanner.nextLine());
                    anotacao.setDataHora(LocalDateTime.now());
                    System.out.print("Descrição: ");
                    anotacao.setDescricao(scanner.nextLine());
                    System.out.print("Cor: ");
                    anotacao.setCor(scanner.nextLine());
                    anotacao.setFoto(null); // Adicione uma foto se necessário
                    anotacaoDAO.cadastrarAnotacao(anotacao);
                    break;
                case 2:
                    List<Anotacao> anotacoes = anotacaoDAO.listarAnotacoes();
                    for (Anotacao a : anotacoes) {
                        System.out.println(a.getTitulo());
                    }
                    break;
                case 3:
                    System.out.print("ID da anotação a alterar: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    
                    System.out.println("Novo título: ");
                    String novoTitulo = scanner.nextLine();

                    System.out.println("Nova descrição: ");
                    String novaDescricao = scanner.nextLine();

                    System.out.println("Nova cor: ");
                    String novaCor = scanner.nextLine();

                    System.out.println("Caminho da foto nova: ");
                    String caminhoFoto = scanner.nextLine();

                    byte[] novaFoto = null;

                    if (!caminhoFoto.isEmpty()) {
                        try {
                            novaFoto = Files.readAllBytes(Paths.get(caminhoFoto));
                        } catch (IOException e) {
                            System.err.println("Erro ao ler a foto: " + e.getMessage());
                        }

                    Anotacao anotacaoAtt = new Anotacao(idAlterar, novoTitulo, null, novaDescricao, novaCor, novaFoto);
                    AnotacaoDAOImpl.alterarAnotacao(anotacaoAtt);
                    }
                    break;
                case 4:
                    System.out.print("ID da anotação a copiar: ");
                    int idCopiar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    Anotacao copia = anotacaoDAO.copiarAnotacao(idCopiar);
                    if (copia != null) {
                        System.out.println("Cópia: " + copia.getTitulo());
                    } else {
                        System.out.println("Anotação não encontrada!");
                    }
                    break;
                case 5:
                    System.out.print("ID da anotação a excluir: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                    anotacaoDAO.excluirAnotacao(idExcluir);
                    break;
                case 6:
                    List<Anotacao> anotacoesOrdenadas = anotacaoDAO.ordenarAnotacoesPorDataHora();
                    for (Anotacao a : anotacoesOrdenadas) {
                        System.out.println(a.getDataHora() + " - " + a.getTitulo());
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}
