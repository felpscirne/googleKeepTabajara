
import java.util.List;

public interface AnotacaoDAO {
    void cadastrarAnotacao(Anotacao anotacao);
    void excluirAnotacao(int id);
    void alterarAnotacao(Anotacao anotacao);
    Anotacao copiarAnotacao(int id);
    List<Anotacao> listarAnotacoes();
    List<Anotacao> ordenarAnotacoesPorDataHora();
}