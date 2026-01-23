# Processo Seletivo – Polícia Civil do Estado do Pará (PCPA)
Este projeto foi desenvolvido como parte do desafio técnico para o processo seletivo da Polícia Civil do Estado do Pará (PCPA), com foco na avaliação de competências em desenvolvimento back-end.

o desafio consiste no desenvolvimento de uma API simples em Laravel, contemplando um CRUD básico, utilizando Postgres como banco de dados e Docker para padronização do ambiente. Serão considerados diferenciais o uso adequado de Requests e Resources do Laravel, a correta validação das requisições, a aplicação apropriada dos princípios de orientação a objetos, a organização e limpeza do código, bem como o uso correto das bibliotecas disponibilizadas pelo framework Laravel 

## Resumo do projeto
CoffeeStockAPI é uma API REST destinada ao gerenciamento de inventário de cafeterias, desenvolvida com o framework Laravel 11 e documentada por meio do Swagger (OpenAPI).

A aplicação permite o controle eficiente de produtos, categorias e operações relacionadas ao estoque, utilizando endpoints HTTP padronizados e seguindo boas práticas de desenvolvimento de APIs REST.

## Como Iniciar (Ambiente Docker)

### 1. Clone the repository
```bash
git clone "https://github.com/Mariohanijar/CoffeStockApi.git](https://github.com/Mariohanijar/coffee-inventory-api.git"
cd coffee-inventory-api
```
### 2. Configurar Variáveis de Ambiente
```bash
cp .env.example .env
```
### 3. Subir o Ambiente
```bash
docker compose up -d --build
```
### 4. Configurar a Aplicação
```bash
# Instalar dependências do PHP
docker compose exec app composer install

# Gerar chave da aplicação
docker compose exec app php artisan key:generate

# Rodar as migrações do banco de dados
docker compose exec app php artisan migrate

# Gerar a documentação do Swagger
docker compose exec app php artisan l5-swagger:generate
```

## Acessando a Documentação
Após subir os containers, você pode acessar a interface do Swagger para testar os endpoints:
URL: http://localhost:8000/api/documentation

## Solução de Problemas
### Se ao tentar criar um produto (POST) você receber um erro de servidor informando que classes de validação não foram encontradas, as dependências podem estar incompletas no container. Resolva com:
```bash
docker compose exec app composer install --optimize-autoloader
```
### O Swagger não carrega ou dá "Failed to Fetch"
- Certifique-se de que o servidor está rodando: docker compose logs app.
- Verifique se o seu .env possui APP_URL=http://localhost:8000

## Civil Police of the State of Pará (PCPA) – Selection Process

This project was developed as part of the back-end technical challenge for the selection process of the Civil Police of the State of Pará (PCPA).

The challenge consists of developing a simple RESTful API using the Laravel framework, implementing basic CRUD (Create, Read, Update, and Delete) operations. The application uses PostgreSQL as the database management system and Docker to standardize and isolate the development environment.

The following aspects are considered technical differentiators in this project:
- Proper use of Laravel Form Requests and API Resources;
- Correct and secure request validation;
- Appropriate application of Object-Oriented Programming (OOP) principles;
- Clean, organized, and well-structured code;
- Correct usage of the libraries and features provided by the Laravel framework.

## Project Overview

CoffeeStockAPI is a RESTful API designed for coffee shop inventory management, developed using Laravel 11 and documented with Swagger (OpenAPI).

The API allows efficient control of products, categories, and inventory-related operations through standardized HTTP endpoints.

## Getting Started (Docker Environment)

### 1. Clone the repository
git clone https://github.com/Mariohanijar/coffee-inventory-api.git
cd coffee-inventory-api

### 2. Configure Environment Variables
cp .env.example .env

### 3. Start the Environment
docker compose up -d --build

### 4. Configure the Application

Install PHP dependencies:
docker compose exec app composer install

Generate application key:
docker compose exec app php artisan key:generate

Run database migrations:
docker compose exec app php artisan migrate

Generate Swagger documentation:
docker compose exec app php artisan l5-swagger:generate

## Accessing the API Documentation

After starting the containers, you can access the Swagger interface to test the endpoints:

URL: http://localhost:8000/api/documentation

## Troubleshooting

### Validation class not found error when creating a product (POST)

If you receive a server error indicating that validation classes were not found, the dependencies may not have been fully installed inside the container. Fix it by running:

docker compose exec app composer install --optimize-autoloader

### Swagger does not load or shows "Failed to Fetch"

- Ensure the application container is running:
docker compose logs app

- Verify that your .env file contains:
APP_URL=http://localhost:8000
