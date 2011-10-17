package foo.bar.UDPServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String rLine = "";
		while(true)
		{
			while(is.available() > 0)
			{
				rLine = br.readLine();
				Log.d("debug","br:"+rLine);
				if(rLine.equals("close"))
				{
				Log.d("debug","getClose");
				br.close();
				is.close();
				bw.close();
				osw.close();
				socket.close();
					
				}
			}
			//z x y
			bw.write("z:" + String.valueOf(sensorThread.a)+" ");
			bw.write("x:" + String.valueOf(sensorThread.b)+" ");
			bw.write("y:" + String.valueOf(sensorThread.c)+"\n");
			bw.flush();
			Thread.sleep(50);
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


