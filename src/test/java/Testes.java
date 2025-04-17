import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Testes {
    private static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @BeforeClass
    public static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test // [ CASO DE TESTE CT001 ]
    public void test01_testLoginFluxoCompleto() {
        WebElement campoUsuario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        campoUsuario.clear();
        campoUsuario.sendKeys("Admin");

        WebElement campoSenha = driver.findElement(By.name("password"));
        campoSenha.clear();
        campoSenha.sendKeys("admin123");

        WebElement botaoLoginCerto = driver.findElement(By.xpath("//button[@type='submit']"));
        botaoLoginCerto.click();
    }

    @Test
    public void test02_adicionarFuncionario() throws InterruptedException {
        By menuPIM = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='PIM']]");
        WebElement pimLink = wait.until(ExpectedConditions.elementToBeClickable(menuPIM));
        pimLink.click();

        wait.until(ExpectedConditions.urlContains("/pim/viewEmployeeList"));
        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(., 'Add')]")));
        addButton.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));
        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='lastName']")));
        firstName.sendKeys("AndIwz");
        lastName.sendKeys("SATAWT1");

        WebElement idEmployee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Employee Id']/following::input[1]")));
        idEmployee.clear();
        idEmployee.sendKeys("ESSAT1");

        WebElement sendFuncionario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()[normalize-space()='Save']]")));
        sendFuncionario.click();
        Thread.sleep(7000);
    }

    @Test // [ CASO DE TESTE CT005 ]
    public void test03_buscarFuncionario() throws InterruptedException {
        driver.navigate().refresh();

        By menuPIM = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='PIM']]");
        WebElement pimLink = wait.until(ExpectedConditions.elementToBeClickable(menuPIM));
        pimLink.click();

        wait.until(ExpectedConditions.urlContains("/pim/viewEmployeeList"));

        WebElement campoBusca = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Employee Name']/following::input[@placeholder='Type for hints...'][1]")));
        campoBusca.clear();
        campoBusca.sendKeys("And");

        WebElement botaoBusca = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Search']")));
        botaoBusca.click();

        WebElement resultado = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='row']//div[contains(text(), 'AndIwz')]")));
        assert resultado.isDisplayed();
    }

    @Test
    public void test04_alterarDadosFuncionario() throws InterruptedException {
        By menuPIM = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='PIM']]");
        WebElement pimLink = wait.until(ExpectedConditions.elementToBeClickable(menuPIM));
        pimLink.click();

        WebElement employeeNameSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Employee Name']/following::input[@placeholder='Type for hints...'][1]")));
        employeeNameSearch.sendKeys("AndIwz");
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Search']")));
        searchButton.click();

        WebElement editarFuncionario = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class, 'bi-pencil-fill')]")));
        editarFuncionario.click();

        WebElement otherIdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Other Id']/following::input[1]")));
        otherIdInput.sendKeys("666621");

        WebElement nationalityDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Nationality']/following::div[contains(@class, 'oxd-select-text')][1]")));
        nationalityDropdown.click();
        Thread.sleep(1000);

        WebElement optionBrazilian = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']/span[text()='Brazilian']")));
        optionBrazilian.click();

        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit' and contains(@class, 'orangehrm-left-space') and normalize-space()='Save']")));
        saveButton.click();
    }

    @Test
    public void test05_adicionarUsuario() throws InterruptedException {
        By menuAdmin = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Admin']]");
        WebElement adminLink = wait.until(ExpectedConditions.elementToBeClickable(menuAdmin));
        adminLink.click();

        wait.until(ExpectedConditions.urlContains("/admin/viewSystemUsers"));

        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button') and contains(., 'Add')]")));
        addButton.click();
        wait.until(ExpectedConditions.urlContains("/admin/saveSystemUser"));

        WebElement dropdownRole = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='User Role']/following::div[contains(@class, 'oxd-select-text')][1]")));
        dropdownRole.click();
        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option' and contains(., 'Admin')]")));
        adminOption.click();

        WebElement dropdownStatus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Status']/following::div[contains(@class, 'oxd-select-text')][1]")));
        dropdownStatus.click();
        WebElement enabledOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option' and contains(., 'Enabled')]")));
        enabledOption.click();

        WebElement campoEmployee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Type for hints...']")));
        campoEmployee.sendKeys("AndIwz");

        WebElement sugestao = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[contains(text(),'AndIwz')]")));
        sugestao.click();

        WebElement username = driver.findElement(By.xpath("//label[text()='Username']/ancestor::div[contains(@class, 'oxd-input-group')]//input"));
        username.sendKeys("AndIwz");

        WebElement passwordCriar = driver.findElement(By.xpath("//label[text()='Password']/ancestor::div[contains(@class, 'oxd-input-group')]//input[@type='password']"));
        passwordCriar.sendKeys("Test@321And");

        WebElement confirmPasswordCriar = driver.findElement(By.xpath("//label[text()='Confirm Password']/ancestor::div[contains(@class, 'oxd-input-group')]//input[@type='password']"));
        confirmPasswordCriar.sendKeys("Test@321And");

        WebElement buttonSaveAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and normalize-space()='Save']")));
        new Actions(driver).moveToElement(buttonSaveAdd).click().perform();

        Thread.sleep(2000);
        List<WebElement> erros = driver.findElements(By.xpath("//*[contains(@class, 'oxd-input-field-error-message')]"));
        for (WebElement erro : erros) {
            System.out.println("Erro: " + erro.getText());
        }
    }

    @Test // [CASO DE TESTE CT008 E CT009]
    public void test06_editarEdeletarUser() throws InterruptedException {
        test03_buscarFuncionario();

        WebElement editarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class, 'bi-pencil-fill')]")));
        editarButton.click();
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'orangehrm-left-space') and normalize-space()='Save']")));
        saveButton.click();

        test03_buscarFuncionario();
        WebElement deletarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class, 'bi-trash')]")));
        deletarButton.click();
        WebElement deletarConfirmar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'oxd-button--label-danger') and normalize-space()='Yes, Delete']")));
        deletarConfirmar.click();
    }

    @Test
    public void test07_criarPublicacao() throws InterruptedException {
        By menuBuzz = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Buzz']]");
        WebElement buzzLink = wait.until(ExpectedConditions.elementToBeClickable(menuBuzz));
        buzzLink.click();
        Thread.sleep(1000);

        WebElement textArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@placeholder=\"What's on your mind?\"]")));
        textArea.click();
        textArea.sendKeys("Diva babilônica, barbarizou! Divouuuu");

        WebElement postButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body//button[normalize-space()='Post']")));
        postButton.click();
    }

    @Test
    public void test08_curtirPublicacao() throws InterruptedException {
        By menuBuzz = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Buzz']]");
        WebElement buzzLink = wait.until(ExpectedConditions.elementToBeClickable(menuBuzz));
        buzzLink.click();

        WebElement curtir = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[name()='svg' and contains(@class, 'orangehrm-heart-icon')]//*[name()='path']")));
        curtir.click();
    }

    @Test
    public void test09_comentarPublicacao() {
        By menuBuzz = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Buzz']]");
        WebElement buzzLink = wait.until(ExpectedConditions.elementToBeClickable(menuBuzz));
        buzzLink.click();

        WebElement comentarButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='oxd-icon-button' and .//i[contains(@class, 'bi-chat-text-fill')]]")));
        comentarButton.click();

        WebElement comentar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Write your comment...']")));
        comentar.sendKeys("AMEI DIVOU" + Keys.ENTER);
    }

    @Test
    public void test10_compartilharPublicacao() throws InterruptedException {
        By menuBuzz = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Buzz']]");
        WebElement buzzLink = wait.until(ExpectedConditions.elementToBeClickable(menuBuzz));
        buzzLink.click();

        WebElement compartilhar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[contains(@class, 'bi-share-fill')]")));
        compartilhar.click();

        WebElement compartilharButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//form[@class='oxd-form']//button[@type='submit' and contains(@class, 'oxd-button--main') and normalize-space()='Share']")));
        compartilharButton.click();
        Thread.sleep(5000);
    }

    @Test
    public void test11_verPlanilhaHoras() throws InterruptedException {
        By menuTime = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Time']]");
        WebElement timeLink = wait.until(ExpectedConditions.elementToBeClickable(menuTime));
        timeLink.click();
        Thread.sleep(2000);

        WebElement viewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'2022-15-08 - 2022-21-08')]/ancestor::div[@role='row']//button[text()=' View ']")
        ));
        viewButton.click();
    }

    @Test
    public void test12_Directory() throws InterruptedException {
        By menuDirectory = By.xpath("//a[contains(@class, 'oxd-main-menu-item') and .//span[text()='Directory']]");
        WebElement directoryLink = wait.until(ExpectedConditions.elementToBeClickable(menuDirectory));
        directoryLink.click();

        WebElement dropdownLocation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Location']/following::div[contains(@class,'oxd-select-text-input')][1]")));
        dropdownLocation.click();

        WebElement newYorkSelect = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[text()='New York Sales Office']")));
        newYorkSelect.click();

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(., 'Search')]")));
        searchButton.click();

        WebElement card = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'orangehrm-directory-card') and .//p[contains(normalize-space(), 'Sania Shaheen')]]")));
        card.click();
    }

    @Test
    public void test13_SairFinalmente() {
        WebElement usuarioDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'oxd-userdropdown-tab')]")));
        usuarioDropdown.click();

        WebElement sairButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@role='menuitem' and text()='Logout']")));
        sairButton.click();
    }
}
