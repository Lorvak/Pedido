/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ArquivoDAO;
import dao.ArquivoDAOImp;
import entidade.Arquivo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Junior
 */
@ManagedBean(name = "upDon")
@SessionScoped
public class UploadDonwload implements Serializable {

    private static final long serialVersionUID = 1L;
    private Arquivo arquivo = new Arquivo();
    private List<Arquivo> arquivos = new ArrayList<Arquivo>();
    private ArquivoDAO arquivoDAO;
    
    private StreamedContent file;
    

    public StreamedContent getFile() throws FileNotFoundException {
        arquivoDAO = new ArquivoDAOImp();
        Long id = arquivo.getId();
        arquivo = arquivoDAO.pesquisaPorId(id);
        String caminho = arquivo.getCaminho();
//        String caminho = "\\arquivos\\"+ arquivo.getNome();
//        FileInputStream stream = new FileInputStream("arquivos\\"+ arquivo.getNome());
        InputStream stream = new FileInputStream(caminho);  
        file = new DefaultStreamedContent(stream, "", arquivo.getNome());
//        file = new DefaultStreamedContent(stream, caminho, arquivo.getNome());
        return file;
    }

    public String fileUploadAction(FileUploadEvent event) throws IOException {
        try {
            arquivo.setNome(event.getFile().getFileName());
            byte[] conteudo = event.getFile().getContents();
            String caminho = "C:\\Documents and Settings\\Liana\\Desktop\\testeup\\" + event.getFile().getFileName();
            FileOutputStream fos = new FileOutputStream(caminho);
            fos.write(conteudo);
            fos.close();
            arquivoDAO = new ArquivoDAOImp();
            arquivo.setCaminho(caminho);
            arquivo.setTamanho(conteudo.length);
            String nomeArquivo = arquivo.getNome();
            int e = nomeArquivo.lastIndexOf(".");
            arquivo.setTipo(nomeArquivo.substring(e));
            arquivoDAO.salva(arquivo);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Arquivo Salvo!", arquivo.getNome()));
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " " + e));
        } finally {
            arquivo = new Arquivo();
            arquivos = new ArrayList<Arquivo>();
            file = null;
        }
        return "midiateca";
    }

    public List<Arquivo> getListArquivos() {
        arquivoDAO = new ArquivoDAOImp();
        arquivos = arquivoDAO.getTodos();
        return arquivos;
    }

    public void excluir() {
        try {
            arquivoDAO = new ArquivoDAOImp();
            Long id = arquivo.getId();
            arquivo = arquivoDAO.pesquisaPorId(id);
            new File(arquivo.getCaminho()).delete();
            arquivoDAO.remove(arquivo);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Arquivo Deletado!", arquivo.getNome()));
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", " " + e));
        } finally {
            arquivo = new Arquivo();
            arquivos = new ArrayList<Arquivo>();
            file = null;
        }
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    
    
}
