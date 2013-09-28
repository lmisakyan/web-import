package com.webimport.webtable;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;

public class Row {
	private final List<HtmlTableCell> cells;

	public Row(List<HtmlTableCell> cells) {
		this.cells = cells;
	}

	public String getData(int cellIndex) {
		return this.cells.get(cellIndex).asText();
	}

	public String getFirstRef(int cellIndex) {
		List<String> refs = getRefs(cellIndex);
		if (refs.size() > 0)
			return refs.get(0);
		return null;
	}

	public List<String> getRefs(int cellIndex) {
		return getRefsFromList(this.cells.get(cellIndex).getChildNodes());
	}

	private List<String> getRefsFromList(List<DomNode> elems) {
		List<String> result = new ArrayList<String>();
		for (DomNode elem : elems) {
			if (elem instanceof DomElement) {
				String ref = ((DomElement) elem).getAttribute("href");
				if (!ref.equals(DomElement.ATTRIBUTE_NOT_DEFINED) && !ref.equals(DomElement.ATTRIBUTE_VALUE_EMPTY))
					result.add(ref);
				else {
					result.addAll(getRefsFromList(elem.getChildNodes()));
				}
			}
		}
		return result;
	}
}
