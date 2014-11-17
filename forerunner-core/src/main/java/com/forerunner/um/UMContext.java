package com.forerunner.um;

public class UMContext {

	private static final ThreadLocal<UMContext> umContext = new ThreadLocal<UMContext>();

	private String currentUser;

	//*********** context method ***********//

	public static UMContext getCurrentContext() {
		UMContext context = umContext.get();

		if (context == null) {
			context = new UMContext();
			setCurrentContext(context);
		}

		return context;
	}

	public static void setCurrentContext(UMContext context) {
		umContext.set(context);
	}

	public static void resetCurrentContext() {
		umContext.remove();
	}

	//*********** getter setter ***********//

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
}
