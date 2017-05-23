package account_utilities;

import java.sql.*;

import beans.LoginBean;
import collection_cataloger.TransactionObject;
import collection_cataloger.TransactionResponse;
import dao.UserAccountDAO;
import database_utilities.ConnectionProvider;

public class UserAccountDAOImpl implements UserAccountDAO{

	public TransactionResponse validateAccount(TransactionObject transaction, TransactionResponse transactionResponse) {
		boolean status = false;
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(transaction.getUsername());
		loginBean.setPassword(transaction.getPassword()); 
		try {
			Connection con = ConnectionProvider.getCon();

			PreparedStatement ps = con.prepareStatement("select * from user where username=? and password=?");

			ps.setString(1, loginBean.getUsername());
			ps.setString(2, loginBean.getPassword());

			ResultSet rs = ps.executeQuery();
			status = rs.next();
			
			if(status){
				transactionResponse.setStatus("SUCCESS");
			}else{
				transactionResponse.setStatus("ERROR");
				transactionResponse.setMessage("Username or password incorrect, please try again.");
			}

		} catch (Exception e) {
			transactionResponse.setStatus("ERROR");
			transactionResponse.setMessage("An error has occured, please contact the administrator.");
		}

		return transactionResponse;

	}
}