package com.dimimport;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.List;

public class TestFB {
    public static void main(String[] args) throws IOException {
        final WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);

        final HtmlPage page1 = webClient.getPage("http://www.facebook.com");
        List<HtmlForm> forms = page1.getForms();
        final HtmlForm form = forms.get(0);

        /*System.out.println(form.getId());
        System.out.println(form.getInputsByName("email"));
        System.out.println(form.getInputsByName("pass"));
        System.out.println(form.getInputByValue("Log In"));*/
        final HtmlSubmitInput button = (HtmlSubmitInput)form.getInputByValue("Log In");
        final HtmlTextInput textField = form.getInputByName("email");
        textField.setValueAttribute("ls4md@yandex.ru");
        final HtmlPasswordInput textField2 = form.getInputByName("pass");
        textField2.setValueAttribute("tx#6lw1vd");
        final HtmlPage page2 = button.click();

        HtmlPage page = webClient.getPage("https://m.facebook.com/golubskikck/friends?all=1");
        System.out.println("------------------------===---------------------");
        System.out.println(page.asXml());
    }

}
