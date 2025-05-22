package utils;

import com.microsoft.playwright.Page;

public class PlaywrightContext {

	private static final ThreadLocal<Page> pageHolder = new ThreadLocal<>();

	public static Page getPage() {
		return pageHolder.get();
	}

	public static void setPage(Page page) {
		pageHolder.set(page);
	}

	public static void clear() {
		pageHolder.remove();
	}
}