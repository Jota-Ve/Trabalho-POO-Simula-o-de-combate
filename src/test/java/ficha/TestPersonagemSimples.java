package ficha;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import equipamentos.Armaduras;
import equipamentos.Armas;
import racasClasses.Classe;
import racasClasses.Raca;

public class TestPersonagemSimples {

    // ----------------- Setup -----------------
    @BeforeEach
    void inicializa() {
        new Personagem();                  // zera atributos e nivel=1
        Personagem.setNome(null);
        Personagem.setRaca(null);
        Personagem.setClasse(null);
        Personagem.setArma(null);
        Personagem.setArmadura(null);
        for (int i = 0; i < 6; i++) Personagem.setAtributos(i, 10); // base simples
    }

    // ----------------- Testes simples -----------------
    @Test
    public void testSetNomeEGetNome() {
        Personagem.setNome("Aelarion");
        assertEquals("Aelarion", Personagem.getNome());
    }

    @Test
    public void testSetRacaEGetRaca() {
        Raca r = mock(Raca.class);
        Personagem.setRaca(r);
        assertEquals(r, Personagem.getRaca());
    }

    @Test
    public void testSetClasseEGetClasse() {
        Classe c = mock(Classe.class);
        Personagem.setClasse(c);
        assertEquals(c, Personagem.getClasse());
    }

    @Test
    public void testSetArmaEGetArma() {
        Armas arma = mock(Armas.class);
        Personagem.setArma(arma);
        assertEquals(arma, Personagem.getArma());
    }

    @Test
    public void testSetArmaduraEGetArmadura() {
        Armaduras arm = mock(Armaduras.class);
        Personagem.setArmadura(arm);
        assertEquals(arm, Personagem.getArmadura());
    }


    @Test
    public void testElfoPoderAumentaDeslocamento() {
        Raca elfo = mock(Raca.class);
        when(elfo.getPoder()).thenReturn(1);
        Personagem.setRaca(elfo);

        int deslAntes = Personagem.getDeslocamento();
        Personagem.poderRaca();

        assertEquals(deslAntes + 2, Personagem.getDeslocamento());
    }

    @Test
    public void testElfoPoderAumentaMana() {
        Raca elfo = mock(Raca.class);
        when(elfo.getPoder()).thenReturn(1);
        Personagem.setRaca(elfo);

        int manaAntes = Personagem.getMana();
        Personagem.poderRaca();

        assertEquals(manaAntes + 1, Personagem.getMana());
    }

    @Test
    public void testAnaoPoderFixaDeslocamento() {
        Raca anao = mock(Raca.class);
        when(anao.getPoder()).thenReturn(2);
        Personagem.setRaca(anao);

        Personagem.poderRaca();

        assertEquals(6, Personagem.getDeslocamento());
    }

    @Test
    public void testAnaoPoderAumentaPv() {
        Raca anao = mock(Raca.class);
        when(anao.getPoder()).thenReturn(2);
        Personagem.setRaca(anao);

        int pvAntes = Personagem.getPv();
        Personagem.poderRaca();

        assertEquals(pvAntes + 3, Personagem.getPv());
    }

    public void testMinotauroPoderAumentaDefesa() {
        Raca minotauro = mock(Raca.class);
        when(minotauro.getPoder()).thenReturn(3);
        Personagem.setRaca(minotauro);

        int defAntes = Personagem.getDefesa();
        Personagem.poderRaca();

        assertEquals(defAntes + 1, Personagem.getDefesa());
    }

    @Test
    public void testGoblinPoderAumentaAtaque() {
        Raca goblin = mock(Raca.class);
        when(goblin.getPoder()).thenReturn(99); // qualquer valor diferente de 1, 2, 3
        Personagem.setRaca(goblin);

        int atqAntes = Personagem.getAtq();
        Personagem.poderRaca();

        assertEquals(atqAntes + 1, Personagem.getAtq());
    }
}
