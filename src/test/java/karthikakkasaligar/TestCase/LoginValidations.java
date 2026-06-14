package karthikakkasaligar.TestCase;

import org.testng.annotations.Test;

import karthikakkasaligar.TestComponents.BaseTest;

public class LoginValidations extends BaseTest {

	@Test
	public void Validlogin() {
		login.Validlogin(login.getusername(), login.getpassword());
	}
	
	@Test
	public void Invalidusernamelogin() {
		login.InvalidUsernamelogin("karthik", login.getpassword());
	}
	
	@Test
	public void Invalidpasswordlogin() {
		login.Invalidpasswordlogin(login.getusername(), "Karthik");
	}

}
