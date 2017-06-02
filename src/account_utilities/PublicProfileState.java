package account_utilities;

public class PublicProfileState implements ProfileState{

	@Override
	public void doAction(User user) {
		System.out.println("User profile has been set to public");
		user.setProfileState(this);	
		
	}

}
