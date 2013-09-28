package com.webimport.webtable;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.webimport.WebImportBehaviour;

public class RowBehaviour extends WebImportBehaviour {

	public RowBehaviour() throws IOException {
		super("http://www.chicanef1.com/list.pl?who=a&hide=&indy=&init=%23&nc=0");
	}

	@Test
	public void shouldGetRefAndDescriptionFromCell() {
		Row row = webSource.tables().get(7).rows().get(1);
		assertThat(row.getFirstRef(1), is("indiv.pl?name=3-L%20Racing%20Team&type=a"));
		assertThat(row.getData(1), is("3-L Racing Team (Indy only)"));
	}

	@Test
	public void shouldReturnNullIfNoRefInCell() {
		assertThat(webSource.tables().get(7).rows().get(0).getFirstRef(1), is(nullValue()));
	}

	@Test
	public void shouldGetManyRefsFromCell() {
		assertThat(webSource.tables().get(5).rows().get(0).getRefs(0), hasSize(24));
	}
	
	@Test
	public void shouldReturnAllRefsFromCellRecursively() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		webSource.goToPage("http://wildsoft.ru/cir.php?l=A&id=61");
		assertThat(webSource.tables().get(1).rows().get(1).getRefs(0), hasSize(70));
	}

}

