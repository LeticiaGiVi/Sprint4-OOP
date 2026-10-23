# API REST - Manutenção Rodoviária (Spring Boot + JPA + Oracle)

Fluxo: Cliente HTTP -> Controller -> Service -> Repository -> Oracle

## Configuração
Defina as variáveis de ambiente (IntelliJ: Run > Edit Configurations):
`DB_URL`, `DB_USER`, `DB_PASSWORD`  (não coloque senha no código).

## Endpoints
| Recurso | Rotas |
|---|---|
| /api/equipes | GET, GET/{id}, GET /funcao/{f}, POST, PUT/{id}, PATCH /{id}/membros/{n}, DELETE/{id} |
| /api/trechos | GET, GET/{km}, GET /prioridade, GET /vegetacao/acima/{n}, POST, PUT/{km}, PATCH /{km}/vegetacao/{n}, DELETE/{km} |
| /api/intervencoes | GET, GET/{id}, GET /{id}/resumo, GET /operador/{nome}, POST (campo "tipo"), DELETE/{id} |
| /api/relatorios | GET, GET/{id}, POST /gerar |

## Testes (PowerShell)
$url = "http://localhost:8080/api"
curl.exe -i -X POST "$url/equipes" -H "Content-Type: application/json" --data-binary "@requisicoes/equipe.json"
curl.exe -i -X POST "$url/trechos" -H "Content-Type: application/json" --data-binary "@requisicoes/trecho_urgente.json"
curl.exe -i -X POST "$url/trechos" -H "Content-Type: application/json" --data-binary "@requisicoes/trecho_normal.json"
curl.exe -i "$url/trechos/prioridade"
curl.exe -i -X POST "$url/relatorios/gerar"
curl.exe -i -X POST "$url/intervencoes" -H "Content-Type: application/json" --data-binary "@requisicoes/pulverizacao.json"
curl.exe -i -X POST "$url/intervencoes" -H "Content-Type: application/json" --data-binary "@requisicoes/rocada.json"
curl.exe -i -X POST "$url/trechos" -H "Content-Type: application/json" --data-binary "@requisicoes/trecho_invalido.json"   # esperado: 400
curl.exe -i "$url/trechos/999999"   # esperado: 404
