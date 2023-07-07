package steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.ConfigProvider;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static helpers.ConfigProvider.IS_USER_2_ADMIN;

public class Steps {
    private final SelenideElement loginButton = $x("//button[@id='login']");
    private final SelenideElement userNameField = $x("//input[@id='userName']");
    private final SelenideElement userPasswordField = $x("//input[@id='password']");
    private final SelenideElement searchBoxField = $x("//input[@id='searchBox']");
    private final SelenideElement searchButton = $x("//span[@class='input-group-text']");
    private final SelenideElement addToYourCollectionButton = $x("//button[@id='addNewRecordButton' and contains(text(), 'Add To Your Collection')]");
    private final SelenideElement profileButton = $x("//li[@id='item-3']/span[contains(text(), 'Profile')]");
    private final SelenideElement deleteAllBooksButton = $x("//div[@class='text-right button di']/button[@id='submit' and contains(text(), 'Delete All Books')]");
    private final SelenideElement confirmDeletionAllBooksButton = $x("//button[@id='closeSmallModal-ok']");
    private ElementsCollection searchResult;
    private SelenideElement nthSearchResult;

    private ElementsCollection setSearchResultXpath(String searchString) {
        String elementLocator = String.format(("//a[contains(text(), '%s')]"), searchString);
        return this.searchResult = $$x(elementLocator);
    }

    private SelenideElement setNthSearchResultXpath(String nthResult, String searchString) {
        String elementLocator = String.format(("(//a[contains(text(), '%s')])[%s]"), searchString, nthResult);
        return this.nthSearchResult = $x(elementLocator);
    }

    @When("User logs into website")
    public void userLogsIntoWebsite() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        loginButton.click();
        userNameField.sendKeys(System.getProperty("user1.admin.name"));
        userPasswordField.sendKeys(System.getProperty("user1.admin.password"));
        loginButton.click();

        /*
        //example, how to use ConfigProvider when reading properties from application.conf file
        String url = ConfigProvider.URL;
        if (IS_USER_2_ADMIN) {
            System.out.println("this user is indeed the admin on website " + url);
        } else {
            System.out.println("this user is not an admin o website " + url);
        }
        */
    }

    @When("User searches for a book with keyword {string}")
    public void userSearchesForABookWithKeyword(String searchString) {
        searchBoxField.sendKeys(searchString);
        searchButton.click();
    }

    @Then("Search result contains a book with keyword {string}")
    public void searchResultContainsABookWithKeyword(String searchString) {
        this.searchResult = setSearchResultXpath(searchString);
        Assertions.assertTrue(searchResult.size() > 0);
    }

    @Then("User adds book number {string} with keyword {string} into his collection")
    public void userAddsBookNumberWithKeywordIntoHisCollection(String nthBook, String searchKeyword) {
        this.nthSearchResult = setNthSearchResultXpath(nthBook, searchKeyword);
        nthSearchResult.click();
        addToYourCollectionButton.click();
        Alert alert = Selenide.webdriver().driver().switchTo().alert();
        Assertions.assertEquals("Book added to your collection.", alert.getText());
        alert.accept();
    }

    @Then("The book with keyword {string} is available in users profile")
    public void theBookWithKeywordIsAvailableInUsersProfile(String searchString) {
        this.searchResult = setSearchResultXpath(searchString);
    }

    @And("User removes all books from his profile")
    public void userRemovesAllBooksFromHisProfile() {
        profileButton.scrollIntoView(true);
        profileButton.click();
        deleteAllBooksButton.click();
        confirmDeletionAllBooksButton.click();
        Alert alertDeletionConfirmed = Selenide.webdriver().driver().switchTo().alert();
        Assertions.assertEquals("All Books deleted.", alertDeletionConfirmed.getText());
        alertDeletionConfirmed.accept();
    }
}
