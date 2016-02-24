package com.dimimport;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.webimport.WebSource;
import com.webimport.webtable.Row;
import com.webimport.webtable.Table;

import static org.apache.commons.io.FileUtils.writeStringToFile;

public class Test {

    /**
     * @param args
     * @throws IOException
     * @throws MalformedURLException
     * @throws FailingHttpStatusCodeException
     */
    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        /*WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
		HtmlPage page = webClient.getPage("http://wildsoft.ru/eng.php?l=A&id=200101022");
		System.out.println(page.asXml());*/
        File file = new File("D:\\tmp\\chicane.csv");
        WebSource ws = new WebSource("http://www.chicanef1.com/list.pl?who=a&nc=0");
        Table t = ws.getTableByTitle("Team");

        for (Row r : t.rows()) {
            for (int i = 0; i < 4; i++) {
                writeStringToFile(file, r.getData(i) + "," + (i == 3 ? "\n" : ""), "UTF-8", true);
            }
        }
    }

}
