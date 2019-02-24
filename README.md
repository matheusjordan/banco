Version 1.0.0 - MJ
1- Criação do Repositorio
2- Definição do escopo do programa

Version 1.1.0 - MJ
1- Cliente agora possui atributo EMAIL
2- Implementado Dto`s de LOGIN e SIGNUP
3- Implementado Service de CLIENTE
4- Implementado Controller de CLIENTE

Version 1.2.0 - MJ
1- Implementado Spring Security
2- Implementado Classe SecurityConfig
3- Implementado configurações básicas do Spring Security

Version 1.3.0 - MJ
1- Implementado perfis de Usuario
2- Classe Cliente renomeada para Usuario

Version 1.4.0 - MJ
1- Implementado UserDetails
2- Implementado verificação de usuario UserDetailsService
3- Implementado sobrecarga do metodo configure auth
4- Implementado o Bean BCryptPasswordEncoder

Version 1.5.0 - MJ
1- Implementado algoritmo de geração de token em JWTUtil
2- Implementado filtro de authenticação de usuarios
3- Implementado validação de authenticação no filtro
4- Implementado procedimento padrão caso usuário seja authenticado com sucesso
5- Implementado encriptação da senha do usuário conforme algoritmo HS512

Version 1.6.0 - MJ
1- Implementado filtro de autorização de usuários
2- Implementado algoritmo de validação de token
3- Implementado procedimento padrão caso usuário seja autorizado com sucesso
4- Autorização de usuários através de JWT feita com sucesso!

Version 1.7.0 -  MJ
1- Implementado regra de negócio de criação de contas
2- Adicionado ContaFactory com persistencia automatica
3- Adicionado ContaService e Repository