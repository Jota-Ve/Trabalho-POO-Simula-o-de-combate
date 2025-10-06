package ficha;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import racasClasses.Raca;
import racasClasses.Classe;
import poderes.Poderes;

import static org.mockito.Mockito.*;

public class TesteTrabalhoPOO {

    private static final PrintStream saidaOriginal = System.out;
    private static final ByteArrayOutputStream saidaCapturada = new ByteArrayOutputStream();
 
    @BeforeAll
    public static void inicializa() {
        
        System.setOut(new PrintStream(saidaCapturada));
    }

    @AfterAll
    public static void finaliza() {
        System.setOut(saidaOriginal);
    }

    @Test
    void testImprimirFichaProvisoria() {
        Raca racaFalsa = mock(Raca.class);
        when(racaFalsa.getNome()).thenReturn("Elfo");

        Classe classeFalsa = mock(Classe.class);
        when(classeFalsa.getNome()).thenReturn("Guerreiro");

        try (MockedStatic<Personagem> personagemFalso = Mockito.mockStatic(Personagem.class)) {
            personagemFalso.when(Personagem::getNome).thenReturn("Arwen");
            personagemFalso.when(Personagem::getRaca).thenReturn(racaFalsa);
            personagemFalso.when(Personagem::getClasse).thenReturn(classeFalsa);
            personagemFalso.when(Personagem::getNivel).thenReturn(5);

            personagemFalso.when(Personagem::imprimeAtributos).thenAnswer(invocacao -> {
                System.out.println("Força: 10  Agilidade: 12  Inteligência: 8");
                return null;
            });

            TrabalhoPOO.imprimirFichaProvisoria();

            String textoImpresso = saidaCapturada.toString();

            assertTrue(textoImpresso.contains("Nome: Arwen"));
            assertTrue(textoImpresso.contains("Raca: Elfo"));
            assertTrue(textoImpresso.contains("Classe: Guerreiro"));
            assertTrue(textoImpresso.contains("Nivel: 5"));
            assertTrue(textoImpresso.contains("Força: 10"));
        }
    }

    @Test
    void testaImprimirFicha() throws Exception {
        Raca racaFalsa = mock(Raca.class);
        when(racaFalsa.getNome()).thenReturn("Anao");
        when(racaFalsa.getPoder()).thenReturn(1);

        Classe classeFalsa = mock(Classe.class);
        when(classeFalsa.getNome()).thenReturn("Bruxo");
        when(classeFalsa.getPoder()).thenReturn(2);

        Map<Integer, String> mapaDePoderes = new HashMap<>();
        mapaDePoderes.put(1, "Poder de Raça: Ataque especial");
        mapaDePoderes.put(2, "Poder de Classe: Bola de Fogo");

        Field campoMapa = Poderes.class.getDeclaredField("mapPoderes");
        campoMapa.setAccessible(true);
        campoMapa.set(null, mapaDePoderes);

        try (MockedStatic<Personagem> personagemFalso = Mockito.mockStatic(Personagem.class)) {
            personagemFalso.when(Personagem::getNome).thenReturn("Zé");
            personagemFalso.when(Personagem::getNivel).thenReturn(10);
            personagemFalso.when(Personagem::getRaca).thenReturn(racaFalsa);
            personagemFalso.when(Personagem::getClasse).thenReturn(classeFalsa);
            personagemFalso.when(Personagem::getPv).thenReturn(80);
            personagemFalso.when(Personagem::getMana).thenReturn(120);
            personagemFalso.when(Personagem::getAtq).thenReturn(15);
            personagemFalso.when(Personagem::getDefesa).thenReturn(6);
            personagemFalso.when(Personagem::getDeslocamento).thenReturn(7);

            personagemFalso.when(Personagem::imprimeAtributos).thenAnswer(invocacao -> {
                System.out.println("Força: 6  Agilidade: 14  Inteligência: 18");
                return null;
            });

            TrabalhoPOO.imprimirFicha();

            String textoImpresso = saidaCapturada.toString();

            assertTrue(textoImpresso.contains("Nome: Zé"));
            assertTrue(textoImpresso.contains("Raca: Anao"));
            assertTrue(textoImpresso.contains("Classe: Bruxo"));
            assertTrue(textoImpresso.contains("Nivel: 10"));
            assertTrue(textoImpresso.contains("PV: 80"));
            assertTrue(textoImpresso.contains("Mana: 120"));
            assertTrue(textoImpresso.contains("Ataque: 15"));
            assertTrue(textoImpresso.contains("Defesa: 6"));
            assertTrue(textoImpresso.contains("Deslocamento: 7"));
            assertTrue(textoImpresso.contains("Poder de Raça: Ataque especial"));
            assertTrue(textoImpresso.contains("Poder de Classe: Bola de Fogo"));
        }
    }
}