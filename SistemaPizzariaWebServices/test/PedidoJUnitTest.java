/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import br.android.pedido.Pedido;
import br.sc.senac.pontoeletronico.integracao.mysql.DAOFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aluno
 */
public class PedidoJUnitTest {
    
    public PedidoJUnitTest() {
    }
    
    @Test
    public void cadastra(){
        Pedido p= new Pedido();
        p.setBorda("catupiri");
        p.setTamanho("Família");
        p.setSabor("frango");
        Integer id=new DAOFactory().getDaoPedido().insere(p);
        assertTrue(id!=null);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
