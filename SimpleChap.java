package robot;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;

public class SimpleChap {

	public static void main(String[] args) {
		//float array to store sample from fetchSample
		float[] sample = new float[1];
		
		//define motors and sensor
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
		
		NXTUltrasonicSensor sensor = new NXTUltrasonicSensor(SensorPort.S1);
		sensor.enable(); //enable sensor yippee
		
		//get a sample provider and store the first sample
		SampleProvider distanceProvider = sensor.getDistanceMode();
		distanceProvider.fetchSample(sample, 0);
		
		//continue to get a new distance sample until less than 50cm distance
		while (sample[0] >= 50) //supposedly measures in centimetres
			distanceProvider.fetchSample(sample, 0);
		
		//stop the motors and sensor
		mLeft.stop();
		mRight.stop();
		sensor.disable();
		
		//close all
		mLeft.close();
		mRight.close();
		sensor.close();
	}

}
