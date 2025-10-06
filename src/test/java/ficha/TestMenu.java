package ficha;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMenu {
    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void inicializa(){
        // redireciona a saída padrão para um ByteArrayOutputStream
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void limpaBuffer(){
        // limpa o conteúdo do buffer antes de cada teste
        outContent.reset();
    }

    @AfterAll
    public static void finaliza(){
        // restaura a saída padrão
        System.setOut(originalOut);
    }

    public static String formataString(String str) {
        // Formata a string para evitar falhas por causa de diferenças de quebra de linha
        return str.replace("\r\n", "\n");
    }

    public static String getSaidaReal() {
        // Retorna a saída real formatada
        return formataString(outContent.toString(StandardCharsets.UTF_8));
    }


    @Test
    void testImprimirMenuPrincipal() {
        // define a saída esperada
        String SAIDA_ESPERADA = formataString("""
            O que deseja fazer?
            1 - Imprimir Racas
            2 - Imprimir Classes
            3 - Escolher Raca
            4 - Escolher Classe
            5 - Rolar Dados
            6 - Fechar
            """);

        // chama o método que imprime no console
        Menu.imprimirMenuPrincipal();
        // compara a saída esperada com a saída real
        assertEquals(SAIDA_ESPERADA, getSaidaReal());
    }


    @Test
    void testImprimirMenuEquipamento() {
        // define a saída esperada
        String SAIDA_ESPERADA = formataString("""

            O que deseja fazer?
            1 - Imprimir Armas
            2 - Imprimir Armaduras
            3 - Escolher Arma
            4 - Escolher Armadura
            5 - Fechar
            """);

        // chama o método que imprime no console
        Menu.imprimirMenuEquipamentos();
        // compara a saída esperada com a saída real
        assertEquals(SAIDA_ESPERADA, getSaidaReal());
    }



}
