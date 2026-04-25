# Graficos do Experimento Micro (DSU Isolado)

Esta pasta concentra figuras geradas a partir de `dados/resultados/experimento_micro.csv`.

## Cenario
No cenário `cadeia_find0`:
1. é montada uma cadeia com `union(k, k-1)` para `k = 1..n-1`;
2. depois é executado `find(0)` repetidamente.

Isso isola o custo do DSU para tornar mais visível a diferença entre `naive`, `rank` e `tarjan`.

## Arquivos e interpretacao
- `micro_cadeia_loglog_acessos.png`
  - Total de acessos a memória por `n` (escala log no eixo Y).
  - Destaca diferença de ordem de grandeza entre as variantes.
  - **Analise dos resultados:** para `n=10000`, `naive` chega a `20,007,999` acessos, contra `41,995` (`rank`) e `53,992` (`tarjan`).
  - **Leitura objetiva:** no pior ponto, `naive` usa ~`476x` mais acessos que `rank` e ~`371x` mais que `tarjan`.

- `micro_cadeia_acessos_por_find.png`
  - Razão `total_acessos / num_finds` por `n`.
  - Aproxima o custo médio por operação de busca no cenário.
  - **Analise dos resultados:** o custo por find cresce quase linearmente no `naive` (`99.05` em `n=100` para `10004.00` em `n=10000`), enquanto cresce lentamente em `rank` (`1.20 -> 21.00`) e `tarjan` (`2.25 -> 27.00`).
  - **Leitura objetiva:** em `n=10000`, cada find no `naive` custa ~`477x` um find em `rank` e ~`371x` um find em `tarjan`.

- `micro_cadeia_tempo.png`
  - Tempo médio (ms) por `n`.
  - Confirma em tempo de execução o padrão visto em acessos.
  - **Analise dos resultados:** em `n=10000`, `naive` fica em `22.805 ms`, `rank` em `0.075 ms` e `tarjan` em `0.066 ms`.
  - **Leitura objetiva:** o ganho de tempo chega a ~`305x` (`naive` vs `rank`) e ~`344x` (`naive` vs `tarjan`).
  - **Observacao:** em `n` pequenos há variação de microbenchmark (ruído de execução), mas a tendência global é estável e consistente.

## Leitura recomendada
1. Comece por `micro_cadeia_loglog_acessos.png` (visão assintótica).
2. Use `micro_cadeia_acessos_por_find.png` para discutir custo amortizado.
3. Finalize com `micro_cadeia_tempo.png` para evidência prática de desempenho.

## Conclusao dos graficos micro
- O cenário controlado evidencia com clareza a pior escalabilidade da versão ingênua.
- `rank` e `tarjan` mantêm custos muito menores; no tempo, `tarjan` tende a ser o melhor para `n` maior no cenário usado.
