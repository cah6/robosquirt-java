package model.externalcomm;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

/**
 * External model, handles communication with other programs, relaying this information to
 * the appropriate models.
 * @author Christian
 *
 */
public class ExternalCommModel {

	/**
	 * Adapter from this model to the task model.
	 */
	TaskAdapter taskModel;
	
	ServerSocket welcomeSocket;
	
	BufferedReader inputStream;
	
	OutputStream outputStream;
	
	String clientSentence;
	
	/**
	 * Constructor for the external communications model, sets up necessary adapters to
	 * talk to other models.
	 * @param taskAdapter
	 */
	public ExternalCommModel(TaskAdapter taskAdapter){
		taskModel = taskAdapter;
		try {
			welcomeSocket = new ServerSocket(6789);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start(){
		startServer();
	}
	
	public void startServer() {
        final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(8000);
                    System.out.println("Waiting for clients to connect...");
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        clientProcessingPool.submit(new ClientTask(clientSocket, taskModel));
                    }
                } catch (IOException e) {
                    System.err.println("Unable to process client request");
                    e.printStackTrace();
                }
            }
        };
        (new Thread(serverTask)).start();

    }
	
	public void sendMultiTask(String multiTask){
		System.out.println("sending the multi task");
	}
	
	public boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
}
