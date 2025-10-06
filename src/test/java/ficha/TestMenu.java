package ficha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestMenu {
    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void inicializa(){
        // redireciona a saída padrão para um ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void finaliza(){
        // restaura a saída padrão
        System.setOut(originalOut);
    }


    @Test
    void deveImprimirMensagemEsperada() {
        // define a saída esperada
        String SAIDA_ESPERADA = """
                O que deseja fazer?
                1 - Imprimir Racas
                2 - Imprimir Classes
                3 - Escolher Raca
                4 - Escolher Classe
                5 - Rolar Dados
                6 - Fechar
                """;

        // chama o método que imprime no console
        Menu.imprimeMenuPrincipal();

        // verifica se a saída é a esperada
        String esperado = SAIDA_ESPERADA.replace("\r\n", "\n");
        String atual = outContent.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");

        assertEquals(esperado, atual);

    }

}
