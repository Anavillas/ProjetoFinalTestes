# 🧪 Projeto de Testes Automatizados com Selenium - OrangeHRM

Este projeto contém uma suíte de testes automatizados desenvolvidos em **Java**, utilizando **JUnit 4** e **Selenium WebDriver**, com foco na automação de testes funcionais da plataforma [OrangeHRM](https://opensource-demo.orangehrmlive.com/). (DEU UM TRABALHÃO, MAS DEU CERTO)


## 🔧 Estrutura dos Testes

Os testes estão organizados em uma única classe `Testes.java`, com métodos nomeados sequencialmente (`test01`, `test02`, ..., `test13`) para garantir a ordem de execução com `@FixMethodOrder`.

| Teste | Descrição |
|-------|-----------|
| `test01_testLoginFluxoCompleto` | Realiza login com credenciais válidas |
| `test02_adicionarFuncionario` | Adiciona novo funcionário no módulo PIM |
| `test03_buscarFuncionario` | Busca funcionário criado |
| `test04_alterarDadosFuncionario` | Altera dados do funcionário |
| `test05_adicionarUsuario` | Cria novo usuário vinculado a um funcionário |
| `test06_editarEdeletarUser` | Edita e deleta um usuário do sistema |
| `test07_criarPublicacao` | Cria publicação no módulo Buzz |
| `test08_curtirPublicacao` | Curte uma publicação |
| `test09_comentarPublicacao` | Comenta em uma publicação |
| `test10_compartilharPublicacao` | Compartilha uma publicação |
| `test11_verPlanilhaHoras` | Visualiza planilha de horas no módulo Time |
| `test12_Directory` | Pesquisa por funcionários no diretório |
| `test13_SairFinalmente` | Realiza logout do sistema |

---

## ▶️ Como Executar

1. Clone este repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
