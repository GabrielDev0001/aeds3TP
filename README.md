# 🎬 PUCFlix 1.0.0

PUCFlix é um projeto desenvolvido para o Trabalho Prático 1 da disciplina de **Algoritmos e Estruturas de Dados 3 (AEDS 3)** na **PUC Minas**. Este sistema tem como objetivo gerenciar séries e seus episódios, utilizando estruturas de arquivos e índices, especificamente **Árvores B+** e **Tabela Hash Extensível**, para alcançar eficiência e organização.

## 🚀 Funcionalidades

O PUCFlix permite operações de **CRUD** (Criar, Ler, Atualizar e Excluir) tanto para séries quanto para episódios. Cada série pode conter vários episódios, enquanto cada episódio está sempre vinculado a uma única série, garantindo um relacionamento **1:N**.

### Principais Características
- Integridade assegurada: séries com episódios associados não podem ser removidas.
- Organização clara dos episódios por temporada, proporcionando uma navegação fácil.
- Implementação de **Árvores B+** para gerenciar o vínculo entre séries e episódios.
- Utilização de **Tabela Hash Extensível** como índice direto para buscas rápidas.

## 👥 Equipe de Desenvolvimento
- **Vitor Leite Setragni**
- **Gabriel Henrique de Morais**
- **Mateus Martins Parreiras**

## 🗂️ Estrutura do Projeto

### 📁 Modelos (Entidade)
- **Serie.java**: Classe que define a entidade série, com os atributos:
  - id, nome, anoLancamento, sinopse, streaming
- **Episodio.java**: Classe que define a entidade episódio, com os atributos:
  - id, idSerie, nome, temporada, dataLancamento, duracao

### 📁 Visão (Menu)
- **VisaoSeries.java**: Métodos para leitura e apresentação de séries:
  - `Serie leSerie()`: Lê os dados de uma série inseridos pelo usuário.
  - `void mostraSerie(Serie s)`: Exibe as informações de uma série.
- **VisaoEpisodios.java**: Métodos para leitura e apresentação de episódios:
  - `Episodio leEpisodio(int idSerie)`: Lê os dados de um episódio vinculado a uma série.
  - `void mostraEpisodio(Episodio e)`: Exibe as informações de um episódio.

### 📁 Controle (controller)
- **ControleSeries.java**: Controla as funcionalidades relacionadas às séries:
  - Inclusão, alteração, busca e remoção de séries.
  - Verifica a existência de episódios antes da exclusão.
  - Permite a visualização dos episódios por temporada.
- **ControleEpisodios.java**: Controla as funcionalidades relacionadas aos episódios:
  - Inclusão, alteração, busca e remoção de episódios.
  - Garante que o episódio esteja vinculado a uma série existente.

### Main.java
- Interface principal que organiza o fluxo do sistema:
  - Menu para gestão de séries.
  - Menu para gestão de episódios (após selecionar uma série).
  - Opção de saída.

## 💡 Desafios de Desenvolvimento

A implementação das **Árvores B+** e da **Tabela Hash Extensível** foi um desafio significativo, pois exigiu um planejamento rigoroso para garantir o desempenho nas buscas e o correto relacionamento entre as séries e seus episódios.

O projeto segue o padrão arquitetural **MVC (Model-View-Controller)**, promovendo a separação de responsabilidades e facilitando a manutenção.

## ✅ Checklist de Funcionalidades
- [x] CRUD completo para séries e episódios
- [x] Relacionamento 1:N garantido entre séries e episódios
- [x] Implementação de Árvores B+ para vinculação
- [x] Uso de Tabela Hash Extensível para índice direto
- [x] Exclusão de séries condicionada à ausência de episódios vinculados
- [x] Inclusão de episódio restrita a séries existentes
- [x] Visualização por temporada
- [x] Testes concluídos e verificação de funcionamento
- [x] Projeto original e completo

## 📝 Desafios Encontrados
- Gabriel Henrique: Implementar a Hash, junto com a programação em Java, a qual não praticava a tempos. 

- Vitor Leite: Desenvolver o CRUD em java.

- Mateus Martins Parreiras: Implementar as árvores B+.

## 📂 Repositório
[PUCFlix no GitHub](https://github.com/alicesalim/tp1_aeds3.git)

Projeto realizado para o Trabalho Prático 1 da disciplina de AEDS 3 — PUC Minas.

