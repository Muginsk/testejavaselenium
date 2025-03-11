# Testes Automatizados com Java e Selenium

## 📌 Descrição
Este projeto contém testes automatizados utilizando **Java** e **Selenium WebDriver** para validar funcionalidades de aplicações web. Os testes incluem a automação de fluxo de navegação, interação com elementos e validações de comportamento esperado.

## 🚀 Tecnologias Utilizadas
- **Java** - Linguagem utilizada para os testes
- **Selenium WebDriver** - Framework para automação de testes web
- **JUnit** - Framework de testes unitários
- **Maven** - Gerenciador de dependências
- **GitHub Actions** - Para execução dos testes em CI/CD

## 📂 Estrutura do Projeto
```
/testejavaselenium
│── src/test/java/
│   ├── tests/        # Casos de Teste
│── pom.xml           # Gerenciador de dependências Maven
│── README.md         # Documentação do projeto
│── target/           # Saída de compilação e relatórios de testes
```

## 🔧 Pré-requisitos
Antes de rodar os testes, certifique-se de ter instalado:
- **Java** (versão 11 ou superior)
- **Maven**
- **ChromeDriver** ou **GeckoDriver** (Firefox)

Para instalar as dependências, execute:
```sh
mvn clean install
```

## ▶️ Como Executar os Testes
### Executar os testes via Maven:
```sh
mvn test
```

### Executar testes especificando a classe:
```sh
mvn -Dtest=NomeDaClasseDeTeste test
```

## 📊 Relatório de Testes
Os relatórios de execução dos testes são gerados automaticamente na pasta `target/surefire-reports`.

### 📢 Importância do Relatório
Os relatórios fornecem uma visão clara dos testes executados, incluindo:
- Testes aprovados e falhos
- Tempo de execução de cada teste
- Logs de erro detalhados

Isso facilita a identificação de falhas e ajuda na melhoria da qualidade do software.

## 🛠 Configuração no GitHub Actions
O projeto possui um workflow configurado para executar os testes automaticamente no **GitHub Actions**. O workflow está localizado em:
```
.github/workflows/github_actions_selenium.yml
```

## 📄 Licença
Este projeto está sob a licença MIT. Sinta-se livre para utilizá-lo e contribuir!
