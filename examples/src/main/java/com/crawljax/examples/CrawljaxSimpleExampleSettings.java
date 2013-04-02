package com.crawljax.examples;

import org.apache.commons.configuration.ConfigurationException;

import com.crawljax.browser.EmbeddedBrowser.BrowserType;
import com.crawljax.core.CrawljaxController;
import com.crawljax.core.configuration.CrawlSpecification;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.core.configuration.ThreadConfiguration;

/**
 * Simple Example.
 */
public final class CrawljaxSimpleExampleSettings {

	private static final String URL = "http://www.google.com";
	private static final int MAX_DEPTH = 1;
	private static final int MAX_NUMBER_STATES = 10;

	private CrawljaxSimpleExampleSettings() {

	}

	private static CrawljaxConfiguration getCrawljaxConfiguration() {
		CrawljaxConfiguration config = new CrawljaxConfiguration();
		config.setCrawlSpecification(getCrawlSpecification());
		config.setThreadConfiguration(getThreadConfiguration());
		config.setBrowser(BrowserType.firefox);

		// also crawl this URL
		config.alsoCrawl("http://www.facebook.com");
		config.alsoCrawl("http://video.google.ca/");
		config.alsoCrawl("http://www.blogger.com/");
		config.alsoCrawl("http://books.google.ca/");
		config.alsoCrawl("http://translate.google.ca/");
		config.alsoCrawl("http://picasaweb.google.ca/");
		config.alsoCrawl("http://maps.google.ca/");
		config.alsoCrawl("http://play.google.com/");
		config.alsoCrawl("http://www.youtube.com/");
		config.alsoCrawl("http://news.google.ca/");
		config.alsoCrawl("http://mail.google.com/");
		config.alsoCrawl("http://drive.google.com/");
		return config;
	}

	private static ThreadConfiguration getThreadConfiguration() {
		ThreadConfiguration tc = new ThreadConfiguration();
		tc.setBrowserBooting(true);
		tc.setNumberBrowsers(1);
		tc.setNumberThreads(1);
		return tc;
	}

	private static CrawlSpecification getCrawlSpecification() {
		CrawlSpecification crawler = new CrawlSpecification(URL);
		crawler.setRandomInputInForms(false);
		// click these elements

		crawler.click("a");
		crawler.click("button");

		// except these
		crawler.dontClick("a").underXPath("//DIV[@id='guser']");
		crawler.dontClick("a").withText("Language Tools");

		crawler.setInputSpecification(getInputSpecification());

		// limit the crawling scope
		crawler.setMaximumStates(MAX_NUMBER_STATES);
		crawler.setDepth(MAX_DEPTH);
		
		return crawler;
	}

	private static InputSpecification getInputSpecification() {
		InputSpecification input = new InputSpecification();
		input.field("gbqfq").setValue("Crawljax");
		return input;
	}

	/**
	 * @param args
	 *            the command line args
	 */
	public static void main(String[] args) throws ConfigurationException {
		CrawljaxController crawljax = new CrawljaxController(getCrawljaxConfiguration());
		crawljax.run();
	}

}
