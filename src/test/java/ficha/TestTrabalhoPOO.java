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

public class TestTrabalhoPOO {

    private static final PrintStream saidaOriginal = System.out;
    private static final ByteArrayOutputStream saidaCapturada = new ByteArrayOutputStream();
    private static String saidaTestes;

    @BeforeAll
    public static void inicializa() throws Exception {
        System.setOut(new PrintStream(saidaCapturada));

        Raca racaMock = mock(Raca.class);
        when(racaMock.getNome()).thenReturn("Elfo");

        Classe classeMock = mock(Classe.class);
        when(classeMock.getNome()).thenReturn("Guerreiro");

        try (MockedStatic<Personagem> personagemMock = Mockito.mockStatic(Personagem.class)) {
            personagemMock.when(Personagem::getNome).thenReturn("Arwen");
            personagemMock.when(Personagem::getRaca).thenReturn(racaMock);
            personagemMock.when(Personagem::getClasse).thenReturn(classeMock);
            personagemMock.when(Personagem::getNivel).thenReturn(5);
            personagemMock.when(Personagem::imprimeAtributos).thenAnswer(invocacao -> {
                System.out.println("Força: 10  Agilidade: 12  Inteligência: 8");
                return null;
            });
            TrabalhoPOO.imprimirFichaProvisoria();
        }

        racaMock = mock(Raca.class);
        when(racaMock.getNome()).thenReturn("Anao");
        when(racaMock.getPoder()).thenReturn(1);

        classeMock = mock(Classe.class);
        when(classeMock.getNome()).thenReturn("Bruxo");
        when(classeMock.getPoder()).thenReturn(2);

        Map<Integer, String> mapaDePoderes = new HashMap<>();
        mapaDePoderes.put(1, "Poder de Raça: Ataque especial");
        mapaDePoderes.put(2, "Poder de Classe: Bola de Fogo");

        Field campoMapa = Poderes.class.getDeclaredField("mapPoderes");
        campoMapa.setAccessible(true);
        campoMapa.set(null, mapaDePoderes);

        try (MockedStatic<Personagem> personagemMock = Mockito.mockStatic(Personagem.class)) {
            personagemMock.when(Personagem::getNome).thenReturn("Zé");
            personagemMock.when(Personagem::getNivel).thenReturn(10);
            personagemMock.when(Personagem::getRaca).thenReturn(racaMock);
            personagemMock.when(Personagem::getClasse).thenReturn(classeMock);
            personagemMock.when(Personagem::getPv).thenReturn(80);
            personagemMock.when(Personagem::getMana).thenReturn(120);
            personagemMock.when(Personagem::getAtq).thenReturn(15);
            personagemMock.when(Personagem::getDefesa).thenReturn(6);
            personagemMock.when(Personagem::getDeslocamento).thenReturn(7);
            personagemMock.when(Personagem::imprimeAtributos).thenAnswer(invocacao -> {
                System.out.println("Força: 6  Agilidade: 14  Inteligência: 18");
                return null;
            });
            TrabalhoPOO.imprimirFicha();
        }
        
        saidaTestes = saidaCapturada.toString();
    }

    @AfterAll
    public static void finaliza() {
        System.setOut(saidaOriginal);
    }
    
    @Test
    void testNomeFichaProvisoria() {
        assertTrue(saidaTestes.contains("Nome: Arwen"));
    }
    
    @Test
    void testRacaFichaProvisoria() {
        assertTrue(saidaTestes.contains("Raca: Elfo"));
    }
    
    @Test
    void testClasseFichaProvisoria() {
        assertTrue(saidaTestes.contains("Classe: Guerreiro"));
    }

    @Test
    void testNivelFichaProvisoria() {
        assertTrue(saidaTestes.contains("Nivel: 5"));
    }

    @Test
    void testAtributosFichaProvisoria() {
        assertTrue(saidaTestes.contains("Força: 10  Agilidade: 12  Inteligência: 8"));
    }

    @Test
    void testNomeFichaCompleta() {
        assertTrue(saidaTestes.contains("Nome: Zé"));
    }
    
    @Test
    void testRacaFichaCompleta() {
        assertTrue(saidaTestes.contains("Raca: Anao"));
    }
    
    @Test
    void testClasseFichaCompleta() {
        assertTrue(saidaTestes.contains("Classe: Bruxo"));
    }

    @Test
    void testNivelFichaCompleta() {
        assertTrue(saidaTestes.contains("Nivel: 10"));
    }
    
    @Test
    void testPVFichaCompleta() {
        assertTrue(saidaTestes.contains("PV: 80"));
    }

    @Test
    void testManaFichaCompleta() {
        assertTrue(saidaTestes.contains("Mana: 120"));
    }
    
    @Test
    void testAtaqueEDefesaFichaCompleta() {
        assertTrue(saidaTestes.contains("Ataque: 15"));
        assertTrue(saidaTestes.contains("Defesa: 6"));
    }
    
    @Test
    void testDeslocamentoFichaCompleta() {
        assertTrue(saidaTestes.contains("Deslocamento: 7"));
    }
    
    @Test
    void testPoderDeRacaFichaCompleta() {
        assertTrue(saidaTestes.contains("Poder de Raça: Ataque especial"));
    }
    
    @Test
    void testPoderDeClasseFichaCompleta() {
        assertTrue(saidaTestes.contains("Poder de Classe: Bola de Fogo"));
    }
}