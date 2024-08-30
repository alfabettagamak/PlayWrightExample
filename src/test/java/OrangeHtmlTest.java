import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class OrangeHtmlTest {

    static BrowserContext context;
    static Playwright playwright;
    Page page;

    @BeforeAll
    public static void beforeClass(){
        playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
    }

    @BeforeEach
    public void beforeEach(){
        page = context.newPage();
    }

    @AfterEach
    public void afterEach(){
        page.close();
    }

    @AfterAll
    public static void afterAll(){
        context.close();
        playwright.close();
    }


    @Test
    public void secondPageTesting() throws InterruptedException {
        new MainPage(page).open().auth();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("My Info")).click();
        page.reload();
        Locator locator = page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("profile picture")).nth(1);
        assertThat(locator).isVisible();
    }

    @Test
    public void adminTesting() throws InterruptedException {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        page.pause();
        Thread.sleep(6000);
        assertThat(page.locator("//*[text()='Admin']")).hasText("Dashboard");
    }
}
