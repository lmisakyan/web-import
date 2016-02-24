package com.webimport.webtable;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.webimport.WebImportBehaviour;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TableBehaviour extends WebImportBehaviour {

    public TableBehaviour() throws IOException {
        super("http://www.chicanef1.com/list.pl?who=a&hide=&indy=&init=%23&nc=0");
    }

    @Test
    public void shouldGetAllRowsFromTable() {
        assertThat(webSource.tables().get(7).rows(), hasSize(2));
    }

    @Test
    public void shouldGetRowsBetweenTwoHeaders() throws FailingHttpStatusCodeException, IOException {
        webSource.goToPage("http://www.chicanef1.com/indiv.pl?name=AGS&type=a");
        List<Row> rows = webSource.getTableByTitle("Driver").rowsBetweenHeaders("Entrant", "Chassis Designer");
        assertThat(rows, hasSize(3));
        assertThat(rows.get(0).getData(0), is("Jolly Club SpA"));
    }

    @Test
    public void shouldReturnWhetherRowExistsInTable() throws FailingHttpStatusCodeException, IOException {
        webSource.goToPage("http://www.chicanef1.com/indiv.pl?name=AGS&type=a");
        assertThat(webSource.getTableByTitle("Driver").rowExists("Chassis Designer"), is(true));
        assertThat(webSource.getTableByTitle("Driver").rowExists("blabla"), is(false));
    }

    @Test
    public void shouldReturnRowWithSpecifiedCellData() throws FailingHttpStatusCodeException, IOException {
        webSource.goToPage("http://www.chicanef1.com/indiv.pl?name=AGS&type=a");
        assertThat(webSource.getTableByTitle("Driver").getRowByCellData("Chassis Designer"), is(not(nullValue())));
        assertThat(webSource.getTableByTitle("Driver").getRowByCellData("blabla"), is(nullValue()));
    }

}
