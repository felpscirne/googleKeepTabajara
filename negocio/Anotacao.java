
import java.time.LocalDateTime;

public class Anotacao {
    private int id;
    private String titulo;
    private LocalDateTime dataHora;
    private String descricao;
    private String cor;
    private byte[] foto;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public byte[] getFoto() {
        return foto;
    }
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    @Override
    public String toString() {
            return "Anotacao{" +
                    "id=" + id +
                    ", titulo='" + titulo + '\'' +
                    ", dataHora=" + dataHora +
                    ", descricao='" + descricao + '\'' +
                    ", cor='" + cor + '\'' +
                    ", foto=" + (foto != null ? "[imagem]" : "null") +
                    '}';
        }
    }



