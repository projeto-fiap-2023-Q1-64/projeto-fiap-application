# Projeto de pós-graduação (Fiap/Alura) do grupo 2023-Q1-64
## Integrantes
- Ademar Terra
- Aline Santos
- Danilo LR
- Mario Sclavo
- Richard Cardoso

---
## Entrega
[docker-compose.yml](entrega/docker-compose.yml)

---
## Comandos do docker
### Iniciar containers (aplicação e banco)
```
docker-compose up --force-recreate --build
```
### Parar e remover containers (aplicação e banco)
```
docker-compose down -v
```
### Atualizar a imagem no repositório remoto
```
docker login

#informar usuário e senha

docker push rm350010/projeto-fiap-1:latest
```
### Para acessar o swagger

> http://localhost:8080/swagger-ui/index.html

### Prefixos de commit e suas finalidades
```
feat: (new feature for the user, not a new feature for build script)
fix: (bug fix for the user, not a fix to a build script)
docs: (changes to the documentation)
style: (formatting, missing semi colons, etc; no production code change)
refactor: (refactoring production code, eg. renaming a variable)
test: (adding missing tests, refactoring tests; no production code change)
chore: (updating grunt tasks etc; no production code change)
```