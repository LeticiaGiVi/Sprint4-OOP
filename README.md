# API REST - Manutenção Rodoviária (Spring Boot + JPA + Oracle)

Fluxo: Cliente HTTP -> Controller -> Service -> Repository -> Oracle

API REST construída com **Spring Boot 4.1 + Spring Data JPA + Oracle** para gerenciar trechos de rodovia, equipes de manutenção, intervenções operacionais (pulverização e roçada) e relatórios de prioridade.
 
Projeto da **Sprint 4**: migração do protótipo com JDBC puro (Sprint 3) para uma API REST em camadas.


## Configuração
Defina as variáveis de ambiente (IntelliJ: Run > Edit Configurations):
`DB_URL`, `DB_USER`, `DB_PASSWORD` 

## Tecnologias
 
| Item | Versão / detalhe |
|---|---|
| Java | 17 |
| Spring Boot | 4.1.2 |
| Spring Web MVC | `spring-boot-starter-webmvc` |
| Spring Data JPA (Hibernate) | `spring-boot-starter-data-jpa` |
| Banco de dados | Oracle FIAP (`oracle.fiap.com.br:1521:ORCL`) |
| Driver JDBC | `ojdbc17` (dependência Maven) |
| Build | Maven |


## Endpoints
| Recurso | Rotas |
|---|---|
| /api/equipes | GET, GET/{id}, GET /funcao/{f}, POST, PUT/{id}, PATCH /{id}/membros/{n}, DELETE/{id} |
| /api/trechos | GET, GET/{km}, GET /prioridade, GET /vegetacao/acima/{n}, POST, PUT/{km}, PATCH /{km}/vegetacao/{n}, DELETE/{km} |
| /api/intervencoes | GET, GET/{id}, GET /{id}/resumo, GET /operador/{nome}, POST (campo "tipo"), DELETE/{id} |
| /api/relatorios | GET, GET/{id}, POST /gerar |

## Como executar
 
### 1. Pré-requisitos
- JDK 17 ou superior
- Maven 3.9+ (ou o Maven embutido do IntelliJ)
- Acesso à rede do Oracle 
### 2. Configurar as credenciais
Nenhuma credencial fica no código.
 
| Variável | Descrição |
|---|---|
| `DB_URL` | URL JDBC. Padrão: `jdbc:oracle:thin:@oracle`|
| `DB_USER` | Usuário do banco  | 
| `DB_PASSWORD` | Senha do banco | 

**IntelliJ:** `Run > Edit Configurations > Environment variables` → `DB_USER=xxxxx;DB_PASSWORD=sua_senha`

## Testando com cURL
 
Os corpos JSON estão em [`manutencao-rodoviaria/requisicoes/`](manutencao-rodoviaria/requisicoes). Execute os comandos **na pasta `manutencao-rodoviaria`** (para o caminho `@requisicoes/...` funcionar), com a API rodando em outro terminal.
 
**PowerShell (`curl.exe`)** — no Linux/macOS, troque `curl.exe` por `curl`.
 
```powershell
$url = "http://localhost:8080/api"
 
# Equipes
curl.exe -i -X POST "$url/equipes" -H "Content-Type: application/json" --data-binary "@requisicoes/equipe.json"
curl.exe -i "$url/equipes"
curl.exe -i "$url/equipes/1"
 
# Trechos
curl.exe -i -X POST "$url/trechos" -H "Content-Type: application/json" --data-binary "@requisicoes/trecho_urgente.json"
curl.exe -i -X POST "$url/trechos" -H "Content-Type: application/json" --data-binary "@requisicoes/trecho_normal.json"
curl.exe -i "$url/trechos"
curl.exe -i "$url/trechos/prioridade"
curl.exe -i "$url/trechos/vegetacao/acima/50"
 
# Intervenções
curl.exe -i -X POST "$url/intervencoes" -H "Content-Type: application/json" --data-binary "@requisicoes/pulverizacao.json"
curl.exe -i -X POST "$url/intervencoes" -H "Content-Type: application/json" --data-binary "@requisicoes/rocada.json"
curl.exe -i "$url/intervencoes/1/resumo"
curl.exe -i "$url/intervencoes/operador/Carlos"
curl.exe -i -X PUT "$url/intervencoes/1" -H "Content-Type: application/json" --data-binary "@requisicoes/pulverizacao_atualizada.json"
 
# Relatórios
curl.exe -i -X POST "$url/relatorios"
curl.exe -i "$url/relatorios"
curl.exe -i "$url/relatorios/periodo?inicio=2026-10-01&fim=2026-10-31"
 
# Erros esperados
curl.exe -i -X POST "$url/trechos" -H "Content-Type: application/json" --data-binary "@requisicoes/trecho_invalido.json"   # 400
curl.exe -i "$url/trechos/999999"                                                                                       # 404
 
# Remoção
curl.exe -i -X DELETE "$url/equipes/1"                                                                                  # 204
```
 
Arquivos de apoio em `requisicoes/`: `equipe.json`, `trecho_urgente.json`, `trecho_normal.json`, `trecho_invalido.json`, `pulverizacao.json`, `pulverizacao_atualizada.json`, `rocada.json`.

 

