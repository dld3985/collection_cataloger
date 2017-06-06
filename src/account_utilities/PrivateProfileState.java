package account_utilities;

public class PrivateProfileState implements ProfileState {

	@Override
	public void doAction(User user) {
		System.out.println("User profile has been set to private");
		user.setProfileState(this);

	}

	@Override
	public String toString() {
		return "Private Profile";
	}

}
