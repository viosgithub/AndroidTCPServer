package foo.bar.UDPServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.util.Log;

public class SendThread extends Thread{ 
	Socket socket = null;
	
	SensorThread sensorThread;

	public SendThread(SensorThread st) {
		// TODO Auto-generated constructor stub
		sensorThread = st;
	}
	
	
	
	
	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
	
	public void run()
	{
		if(socket == null)
		{
			return;
		}
		
		Log.d("debug","connected");
	
		try{
		OutputStreamWriter	osw = new OutputStreamWriter(socket.getOutputStream());
			BufferedWriter bw = new BufferedWriter(osw);
		while(true)
		{
			//z x y
			bw.write("z:" + String.valueOf(sensorThread.a)+"#");
			bw.write("x:" + String.valueOf(sensorThread.b)+"#");
			bw.write("y:" + String.valueOf(sensorThread.c)+"#");
			bw.flush();
			if (!socket.isConnected())
				{
					Log.d("debug","broken");
					break;
				}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e2)
		{
			e2.printStackTrace();
		}
		}
	}


