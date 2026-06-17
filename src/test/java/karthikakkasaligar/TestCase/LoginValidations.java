package karthikakkasaligar.TestCase;

import org.testng.annotations.Test;

import karthikakkasaligar.PageObjectModel.DashboardPage;
import karthikakkasaligar.TestComponents.BaseTest;

public class LoginValidations extends BaseTest {

	@Test
	public void ValidateValidlogin() {
		login.Validlogin(login.getusername(), login.getpassword());
	}

	@Test
	public void ValidateInvalidusernamelogin() {
		login.InvalidUsernamelogin("karthik", login.getpassword());
	}

	@Test
	public void ValidateInvalidpasswordlogin() {
		login.Invalidpasswordlogin(login.getusername(), "Karthik");
	}

	@Test
	public void ValidateEmptyUsernamelogin() {
		login.Emptyusernamelogin(login.getpassword());
	}

	@Test
	public void ValidateEmptypasswordlogin() {
		login.Emptyusernamelogin(login.getpassword());
	}

	@Test
	public void ValidateEmptyusernameandpasswordlogin() {
		login.Emptyusernameandpasswordlogin();
	}

	@Test
	public void Validatepasswordmasking() {
		login.passwordmasking(login.getpassword());
	}

	@Test
	public void Logout() {
		DashboardPage dashboard = login.Validlogin(login.getusername(), login.getpassword());
		dashboard.Logout();
	}

}
