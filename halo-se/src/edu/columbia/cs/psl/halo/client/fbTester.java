package edu.columbia.cs.psl.halo.client;

import edu.columbia.cs.psl.halo.HALOServiceFactory;

public class fbTester {
	public static void main(String[] args) {
		HALOServiceFactory.getInstance().login("jon", "test123");
//		HALOServiceFactory.getInstance().getUserSvc().postQuestCopmletionToFacebook(null);
	}
}
