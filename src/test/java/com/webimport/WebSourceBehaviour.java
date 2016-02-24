package com.webimport;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.webimport.webtable.Table;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class WebSourceBehaviour extends WebImportBehaviour {

    public WebSourceBehaviour() throws IOException {
        super("http://www.chicanef1.com/list.pl?who=a&nc=0");
    }

    @Test
    public void shouldFindAllTablesWithRowsInURI() {
        List<Table> webTables = webSource.tables();
        assertThat(webTables.size(), is(13));
        assertThat(webTables.get(0).rows().size(), is(not(0)));
    }

    @Test
    public void shouldFindTableWithSpecificTitle() {
        Table table = webSource.getTableByTitle("Team");
        assertThat(table, is(not(nullValue())));
    }

    @Test
    public void shouldReturnNullIfNoTableWithSpecificTitle() {
        Table table = webSource.getTableByTitle("BlaBla");
        assertThat(table, is(nullValue()));
    }

    @Test
    public void shouldGoToOtherPage() throws FailingHttpStatusCodeException, IOException {
        webSource.goToPage("http://www.chicanef1.com/indiv.pl?name=A%20E%20Dean&type=a");
        assertThat(webSource.currentUri(), is("http://www.chicanef1.com/indiv.pl?name=A%20E%20Dean&type=a"));
    }

    @Test
    public void shouldGetCurrentHost() {
        assertThat(webSource.currentHost(), is("www.chicanef1.com"));
    }

}