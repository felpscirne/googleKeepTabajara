import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import negocio.AnotacaoDAO;
import negocio.Anotacao;

public class AnotacaoDAOImpl implements AnotacaoDAO {
    
    @Override
    public void cadastrarAnotacao(Anotacao anotacao) {
        String sql = "INSERT INTO anotacoes (titulo, descricao, cor, foto) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = new ConexaoPostgreSQL().getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, anotacao.getTitulo());
            stmt.setString(2, anotacao.getDescricao());
            stmt.setString(3, anotacao.getCor());
            
            
            if (anotacao.getFoto() == null) {
                stmt.setNull(4, Types.BINARY);
            } else {
                stmt.setBytes(4, anotacao.getFoto());
            }
            stmt.executeUpdate();
            System.out.println("Anotação cadastrada com sucesso!");
            
            } catch (SQLException e) {
                System.err.println("Erro ao cadastrar anotação: " + e.getMessage());
            }
            }
            
            @Override
            public void excluirAnotacao(int id) {
                String sql = "DELETE FROM anotacoes WHERE id = ?";
                
                try (Connection connection = new ConexaoPostgreSQL().getConexao();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                    
                    stmt.setInt(1, id);
                    stmt.executeUpdate();

                    if (stmt.executeUpdate() == 0) {
                        System.out.println("Anotação não encontrada!");
                    } else {
                        System.out.println("Anotação excluída com sucesso!");
                    }
                } catch (SQLException e) {
                    System.err.println("Erro ao excluir anotação: " + e.getMessage());
                }
            }
            
            @Override
            public void alterarAnotacao(Anotacao anotacao) {
                String sql = "UPDATE anotacoes SET titulo = ?, descricao = ?, cor = ?, foto = ? WHERE id = ?";
                
                try (Connection connection = new ConexaoPostgreSQL().getConexao();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {
                    
                    stmt.setString(1, anotacao.getTitulo());
                    stmt.setString(2, anotacao.getDescricao());
                    stmt.setString(3, anotacao.getCor());
                    
                    if (anotacao.getFoto() == null) {
                        stmt.setNull(4, Types.BINARY);
                    } else {
                        stmt.setBytes(4, anotacao.getFoto());
                    }

                    stmt.setInt(5, anotacao.getId());
                    stmt.executeUpdate();

                    if (stmt.executeUpdate() == 0) {
                        System.out.println("Anotação não encontrada!");
                    } else {
                        System.out.println("Anotação alterada com sucesso!");
                    }

                } catch (SQLException e) {
                    System.err.println("Erro ao alterar anotação: " + e.getMessage());
                }
            }

    @Override
    public Anotacao copiarAnotacao(int id) {
        Anotacao anotacao = null;
        String sql = "INSERT INTO anotacoes (titulo, data_hora descricao, cor, foto)\n" +
                    "SELECT titulo, data_hora, descricao, cor, foto\n" +
                    "FROM anotacoes\n" +
                    "WHERE id = ?;";;
        
        try (Connection connection = new ConexaoPostgreSQL().getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                anotacao = new Anotacao();
                anotacao.setTitulo(rs.getString("titulo"));
                anotacao.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                anotacao.setDescricao(rs.getString("descricao"));
                anotacao.setCor(rs.getString("cor"));
                anotacao.setFoto(rs.getBytes("foto"));
            }

            if (anotacao == null) {
                System.out.println("Anotação não encontrada!");
            } else {
                System.out.println("Anotação copiada com sucesso!");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao copiar anotação: " + e.getMessage());
        }
        return anotacao;
    }

    @Override
    public List<Anotacao> listarAnotacoes() {
        List<Anotacao> anotacoes = new ArrayList<>();
        String sql = "SELECT * FROM anotacoes";
        
        try (Connection connection = new ConexaoPostgreSQL().getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Anotacao anotacao = new Anotacao();
                anotacao.setId(rs.getInt("id"));
                anotacao.setTitulo(rs.getString("titulo"));
                anotacao.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                anotacao.setDescricao(rs.getString("descricao"));
                anotacao.setCor(rs.getString("cor"));
                anotacao.setFoto(rs.getBytes("foto"));
                anotacoes.add(anotacao);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar anotações: " + e.getMessage());
        }
        return anotacoes;
    }

    @Override
    public List<Anotacao> ordenarAnotacoesPorDataHora() {
        List<Anotacao> anotacoes = new ArrayList<>();
        String sql = "SELECT * FROM anotacoes ORDER BY data_hora";

        try (Connection connection = new ConexaoPostgreSQL().getConexao();
            PreparedStatement stmt = connection.prepareStatement(sql)) {    
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Anotacao anotacao = new Anotacao();
                anotacao.setId(rs.getInt("id"));
                anotacao.setTitulo(rs.getString("titulo"));
                anotacao.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                anotacao.setDescricao(rs.getString("descricao"));
                anotacao.setCor(rs.getString("cor"));
                anotacao.setFoto(rs.getBytes("foto"));
                anotacoes.add(anotacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao ordenar anotações por data/hora: " + e.getMessage());
        }
        return anotacoes;
    }
}