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

- @PostMapping
- public void cadastrar(@RequestBody DadosCadastroPaciente dados) {
- - System.out.println("dados recebido: " +dados);
- }

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

## Spring Data JPA


Nessa aula, você aprendeu como:

- Adicionar novas dependências no projeto;
- Mapear uma entidade JPA e criar uma interface Repository para ela;
- Utilizar o Flyway como ferramenta de Migrations do projeto;
- Realizar validações com Bean Validation utilizando algumas de suas anotações, como a @NotBlank.

Você precisará criar a entidade Paciente:

```
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

- @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
- private Long id;

- private String nome;
- private String email;
- private String cpf;
- private String telefone;

- @Embedded
- private Endereco endereco;

- public Paciente(DadosCadastroPaciente dados) {
- - this.nome = dados.nome();
- - this.email = dados.email();
- - this.telefone = dados.telefone();
- - this.cpf = dados.cpf();
- - this.endereco = new Endereco(dados.endereco());
- }

}
```

Na sequência, precisará criar um repository:

```
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
```

Depois, precisará alterar as classes Controller e DTO:

```
    @RestController
    @RequestMapping("pacientes")
    public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
    repository.save(new Paciente(dados));
}

public record DadosCadastroPaciente(
@NotBlank String nome,
@NotBlank @Email String email,
@NotBlank String telefone,
@NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}") String cpf,
@NotNull @Valid DadosEndereco endereco
) {
}
```

E, por fim, vai precisar criar uma migration (Atenção! Lembre-se de parar o projeto antes de criar a migration!):

```
create table pacientes(
id bigint not null auto_increment,
nome varchar(100) not null,
email varchar(100) not null unique,
cpf varchar(14) not null unique,
telefone varchar(20) not null,
logradouro varchar(100) not null,
bairro varchar(100) not null,
cep varchar(9) not null,
complemento varchar(100),
numero varchar(20),
uf char(2) not null,
cidade varchar(100) not null,
primary key(id)
);
```