package foo.bar.UDPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;

public class UDPServer extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SensorThread sensorThread = new SensorThread((SensorManager)getSystemService(SENSOR_SERVICE));
        sensorThread.start();
        
        try {
			ServerSocket serverSocket = new ServerSocket(47777);
				Socket socket = serverSocket.accept();
				SendThread sendThread = new SendThread(sensorThread);
				sendThread.setSocket(socket);
				sendThread.start();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}