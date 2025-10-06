package combate;

import ficha.Personagem;
import equipamentos.Armas;
import racasClasses.Classe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


//mock de inimigo

class MockInimigo extends Inimigo {
    private final Armas arma;

    public MockInimigo(Armas arma) {
       
        super("Goblin", 10, 10, 5, 2, arma);
        this.arma = arma;
    }

    @Override
    public int getPv() { return 10; }
    @Override
    public int getDefesa() { return 10; }
    @Override
    public int getDeslocamento() { return 5; }
    @Override
    public int getAtq() { return 2; }
    @Override
    public String getNome() { return "Goblin"; }
    @Override
    public Armas getArma() { return this.arma; }
}


public class CombateTest {

    private InputStream systemInOriginal;

    @BeforeEach
    public void inicicializa() {
        // Salva a entrada original do sistema (o teclado)
        systemInOriginal = System.in;

        Armas arma = new Armas(6, 20, 2, 1, "Espada de Teste", "Corte");
        Classe classe = new Classe("Guerreiro", 5, 1, 10, 5, 10, true, true, true);

        new Personagem();
        Personagem.setArma(arma);
        Personagem.setClasse(classe);
        Combate.dist = 0; 
    }

    //restaura o System.in original
    @AfterEach
    public void finaliza() {
        System.setIn(systemInOriginal);
    }

    
    @Test
    public void testLuta() {
        
        // 1. "s" para começar o combate.
        // 2. "3" para escolher a opção "ATACAR".
        // 3. "5" para "ENCERRAR TURNO" e sair do loop do turno do jogador.
        String entradasSimuladas = "s\n3\n5\n";
        InputStream inputStreamSimulado = new ByteArrayInputStream(entradasSimuladas.getBytes());
        System.setIn(inputStreamSimulado);
        
    }


  @Test
    public void testTurnoInimigoComDistanciaTresNaoDeveAtacar() {
        Combate.dist = 3;
        boolean atacou = Combate.turnoInimigo();
        assertFalse(atacou);
    }

    @Test
    public void testTurnoInimigoComDistanciaTresDeveAproximar() {
        Combate.dist = 3;
        Combate.turnoInimigo();
        assertEquals(1, Combate.dist);
    }

    @Test
    public void testTurnoInimigoComDistanciaUmDeveAtacar() {
        Combate.dist = 1;
        boolean atacou = Combate.turnoInimigo();
        assertTrue(atacou);
    }

    @Test
    public void testTurnoInimigoComDistanciaDoisDeveAtacar() {
        Combate.dist = 2;
        boolean atacou = Combate.turnoInimigo();
        assertTrue(atacou);
    }

    @Test
    public void testTurnoInimigoComDistanciaUmNaoDeveMudarDistancia() {
        Combate.dist = 1;
        Combate.turnoInimigo();
        assertEquals(1, Combate.dist);
    }

    @Test
    public void testTurnoInimigoComDistanciaDoisDeveMudarDistancia() {
        Combate.dist = 2;
        Combate.turnoInimigo();
        assertEquals(1, Combate.dist);
    }

    @Test
    public void testAtaqueDefesaSuperaAtaque() {
        int pvInicial = 50;
        int ataqueAtacante = 5;
        int defesaAlvo = 100; 
        int pvFinal = Combate.ataque(ataqueAtacante, defesaAlvo, pvInicial, 6, 20, 2);
        assertEquals(pvInicial, pvFinal);
    }

    @Test
    public void testAtaqueAtaqueSuperaDefesa() {
        int pvInicial = 50;
        int ataqueDoAtacante = 100; 
        int defesaDoAlvo = 10;
        int pvFinal = Combate.ataque(ataqueDoAtacante, defesaDoAlvo, pvInicial, 6, 20, 2);
        assertTrue(pvFinal < pvInicial);
    }

    @Test
    public void testAtaqueEspecial() {
        int pvInicial = 50;
        int ataqueDoHeroi = 100;
        int defesaDoAlvo = 10;
        int pvFinal = Combate.ataqueEspecial(pvInicial, ataqueDoHeroi, defesaDoAlvo);
        assertTrue(pvFinal < pvInicial);
    }

    @Test
    public void testMarcaCacador() {
        int pvInicial = 50;
        int ataqueDoHeroi = 100;
        int defesaDoAlvo = 10;
        int pvFinal = Combate.marcaCacador(pvInicial, ataqueDoHeroi, defesaDoAlvo);
        assertTrue(pvFinal < pvInicial);
    }

    @Test
    public void testCurarFerimentos() {
        int pvInicial = 10;
        int pvFinal = Combate.curarFerimentos(pvInicial);
        assertTrue(pvFinal > pvInicial);
    }

    @Test
    public void testBolaFogo() {
        int pvInicial = 50;
        int pvFinal = Combate.bolaFogo(pvInicial);
        assertTrue(pvFinal < pvInicial);
    }

    @Test
    public void testConfereManaSuficiente() {
        Classe classeComCustoBaixo = new Classe("Mago", 6, 3, 8, 4, 12, false, true, false);
        Personagem.setClasse(classeComCustoBaixo);
        int manaAtual = 5;
        assertTrue(Combate.confereMana(manaAtual));
    }

    @Test
    public void testConfereManaInsuficiente() {
        Classe classeComCustoAlto = new Classe("Mago", 6, 10, 8, 4, 12, false, true, false);
        Personagem.setClasse(classeComCustoAlto);
        int manaAtual = 5;
        assertFalse(Combate.confereMana(manaAtual));
    }
}

