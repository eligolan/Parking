package ClientServer;

import java.io.Serializable;

public class ObjectSender implements Serializable {
	private int numRequest;
	private Object msg;
	
	public ObjectSender(int numRequest, Object msg) {
		super();
		this.numRequest = numRequest;
		this.msg = msg;
	}
	public int getNumRequest() {
		return numRequest;
	}
	public Object getMsg() {
		return msg;
	}
}
