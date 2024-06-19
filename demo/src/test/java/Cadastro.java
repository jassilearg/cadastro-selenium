import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class Cadastro {
    private WebDriver navegador;

    private Faker faker = new Faker();
    private String cidade = faker.address().city();
    private String estado = faker.address().state();
    private String pais = faker.address().country();
    private String nomeRua = faker.address().streetName();
    private String cep = faker.address().zipCode();
    private String email = faker.internet().emailAddress();
    private String nome = faker.name().firstName();
    private String sobrenome = faker.name().lastName();
    private String telefone = faker.phoneNumber().cellPhone();

    @BeforeTest
    public void setUp() {
        navegador = new ChromeDriver();
        navegador.get("https://automationexercise.com/login");
    }

    @Test
    public void chromeTest() {
        // Criar formulário
        WebElement loginParaCriarUsuario = navegador.findElement(By.className("signup-form"));

        // Preencher campo nome
        loginParaCriarUsuario.findElement(By.name("name")).sendKeys("curso");

        // Preencher campo email
        loginParaCriarUsuario.findElement(By.name("email")).sendKeys(email);

        // Clicar no botão sigup
        navegador.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();

        // Isolar e preencher formulário
        WebElement cadastro = navegador.findElement(By.id("form"));
        cadastro.findElement(By.id("id_gender1")).click();
        cadastro.findElement(By.id("password")).sendKeys("123456");
        cadastro.findElement(
                By.xpath("/html/body/section/div/div/div/div/form/div[5]/div/div[1]/div/select/option[18]")).click();
        cadastro.findElement(By.xpath("/html/body/section/div/div/div/div/form/div[5]/div/div[2]/div/select/option[6]"))
                .click();
        cadastro.findElement(
                By.xpath("/html/body/section/div/div/div/div/form/div[5]/div/div[3]/div/select/option[24]")).click();
        cadastro.findElement(By.id("newsletter")).click();
        cadastro.findElement(By.id("optin")).click();
        cadastro.findElement(By.id("first_name")).sendKeys(nome);
        cadastro.findElement(By.id("last_name")).sendKeys(sobrenome);
        cadastro.findElement(By.id("address1")).sendKeys(nomeRua);
        cadastro.findElement(By.xpath("/html/body/section/div/div/div/div/form/p[6]/select/option[4]")).click();
        cadastro.findElement(By.id("state")).sendKeys(estado);
        cadastro.findElement(By.id("city")).sendKeys(cidade);
        cadastro.findElement(By.id("zipcode")).sendKeys(cep);
        cadastro.findElement(By.id("mobile_number")).sendKeys(telefone);

        // Clicar e enviar cadastro
        cadastro.findElement(By.xpath("/html/body/section/div/div/div/div/form/button")).click();
        // Validar tela de sucesso
        navegador.findElement(By.xpath("//*[contains(text(), 'Account Created!')]"));
    }

    @AfterTest
    public void tearDown() {
        // Saindo do formulário
        navegador.quit();
    }

}
