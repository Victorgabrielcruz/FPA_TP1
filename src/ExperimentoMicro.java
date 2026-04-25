import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nucleo.DSU;
import nucleo.DSUNaive;
import nucleo.DSURank;
import nucleo.DSUTarjan;
import utilitarios.ColetorMetricas;

/**
 * Micro-experimento sobre o DSU isolado: constrói uma cadeia patológica para
 * {@code find(0)} (sequência {@code union(k, k-1)}) e repete {@code find(0)} um
 * número fixo de vezes. Isso separa o custo amortizado das variantes:
 * ingênua ~Θ(n) por find na cadeia, rank ~O(log n), Tarjan ~O(α(n)).
 */
public class ExperimentoMicro {

    private static final int[] VALORES_N = {
            100, 250, 500, 1000, 2000, 4000, 8000, 10000
    };
    /** Repetições de {@code find(0)} após montar a cadeia (fixo ⇒ custo ingênuo cresce ~linearmente em n). */
    private static final int NUM_FINDS = 2000;
    private static final int REPETICOES = 3;

    public static void main(String[] args) {
        System.out.println("Experimento micro: cadeia + find(0) x " + NUM_FINDS);
        List<String> linhas = new ArrayList<>();
        linhas.add("cenario,tipo_dsu,n,num_unions,num_finds,repeticao,tempo_ms,"
                + ColetorMetricas.getCSVCabecalho());

        for (int n : VALORES_N) {
            System.out.println("n = " + n);
            rodarVariante(linhas, n, new DSUNaive(), "naive");
            rodarVariante(linhas, n, new DSURank(), "rank");
            rodarVariante(linhas, n, new DSUTarjan(), "tarjan");
        }

        salvar(linhas, "dados/resultados/experimento_micro.csv");
        System.out.println("Salvo: dados/resultados/experimento_micro.csv");
    }

    private static void rodarVariante(List<String> linhas, int n, DSU dsu, String nome) {
        int unions = Math.max(0, n - 1);
        for (int rep = 1; rep <= REPETICOES; rep++) {
            dsu.reset(n);
            ColetorMetricas c = new ColetorMetricas();
            dsu.setColetor(c);
            c.iniciarTempo();

            for (int k = 1; k < n; k++) {
                dsu.union(k, k - 1);
            }
            for (int t = 0; t < NUM_FINDS; t++) {
                dsu.find(0);
            }

            c.pararTempo();
            String linha = String.format(Locale.US,
                    "cadeia_find0,%s,%d,%d,%d,%d,%.3f,%s",
                    nome, n, unions, NUM_FINDS, rep,
                    c.getTempoMilissegundos(),
                    c.toCSV());
            linhas.add(linha);
        }
    }

    private static void salvar(List<String> linhas, String caminho) {
        try (PrintWriter w = new PrintWriter(new FileWriter(caminho))) {
            for (String s : linhas) {
                w.println(s);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
