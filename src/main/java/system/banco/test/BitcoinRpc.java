//package system.banco.test;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
//
//public class BitcoinRpc {
//
//	private String user = "ajit";
//	private String password = "oodles";
//	private String host = "127.0.0.1";
//	private String port = "18332";
//
//	public void run() {
//		try {
//			URL url = new URL("http://" + user + ':' + password + "@" + host + ":" + port + "/");
//
//			BitcoinJSONRPCClient bitcoinClient = new BitcoinJSONRPCClient(url);
//			System.out.println(bitcoinClient.getWalletInfo());
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}
