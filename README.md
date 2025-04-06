# 📺 PUCFlix 

PUCFlix é um projeto desenvolvido como parte do Trabalho Prático 1 da disciplina de **Algoritmos e Estruturas de Dados 3 (AEDS 3)** na **PUC Minas**. O objetivo do sistema é gerenciar séries e episódios, utilizando estrutura de arquivos e índices com **Árvores B+** e **Tabela Hash Extensível** para garantir eficiência e organização.

## ✅ Funcionalidades

O PUCFlix permite realizar operações de **CRUD** (Criar, Ler, Atualizar e Deletar) tanto para séries quanto para episódios. Cada série pode conter diversos episódios, enquanto cada episódio pertence exclusivamente a uma série, respeitando assim o relacionamento **1:N**.

### Recursos adicionais
- Garantia de integridade: séries que possuem episódios vinculados não podem ser excluídas.
- Organização de episódios por temporada, facilitando a navegação.
- Utilização de **Árvores B+** para manter o relacionamento entre séries e episódios.
- Uso de **Tabela Hash Extensível** como índice direto, otimizando buscas.

## 👥 Participantes
- **Vitor Leite Setragni**
- **Gabriel Henrique de Morais**
- **Mateus Martins Parreiras**

## 📦 Estrutura de Classes

### 📁 model
- **Serie.java**: Representa a entidade série, com os seguintes atributos:
  - id, nome, anoLancamento, sinopse, streaming
- **Episodio.java**: Representa a entidade episódio, com os atributos:
  - id, idSerie, nome, temporada, dataLancamento, duracao

### 📁 view
- **VisaoSeries.java**: Métodos para leitura e exibição de séries:
  - `Serie leSerie()`: Lê os dados de uma série fornecidos pelo usuário.
  - `void mostraSerie(Serie s)`: Exibe os dados de uma série.
- **VisaoEpisodios.java**: Métodos para leitura e exibição de episódios:
  - `Episodio leEpisodio(int idSerie)`: Lê um episódio vinculado a uma série.
  - `void mostraEpisodio(Episodio e)`: Exibe os dados de um episódio.

### 📁 controller
- **ControleSeries.java**: Controla o menu e as operações com séries:
  - Inclusão, alteração, busca e exclusão de séries.
  - Verificação de episódios vinculados antes da exclusão.
  - Visualização dos episódios organizados por temporada.
- **ControleEpisodios.java**: Gerencia os episódios vinculados às séries:
  - Inclusão, alteração, busca e exclusão de episódios.
  - Verificação da existência da série antes de vincular um episódio.

### Main.java
- Menu principal que organiza as operações:
  - Gerenciamento de séries.
  - Gerenciamento de episódios (após escolher uma série).
  - Opção para sair.

## 🧠 Desenvolvimento

O desenvolvimento do projeto exigiu atenção especial ao relacionamento entre as entidades e à estruturação dos dados em disco. A implementação das **Árvores B+** e da **Tabela Hash Extensível** foi desafiadora, mas trouxe ganhos significativos em eficiência, especialmente nas buscas e no relacionamento entre séries e episódios.

O projeto segue os princípios do padrão **MVC (Model-View-Controller)**, garantindo organização e separação clara de responsabilidades.

## 📋 Checklist
- [x] CRUD completo para séries e episódios
- [x] Relacionamento 1:N garantido entre séries e episódios
- [x] Implementação de Árvores B+ para vinculação
- [x] Uso de Tabela Hash Extensível para índice direto
- [x] Exclusão de séries condicionada à ausência de episódios vinculados
- [x] Inclusão de episódio limitada a séries existentes
- [x] Visualização por temporada
- [x] Testes completos e funcionamento garantido
- [x] Projeto original e completo

## 🔗 Repositório
[PUCFlix no GitHub](https://github.com/alicesalim/tp1_aeds3.git)

Projeto desenvolvido como parte do Trabalho Prático 1 da disciplina de AEDS 3 — PUC Minas.

