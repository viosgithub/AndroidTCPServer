package foo.bar.UDPServer;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class SensorThread extends Thread implements SensorEventListener{
	protected final static double RAD2DEG = 180/Math.PI;
	
	float[] rotationMatrix = new float[9];
	float[] gravity = new float[3];
	float[] geomagnetic = new float[3];
	float[] attitude = new float[3];
	
	float a,b,c;
	
	SensorManager sensorManger;
	private boolean registeredSensor;

	public SensorThread(SensorManager sm) {
		// TODO Auto-generated constructor stub
		sensorManger = sm;
		initSensor();
	}
	public void initSensor()
	{
		List<Sensor> sensors = sensorManger.getSensorList(Sensor.TYPE_ORIENTATION);
		if(sensors.size() > 0)
		{
			Sensor sensor = sensors.get(0);
			registeredSensor = sensorManger.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
		}
	}
	public void run()
	{
		initSensor();
		while(true)
		{
			try {
				this.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		a = event.values[0];
		b = event.values[1];
		c = event.values[2];
		
	}

}
