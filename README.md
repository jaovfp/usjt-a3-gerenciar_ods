# 🌍 ConectaODS

**ConectaODS** é um sistema de gerenciamento de ações voluntárias alinhadas com os Objetivos de Desenvolvimento Sustentável (ODS) da ONU. A plataforma permite que usuários cadastrem eventos (ações voluntárias), se inscrevam nessas ações voluntárias, e que administradores façam a gestão e aprovação das iniciativas.

---

## ✨ Funcionalidades

### 🔐 Autenticação e Segurança
- Login e logout de usuários
- Recuperação de senha via e-mail
- Cadastro de usuário
- Restrição de acesso (criação e inscrição em eventos) para usuários que não completaram o cadastro após autenticado

### 👤 Usuário
- Cadastro de novos usuários
- Edição de informações pessoais
- Upload de foto de perfil
- Visualização e cancelamento de inscrições em eventos
- Listagem de eventos aprovados disponíveis para inscrição
- Visualização do status das solicitações de eventos

### 📅 Eventos
- Cadastro de eventos por usuários
- Cada evento deve estar alinhado com uma ODS
- Aprovação ou rejeição de eventos pelo administrador
- Usuários podem se inscrever e cancelar a participação em eventos
- Usuários podem cancelar suas próprias solicitações de eventos ainda não aprovados

### 🧑‍💼 Administrador
- Visualização de todos os eventos pendentes
- Aprovação ou rejeição de eventos

### ✉️ Integrações
- Envio de e-mail para recuperação de senha
- Integração com a API dos Correios para preenchimento automático de endereço via CEP

---

## 🛠️ Tecnologias Utilizadas

- **Java 11**
- **Swing** (Interface Gráfica)
- **MySQL** (Banco de Dados)
- **JavaMail API** (Envio de e-mails)
- **API dos Correios (ViaCEP)** (Busca de endereço via CEP)

---

## 📸 Capturas de Tela

> 

---

## 🧪 Como Executar

1. Clonar o repositório em sua máquina;
2. Crie um arquivo .env na raiz do projeto baseada no arquivo .env.example;
3. Inicie o banco de dados MySQL usando Docker Compose:  
   Execute o comando abaixo na raiz do projeto:

   ```bash
   docker-compose up -d
4. Execute a aplicação pela classe principal GerenciarODS.java

---

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues, pull requests ou sugerir melhorias.

---

## 📦 Estrutura do projeto
├── domain/ # Entidades de domínios, value objects e interfaces de services e repositórios
├── app/ # DTOs e serviços da aplicação
├── infra/ # Implementações (Banco, Email, API Correios, Controller etc)
├── views/ # Telas Swing (cadastro, login, painel, etc)
├── common/ # Classes utilitárias e helpers
├── Main.java # Classe principal da aplicação
