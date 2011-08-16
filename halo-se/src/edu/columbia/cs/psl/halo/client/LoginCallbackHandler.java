package edu.columbia.cs.psl.halo.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;


public class LoginCallbackHandler implements CallbackHandler {

	public LoginCallbackHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		// TODO Auto-generated method stub
		System.out.println("Asked to handle");
		
	}

}
