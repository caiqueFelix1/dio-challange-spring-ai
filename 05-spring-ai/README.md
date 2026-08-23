# Desafio DIO: API Inteligente com Spring AI

## 💡 O que o projeto faz
Este projeto é uma API de orçamento pessoal inteligente. Ele recebe comandos de voz (áudio) enviados pelo cliente, transforma em texto, usa IA (OpenAI) para entender a intenção do usuário e usa *Tool Calling* para executar funções reais no sistema, como criar uma transação financeira no banco de dados, e no final retorna uma resposta em áudio.

## 🚀 Como executar a aplicação
1. Tenha o Docker Desktop rodando na sua máquina.
2. Configure sua chave da OpenAI no terminal: `set OPENAI_API_KEY=sua-chave-aqui` (no Windows PowerShell, use `$env:OPENAI_API_KEY="sua-chave-aqui"`)
3. Rode o comando: `.\gradlew bootRun`
4. O banco de dados MySQL subirá automaticamente via Docker Compose.

## 🛠️ Qual melhoria foi implementada
1. **Validação de Regra de Negócio na Persistência de Transação**
Foi adicionada uma trava de segurança na classe `PersistTransactionUseCase`. Agora, antes de salvar uma nova transação financeira no banco de dados a partir de um comando da IA, a API verifica se o valor da transação é menor ou igual a zero. Caso seja, uma `IllegalArgumentException` é lançada, impedindo a criação de dados financeiros inválidos pelo modelo de IA.
2. **Nova Ferramenta de Consulta de Saldo (`QueryBalanceByCategoryUseCase`):**
   Criado um novo caso de uso anotado com `@Tool` para permitir que a Inteligência Artificial consulte e some de forma automatizada o saldo total de transações de uma categoria específica diretamente no banco de dados.

## 💻 Tecnologias utilizadas
- Java 25+
- Spring Boot
- Spring AI (Integração com OpenAI)
- Spring Data JPA
- MySQL
- Docker e Docker Compose

## 🧪 Como testar o fluxo principal
Basta enviar uma requisição HTTP POST para o endpoint da API passando um arquivo de áudio (ex: "gastei 50 reais com comida"). A API vai processar, validar (barrando valores negativos graças à nossa melhoria!) e salvar no MySQL.

## 🧠 O que eu aprendi durante o desafio
Neste projeto, eu aprendi como conectar uma Inteligência Artificial ao nosso código Java usando o Spring AI. Foi muito legal ver na prática a IA chamando funções do nosso sistema. Minha principal atuação foi achar onde o sistema salva a transação financeira (no arquivo PersistTransactionUseCase) e criar uma trava de segurança com o "if" para impedir que valores negativos ou igual a zero sejam salvos no banco. Também aprendi a subir o projeto com o Docker rodando tudo automático no fundo. Estou muito orgulhoso dessa trajetória!