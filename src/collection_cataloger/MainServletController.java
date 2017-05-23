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

import beans.LoginBean;

@WebServlet("/MainServletController/*")
public class MainServletController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	    // This will store all received articles
	 	
	    List<TransactionObject> transactionObject = new LinkedList<TransactionObject>();
	    TransactionResponse transactionResponse = new TransactionResponse();
	    /***************************************************
	     * URL: /jsonservlet
	     * doPost(): receives JSON data, parse it, map it and send back as JSON
	     ****************************************************/
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException{
	 
	        // 1. get received JSON data from request
	        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
	        String json = "";
	        if(br != null){
	            json = br.readLine();
	            System.out.println(json);
	        }
	 
	        // 2. initiate jackson mapper
	        ObjectMapper mapper = new ObjectMapper();
	 
	        // 3. Convert received JSON to TransactionObject
	        TransactionObject transaction = mapper.readValue(json, TransactionObject.class);
	 
	        // 4. Set response type to JSON
	        response.setContentType("application/json");    
	        
	        //validate transaction type then send to dispatcher
	        //TODO Refactor response and remove hardcoded values
	        if(validateTransaction(transaction.getTransaction().toString())){
	        	transactionResponse.setStatus("SUCCESS");
	        	//valid so dispatch request
	        	TransactionDispatcher dispatchTransaction = new ConcreteTransactionDispatcher();
	        	transactionResponse = dispatchTransaction.handleTransaction(transaction, transactionResponse);
	        }else{
	        	transactionResponse.setStatus("ERROR");
	        	transactionResponse.setMessage("Transaction request failed on type.");
	        }
	       
	        System.out.println(transaction.getTransaction().toString());
	        System.out.println(transaction.getUsername().toString());
	        System.out.println(transaction.getPassword().toString());

	        // 6. Send List<Article> as JSON to client
	        mapper.writeValue(response.getOutputStream(), transactionResponse);
	    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	private static boolean validateTransaction(String str) {
	    for (TransactionType transcationTypes : TransactionType.values()) {
	        if (transcationTypes.name().equalsIgnoreCase(str))
	            return true;
	    }
	    return false;
	}
}