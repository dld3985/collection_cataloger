package account_utilities;

/**
 * State interface for handling public and private user profiles. Can be extended to have different types.
 * 
 * @author Dan Dumouchel
 *
 */
public interface ProfileState {
	public void doAction(User user);
}
