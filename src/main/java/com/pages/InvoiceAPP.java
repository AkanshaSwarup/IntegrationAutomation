package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;


import helper.Webdriverbase;
import helper.Wrapperdriver;

public class InvoiceAPP extends Webdriverbase{

	Wrapperdriver wrapperdriver;

	String invoicenumber= "5544";


	public By Home= By.xpath("//*[@class=\"drawerContent__SideBarDark__2k2LWKZ_ThQkeAdH7ClCaY\"]//*[@class='material-icons qa-home-icon']");

	public By FilterBar= By.xpath("//span[text()='Filter']//parent::div//child::div//input[@role=\"input\"]");


	public By InvoiceAp= By.xpath("//div[text()='Invoices']");

	public By InvFilter= By.xpath("//div[text()='Invoice Number']//parent::div[@class='grid-sort__indexDark__3sdD2cwpQEYYGnVe8LDBrc header-ellipsis-visible__indexDark__2qEXSV7B4ngBjl_Q0k_Dd7 qa-grid-filter-container']//following-sibling::div");

	public By Clear = By.xpath("//button[text()=\"Clear\"]");
	
	public By filter= By.xpath("//input[@id='name.$eq']");

	public By Apply= By.xpath("//button[text()=\"Apply\"]");

	public By APinbound= By.xpath("//div[@data-key=\"Project Name\"]");

	public By FiltersApplied = By.xpath("//span[text()='FILTERS APPLIED']//parent::div//child::div");


	public By ABphase= By.xpath("//div[@class=\"container__PhaseBoxUiDark__3smQAI2LjRK0nbF6tb-9a0 qa-phasebox\"]//child::span[@class=\"buttonLabel__ExpandableMenuDark__3gMVBRrgdD-ufbBTQn3xdp\"]");

	public By Payreq= By.xpath("//*[@id=\"2\"]");

	public By InvPayLink= By.xpath("//div[@class=\"grid-td__indexDark__1y4r_xZR8A9GGVRjtJv5tR tableCell__indexDark__1i01MORh7expq7wSANf6Hy\"]//child::div[@data-key=\"Name\"]//child::a");

	public By BPphase= By.xpath("//div[@class='container__ExpandableMenuDark__2sI4a0ibkoExwD8BNA-KUS']//child::span[@class='buttonLabel__ExpandableMenuDark__3gMVBRrgdD-ufbBTQn3xdp']");

	public By BPInvAmount= By.xpath("//div[@id='p_invoice_amount']");

	public By BPPayMethod= By.xpath("//div[@id='p_payment_method']");

	public InvoiceAPP()
	{
		PageFactory.initElements(Driver, this);
		wrapperdriver = new Wrapperdriver(Driver);

	}

	public void FetchInvoiceData() {

		wrapperdriver.clickOnWebElement(Home);
		wrapperdriver.clearElement(FilterBar);


		wrapperdriver.sendKeysToWebelement(FilterBar, "Invoice");

		wrapperdriver.clickOnWebElement(InvoiceAp);
		
		wrapperdriver.clickOnWebElement(FiltersApplied);
		
		wrapperdriver.clickOnWebElement(InvFilter);

		wrapperdriver.sendKeysToWebelement(filter, invoicenumber);


		wrapperdriver.clickOnWebElement(Apply); 


		Driver.findElement(By.xpath("//div[@data-key=\"Invoice Number\"]//child::a[@title='"+invoicenumber+"']")).click();
		//	invlink.click();

		String Invoices_app_phase =  wrapperdriver.getTextOfWebElement(ABphase);

		wrapperdriver.clickOnWebElement(Payreq);
		wrapperdriver.clickOnWebElement(InvPayLink);

		String Invoice_Payment_Phase = wrapperdriver.getTextOfWebElement(BPphase); 
		

		String Invoice_Amount  = wrapperdriver.getTextOfWebElement(BPInvAmount);

		String Payment_Method = wrapperdriver.getTextOfWebElement(BPPayMethod);
		
		System.out.println(Invoices_app_phase + " - Invoices app phase");
		System.out.println(Invoice_Payment_Phase + " - Invoice Payment app Phase");
		
		System.out.println(Invoice_Amount + " - Invoice Amount");
		System.out.println(Payment_Method +" - Payment Method");
		


		Driver.close();

	}

}

