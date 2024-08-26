package locators;

public class Locator {

    //login
    public static String buttonTimezone = "//button[@type='button']";
    public static String textTimezoneVN = "//div[@class='timezone-picked']";
    public static String btn_changeTimezone = "//div[@class='timezone-picked']";
    public static String btn_timezoneJP = "//div[@class='options']";
    public static String inputEmail = "//input[@id='normal_login_username']";
    public static String inputPassword = "//input[@id='normal_login_password']";
    public static String buttonLogin = "//button[@type='submit']";
    public static String msg_error_empty_username = "//div[@id='normal_login_username_help']//div[@class='ant-form-item-explain-error'][normalize-space()='This field is required!']";
    public static String msg_error_empty_password = "//div[@id='normal_login_password_help']//div[@class='ant-form-item-explain-error'][normalize-space()='This field is required!']";
    public static String alertMessage_loginfail = "//span[normalize-space()='Username or password is incorrect']";
    public static String header_title_content = "//span[@class='header-title--content']"; //title khi login success

    //Lead Management
    public static String btn_create_lead = "//button[@class='ant-btn css-6j9yrn ant-btn-default toolbar-create']";
    public static String title_create_lead = "//div[@class='title']";
    public static String btn_popup_create_lead = "//button[@class='ant-btn css-6j9yrn ant-btn-primary sc-jrAGKZ jVoqoJ']"; //popup create lead
    public static String btn_popup_cancel = "//button[@class='ant-btn css-6j9yrn ant-btn-default sc-jrAGKZ jVoqoJ']"; //cancel create lead
    public static String input_lead_name = "//input[@id='fullname']";
    public static String input_client_name = "//input[@id='newClientName']";
    public static String input_client_code = "//input[@id='newClientCode']";
    public static String input_website = "//input[@id='newClientWeb']";
    public static String input_contact_name = "//input[@id='newContactName']";
    public static String input_email = "//input[@id='newContactMail']";
    public static String select_status_prospect = "//div[contains(text(),\"Prospect\")]";
    public static String select_status_contacted = "//div[contains(text(),\"Contacted\")]";
    public static String select_status_estimate = "//div[contains(text(),\"Estimate\")]";
    public static String select_status_follow = "//div[contains(text(),\"Follow up\")]";
    public static String select_status_received = "//div[contains(text(),\"Project received\")]";
    public static String get_status_prospect = "//span[@title='Prospect']";
    public static String get_status_contacted = "//span[@title='Contacted']";
    public static String get_status_estimate = "//span[@title='Estimate']";
    public static String get_status_follow = "//span[@title='Follow up']";
    public static String get_status_received = "//span[@title='Project received']";
    public static String message_option_received = "//span[contains(text(),'If you select this status, the project will be mov')]";
    public static String click_here_create_new_client = "(//a[contains(text(),\"click here\")])[1]";
    public static String click_here_create_new_contact = "(//a[contains(text(),\"click here\")])[2]";
    public static String dropdown_client = "//input[@id='oldClient']";
    public static String msg_error_empty_leadname = "//div[@class='ant-form-item-explain-error']";
    public static String msg_error_empty_clientname = "//div[@id='newClientName_help']//div[@class='ant-form-item-explain-error'][normalize-space()='This field is required!']";
    public static String alert_create_success = "//div[@role='alert']";


}
