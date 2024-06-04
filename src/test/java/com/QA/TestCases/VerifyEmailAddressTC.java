package com.QA.TestCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import com.QA.BaseClass.BaseClass;
import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Link;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;

public class VerifyEmailAddressTC extends BaseClass {

	@Test(dataProvider = "AllData")
	public void VerifyUserEmailAddressProcessTC(String UserEmailAddress, String UserPassword, String UserName, String UserDOB)
			throws InterruptedException, IOException, MailosaurException, ParseException {
		String apiKey = "FdwLCia2bdaeMDQQhXKUHUbU6YwZFqTc";
		String serverId = "gsdhffoz";
		String serverDomain = "gsdhffoz.mailosaur.net";
		String From = "support@chancer.com";

		MailosaurClient mailosaur = new MailosaurClient(apiKey);
		MessageSearchParams params = new MessageSearchParams();
		params.withServer(serverId);
		System.out.println("Mail  = " + UserEmailAddress);
		System.out.println("From  = " + From);

		SearchCriteria criteria = new SearchCriteria();
		criteria.withSentTo(UserEmailAddress);
		criteria.withSentFrom(From);
		Message message = mailosaur.messages().get(params, criteria);

		String subject = message.subject();
		System.out.println("Mail subject = " + subject);
		Thread.sleep(1000);
		System.out.println("Mail subject = " + message.subject());
		System.out.println("Mail CC person = " + message.cc());
		System.out.println("Mail TO person = " + message.to().get(0).email());
		System.out.println("Mail From person = " + message.from().get(0).email());
		Thread.sleep(1000);
		assertNotNull(message);
		assertEquals("Chancer Account Activation", message.subject());

		String messagebody = message.text().body();
		System.out.println("Mail Text = " + messagebody);
		boolean containsText = messagebody.contains("Account Activation for smooth onboarding");
		System.out.println("Text exist in email = " + containsText); // true

		System.out.println("How many links = " + message.html().links().size()); // 2
		Link firstLink = message.html().links().get(0);
		System.out.println("Link is = " + firstLink.href());
		String linkUrl = firstLink.href();

		driver.switchTo().newWindow(WindowType.TAB);
		driver.get(linkUrl);
		driver.close();
		/*
		 * if(!linkUrl.trim().isEmpty()) { try (CloseableHttpClient httpClient =
		 * HttpClients.createDefault()) { HttpGet request = new HttpGet(linkUrl); try
		 * (CloseableHttpResponse response = httpClient.execute(request)) { // Print
		 * response status and content System.out.println("Status Code: " +
		 * response.getCode()); String responseBody =
		 * EntityUtils.toString(response.getEntity());
		 * System.out.println("Response Content: " + responseBody); } } } else {
		 * System.out.println("Link is empty or contains only spaces."); }
		 */
	}
}
