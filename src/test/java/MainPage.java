import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class MainPage {

    Page page;
    BrowserContext context;
    Locator login;
    Locator password;
    Locator submit;

    public MainPage(Page page) {
        this.page = page;
        login = page.getByPlaceholder("Username");
        password = page.getByPlaceholder("Password");
        submit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"));
    }

    public MainPage open(){
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        return this;
    }

    public MainPage auth(){
        login.fill("Admin");
        password.fill("admin123", new Locator.FillOptions().setTimeout(2000));
        submit.click();
        return this;
    }

}
