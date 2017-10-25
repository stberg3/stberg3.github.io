import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class MyHandler implements HttpHandler {
	private void sendFile(String path, OutputStream os) {
		System.out.println(String.format("Parsing path %s", path));
	
		try {
			System.out.println("Reading the file " + path);
			FileInputStream in = new FileInputStream(path);
	         
			byte c;
			while ((c = (byte) in.read()) != -1) {
//				System.out.print(c);
				os.write(c);
			}
			
			in.close();
		} catch (IOException e) {
			System.out.println(String.format("Error reading file %s", path));
		}

	}
	
	private long getFileSize(String path) {
		File f = new File(path);
		return f.length();
	}
	
    @Override
    public void handle(HttpExchange t) throws IOException {
    	String path = t.getHttpContext().getPath();
		if(path.equals("/")) {
			path = "/home/sam/Desktop/eclipse-workspace/WebServer/static/index.html";
		} else {
			path = "/home/sam/Desktop/eclipse-workspace/WebServer/static" + path;
		}
//        String response = "This is the response";
        t.sendResponseHeaders(200, getFileSize(path));
        System.out.println("HEADERS:");
        System.out.println(t.getRequestMethod());
        System.out.println("BODY:");
        System.out.println(t.getRequestBody());
        
        
        OutputStream os = t.getResponseBody();
        sendFile(path, os);
        os.close();
    }
}

