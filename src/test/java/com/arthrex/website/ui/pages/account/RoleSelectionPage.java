package com.arthrex.website.ui.pages.account;

import com.arthrex.website.ui.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleSelectionPage extends BasePage {
    public RoleSelectionPage(WebDriver driver) {
        super(driver);
    }

    public Map<String, String> getAccountRoles() {
        Map<String, String> mapAccountRoles = new HashMap<>();

        List<WebElement> lstAccountRoles = this.driver.findElements(By.className("role-content"));

        for (WebElement accountRole : lstAccountRoles) {
            WebElement roleTitle = accountRole.findElement(By.className("role-title"));
            WebElement roleDescription = accountRole.findElement(By.className("role-specialties"));
            mapAccountRoles.putIfAbsent(roleTitle.getText(), roleDescription.getText());
        }

        return mapAccountRoles;
    }

    public SubscriptionInfoPage selectAccountRole(String accountRole) {
        final String accountRoleXPath = String.format("//span[text()='%s']", accountRole);
        WebElement elemAccountRole = this.driver.findElement(By.xpath(accountRoleXPath));
        this.click(elemAccountRole);

        return new SubscriptionInfoPage(this.driver);
    }
}
