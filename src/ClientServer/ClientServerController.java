package ClientServer;

import application.MainController;

public final class ClientServerController {
	private static ClientConsole client;
	private MainController controller;
	
	@SuppressWarnings("static-access")
	public ClientServerController(ClientConsole client)
	{
		this.client = client;
		controller = new MainController();
	}
	

	public static Object sendMsgToServer(String userAndPass) {
		return client.sendMsgToServer(userAndPass);
	}


	public void showMainMenu(String[] args) {
		controller.showMainMenu(args);
		
	}

}
