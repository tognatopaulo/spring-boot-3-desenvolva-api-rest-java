# spring-boot-3-desenvolva-api-rest-java

## Criacao do projeto

Nessa aula, você aprendeu como:

- Criar um projeto Spring Boot utilizando o site do Spring Initializr;
- Importar o projeto no IntelliJ e executar uma aplicação Spring Boot pela classe contendo o método main;
- Criar uma classe Controller e mapear uma URL nela utilizando as anotações @RestController e @RequestMapping;
- Realizar uma requisição de teste no browser acessando a URL mapeada no Controller.

```
@RestController
@RequestMapping("/hello")
public class HelloController {

- @GetMapping
- public String olaMundo() {
- - return "Hello World Spring!";
- }
}
```

## Requisições POST


Nessa aula, você aprendeu como:

- Mapear requisições POST em uma classe Controller;
- Enviar requisições POST para a API utilizando o Insomnia;
- Enviar dados para API no formato JSON;
- Utilizar a anotação @RequestBody para receber os dados do corpo da requisição em um parâmetro no Controller;
- Utilizar o padrão DTO (Data Transfer Object), via Java Records, para representar os dados recebidos em uma requisição POST.

Você precisará criar uma classe Controller:

```
@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroPaciente dados) {
        System.out.println("dados recebido: " +dados);
    }

}
```

Também precisará criar um DTO:

```
public record DadosCadastroPaciente(
String nome,
String email,
String telefone,
String cpf,
DadosEndereco endereco
) {
}
```

Já o DTO DadosEndereco será o mesmo utilizado na funcionalidade de cadastro de médicos.