# urna-eletronica-rmi
Real-Time Election RMI

Descrição:

Este projeto é uma simulação de um sistema de votação para uma eleição real, desenvolvida como parte do trabalho da disciplina de Sistemas Distribuídos da UTFPR. A aplicação emula o funcionamento de urnas eletrônicas e um servidor de apuração de votos utilizando Java e Maven.

Servidor RMI:
- Contagem total de votos.
- Recepção de votos com nomes e números de candidatos.
- Atualização e exibição da contagem de votos a cada 5 segundos.

Urnas (Clientes RMI):
- Envio de nomes e números de votos para o servidor.
- Entrada de dados via console.

Tecnologias Utilizadas:
- Linguagem: Java
- Gerenciador de Dependências: Maven
- RMI: Remote Method Invocation para comunicação entre cliente e servidor