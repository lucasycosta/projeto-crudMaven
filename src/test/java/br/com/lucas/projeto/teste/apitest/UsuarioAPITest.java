package br.com.lucas.projeto.teste.apitest;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.lucas.projeto.teste.domain.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class UsuarioAPITest {
	
	private static Long id;
	
	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8080/usuario/";
	}

	@Test
	@Order(1)
	public void naoDevoCadastrarUsuarioComNomeVazio() {
		log.info("** Test - naoDevoCadastrarUsuarioComNomeVazio");
		
		log.info("Gravar Usuario");
		RestAssured.given()
			.body("{\"email\": \"jose@mail.com\", \"cpf\": \"315.097.517-42\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("")
		.then()
			.log().all()
			.statusCode(400)
			.body("nome", CoreMatchers.is("Campo não informado"));
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarUsuarioComNomeIniciadoComLetraMinuscula() {
		log.info("** Test - naoDevoCadastrarUsuarioComNomeIniciadoComLetraMinuscula");
		
		log.info("Gravar Usuario");
		RestAssured.given()
			.body("{\"nome\": \"jose maria da silva\", \"email\": \"jose@mail.com\", \"cpf\": \"315.097.517-42\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("")
		.then()
			.log().all()
			.statusCode(400)
			.body("nome", CoreMatchers.is("Nome deve começar com letra maiúscula"));
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarUsuarioComEmailVazio() {
		log.info("** Test - naoDevoCadastrarUsuarioComEmailVazio");
		
		log.info("Gravar Usuario");
		RestAssured.given()
			.body("{\"nome\": \"José Maria da Silva\", \"cpf\": \"315.097.517-42\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("")
		.then()
			.log().all()
			.statusCode(400)
			.body("email", CoreMatchers.is("Campo não informado"));
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarUsuarioComEmailInvalido() {
		log.info("** Test - naoDevoCadastrarUsuarioComEmailInvalido");
		
		log.info("Gravar Usuario");
		RestAssured.given()
			.body("{\"nome\": \"José Maria da Silva\", \"email\": \"josemailcom\", \"cpf\": \"315.097.517-42\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("")
		.then()
			.log().all()
			.statusCode(400)
			.body("email", CoreMatchers.is("Email inválido"));
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarUsuarioComCPFVazio() {
		log.info("** Test - naoDevoCadastrarUsuarioComCPFVazio");
		
		log.info("Gravar Usuario");
		RestAssured.given()
			.body("{\"nome\": \"José Maria da Silva\", \"email\": \"josemailcom\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("")
		.then()
			.log().all()
			.statusCode(400)
			.body("cpf", CoreMatchers.is("Campo não informado"));
		
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarUsuarioComCPFInvalido() {
		log.info("** Test - naoDevoCadastrarUsuarioComCPFInvalido");
		
		log.info("Gravar Usuario");
		RestAssured.given()
			.body("{\"nome\": \"José Maria da Silva\", \"email\": \"jose@mail.com\", \"cpf\": \"123.123.123-00\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("")
		.then()
			.log().all()
			.statusCode(400)
			.body("cpf", CoreMatchers.is("CPF inválido"));
	}
	
	@Test
	@Order(2)
	public void devoCadastrarUsuario() {
		log.info("** Test - devoCadastrarUsuario");

		log.info("Gravar Usuario");
		RestAssured.given()
			.body("{\"nome\": \"José Maria da Silva\", \"email\": \"jose@mail.com\", \"cpf\": \"315.097.517-42\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("")
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	@Order(3)
	public void devoBuscarTodosUsuarios() {
		log.info("** Test - devoBuscarTodosUsuarios");

		log.info("Buscar todos usuarios");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		response.then().log().all().statusCode(200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<Usuario> lista =  jsonPathEvaluator.getList("", Usuario.class);
		id = lista.get(lista.size()-1).getId();
		log.debug("Valor ID: " + id);
	}
	
	@Test
	@Order(4)
	public void devoBuscarUsuarioPorId() {
		log.info("** Test - devoBuscarTodosUsuarios");

		log.info("Buscar usuario por id");
		RestAssured.given()
		.when()
			.get(id.toString())
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	@Order(5)
	public void devoAtualizarUsuario() {
		log.info("** Test - devoBuscarTodosUsuarios");
		log.info("Gravar Usuario");
		RestAssured.given()
			.body("{\"id\": " + id + ", \"nome\": \"Adoniran Barbosa\", \"email\": \"jose@mail.com\", \"cpf\": \"315.097.517-42\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("")
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	@Order(6)
	public void devoExcluirUsuarioPorId() {
		log.info("** Test - devoBuscarTodosUsuarios");

		log.info("Excluir usuario");
		RestAssured.given()
		.when()
			.delete(id.toString())
		.then()
			.log().all()
			.statusCode(200);
	}

	
}
