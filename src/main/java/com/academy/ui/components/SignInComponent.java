package com.academy.ui.components;

import com.academy.ui.components.sub.form.EmailField;
import com.academy.ui.components.sub.form.PasswordField;
import com.academy.ui.pages.ProfilePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInComponent extends BaseComponent{

    @FindBy(how = How.XPATH, using = ".//button[@type='submit']")
    private WebElement submitButton;

    @Getter
    private final EmailField email;
    @Getter
    private final PasswordField password;

    public SignInComponent(WebDriver driver, WebElement rootElement){
        super(driver, rootElement);
        email = new EmailField(driver, rootElement);
        password = new PasswordField(driver, rootElement);
    }

    public SignInComponent enterPassword(String text) {
        this.password.enter(text);
        return this;
    }

    public SignInComponent enterEmail(String text) {
        this.email.enter(text);
        return this;
    }

    public ProfilePage submitForm(){
        submitButton.click();
        return new ProfilePage(driver);
    }

}
