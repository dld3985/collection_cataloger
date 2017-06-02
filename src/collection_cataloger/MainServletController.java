package collection_cataloger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import account_utilities.LoginBean;

/**
 * Main servlet that handles all incoming transactions. Validates that the
 * incoming transaction exists in the system then passes the request to the
 * dispatcher to await a response. Response is set in the TransactionResponse
 * class with a standard SUCCESS or ERROR format and an accompanying error
 * message to be sent back to the user.
 * 
 * NOTE: Since this is for a course project, for ease of development I will ignore session management 
 * and just pass the username and password with every request. This is not ideal nor secure and will likely
 * be tacked on at some later date.
 * 
 * @author Dan Dumouchel
 *
 */
@WebServlet("/MainServletController/*")
public class MainServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// This will store all received articles

	List<RequestObject> requestObject = new LinkedList<RequestObject>();
	TransactionResponse transactionResponse = new TransactionResponse();

	/***************************************************
	 * URL: /MainServletController doPost(): receives JSON data, parse it, map it and send
	 * back as JSON
	 ****************************************************/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. get received JSON data from request
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}

		// 2. initiate jackson mapper
		ObjectMapper mapper = new ObjectMapper();

		// 3. Convert received JSON to RequestObject
		RequestObject requestIn = mapper.readValue(json, RequestObject.class);

		// 4. Set response type to JSON
		response.setContentType("application/json");

		// 5. validate transaction type then send to dispatcher

		if (validateTransaction(requestIn.getRequest().toString())) {
			transactionResponse.setStatus(ResponseType.SUCCESS);
			// valid so dispatch request
			TransactionDispatcher dispatchTransaction = new ConcreteTransactionDispatcher();
			transactionResponse = dispatchTransaction.getTransaction(requestIn, transactionResponse);
		} else {
			transactionResponse.setStatus(ResponseType.ERROR);
			transactionResponse.setMessage("Transaction request failed on type.");
		}

		// 6. Send response to client
		mapper.writeValue(response.getOutputStream(), transactionResponse);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/*
	 * Validate the incoming transaction exists in the TransactionType Enum
	 */
	private static boolean validateTransaction(String str) {
		for (TransactionType transcationTypes : TransactionType.values()) {
			if (transcationTypes.name().equalsIgnoreCase(str))
				return true;
		}
		return false;
	}
}