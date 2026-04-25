# Graficos do Experimento Principal (Kruskal)

Esta pasta concentra figuras geradas a partir de `dados/resultados/experimento.csv`.

## Arquivos e interpretacao
- `kruskal_principal_conexo.png`
  - Linhas de tempo medio (ms) por `n` para `naive`, `rank` e `tarjan` em grafos conexos.
  - Mostra escalabilidade das variantes no cenário conexo.
  - **Analise dos resultados:** com crescimento de `n`, a curva `naive` acelera fortemente (`0.783 ms` em `n=100` para `129.928 ms` em `n=10000`), enquanto `rank` (`0.351 -> 6.371 ms`) e `tarjan` (`0.314 -> 5.558 ms`) crescem muito menos.
  - **Leitura objetiva:** em `n=10000`, `tarjan` fica ~`23.4x` mais rápido que `naive` e `rank` ~`20.4x` mais rápido.

- `kruskal_principal_desconexo.png`
  - Mesmo formato, mas para grafos desconexos.
  - Permite comparar o efeito da conectividade na carga de `find/union`.
  - **Analise dos resultados:** a diferença também é clara no desconexo: `naive` vai de `0.446 ms` (`n=100`) para `65.520 ms` (`n=10000`), enquanto `rank` vai para `5.488 ms` e `tarjan` para `4.861 ms`.
  - **Leitura objetiva:** em `n=10000`, `tarjan` é ~`13.5x` mais rápido que `naive` e `rank` ~`11.9x`.

- `kruskal_acessos_conexo.png`
  - Acessos medios a memoria (`pai + rank`) por `n` em grafos conexos.
  - Complementa o tempo com uma medida de custo estrutural.
  - **Analise dos resultados:** em `n=10000`, `naive` registra `82,907,228` acessos; `rank`, `199,627`; `tarjan`, `211,992`.
  - **Leitura objetiva:** a redução estrutural é de ~`415x` (`rank`) e ~`391x` (`tarjan`) frente ao `naive`.

- `kruskal_acessos_desconexo.png`
  - Acessos medios a memoria por `n` em grafos desconexos.
  - **Analise dos resultados:** em `n=10000`, `naive` tem `47,994,358` acessos; `rank`, `228,953`; `tarjan`, `239,064`.
  - **Leitura objetiva:** no desconexo, a redução é ~`210x` (`rank`) e ~`201x` (`tarjan`) contra `naive`.
  - **Observacao:** `tarjan` tende a contar mais chamadas de `find` devido à recursão da compressão, então pode ter acessos ligeiramente maiores que `rank` em alguns pontos, mesmo com bom tempo.

- `kruskal_principal_100.png`, `kruskal_principal_500.png`, `kruskal_principal_1000.png`, `kruskal_principal_2000.png`, `kruskal_principal_5000.png`, `kruskal_principal_10000.png`
  - Graficos de barras para um `n` fixo.
  - Comparam tempo entre `conexo` e `desconexo` para cada DSU.
  - **Analise por faixa de tamanho:**
    - `n=100` e `n=500`: diferenças existem, mas ainda pequenas por efeito de overhead.
    - `n=1000` e `n=2000`: separação já fica consistente (`naive` acima).
    - `n=5000` e `n=10000`: separação forte, com `naive` claramente fora de escala.
  - **Leitura objetiva (pior caso mostrado):** no gráfico `kruskal_principal_10000.png`, `naive` fica na casa de dezenas/centenas de ms, enquanto `rank`/`tarjan` ficam em ~`5–6 ms`.

## Leitura recomendada
1. Veja `kruskal_principal_conexo.png` e `kruskal_principal_desconexo.png` para tendência geral.
2. Em seguida, use os dois `kruskal_acessos_*.png` para explicar por que o tempo muda.
3. Feche com os `kruskal_principal_<n>.png` para destacar comparações pontuais por tamanho.

## Conclusao dos graficos de Kruskal
- O comportamento empírico confirma ganho expressivo ao sair da versão ingênua.
- `rank` e `tarjan` permanecem próximos entre si no experimento principal porque parte do custo total também vem de componentes fora do DSU (como ordenação de arestas).
