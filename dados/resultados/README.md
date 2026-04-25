# Resultados dos Experimentos

Esta pasta reúne os artefatos da análise experimental do trabalho.

## Arquivos CSV
- `experimento.csv`: experimento principal (Kruskal + 3 variantes de DSU).
- `experimento_micro.csv`: experimento controlado para isolar comportamento do DSU.

### Colunas principais (`experimento.csv`)
- `tipo_grafo`: `conexo` ou `desconexo`.
- `tipo_dsu`: `naive`, `rank` ou `tarjan`.
- `n_vertices`, `n_arestas`: tamanho da entrada.
- `repeticao`: repetição do teste.
- `tempo_ms`, `tempo_ns`: tempo medido.
- `operacoes_find`, `operacoes_union`, `total_operacoes`: contadores de operações.
- `total_acessos`: soma de acessos a `pai` e `rank`.

### Colunas principais (`experimento_micro.csv`)
- `cenario`: atualmente `cadeia_find0`.
- `tipo_dsu`: `naive`, `rank` ou `tarjan`.
- `n`: tamanho da estrutura.
- `num_unions`: quantidade de unions executadas.
- `num_finds`: quantidade lógica de finds executadas no cenário.
- métricas de tempo e acessos iguais ao experimento principal.

## Gráficos
Os gráficos ficam em:
- `graficos/kruskal/`
- `graficos/micro/`

