package com.ashwinupadhyaya.bikepower;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Parcel;
import android.os.Parcelable;

public class BikeSensors  implements Parcelable {
	private int nsen = 0, mSen = 0, j = 0;
	private int[] types=null;
	private int[] rates=null;
	private String[] names=null;
	
	public BikeSensors (List<Sensor> aSList) {
		nsen=aSList.size();
		
		if (nsen>0) {
			for (int i=0;i<nsen;i++) {
				switch (aSList.get(i).getType()) {
				case Sensor.TYPE_ACCELEROMETER:
					mSen++;
					break;
				default:
					break;
				}
			}
			names = new String[mSen];
			types = new int[mSen];
			rates=new int[mSen];
			j = 0;
			
			//Set name
			for (int i=0;i<nsen;i++) {
				switch (aSList.get(i).getType()) {
				case (Sensor.TYPE_ACCELEROMETER):
					names[j]="ACCELEROMETER";
					types[j]=aSList.get(i).getType();
					rates[j]=SensorManager.SENSOR_DELAY_GAME;
					j++;
					break;
				default:
					break;
				}
			}
		}
	}
	
	String[] getNames() {
		return names; 
	}
	
	String getName(int i) {
		return names[i];
	}
	
	String getNameByType(int typ) {
		for (int i=0;i<nsen;i++) {
			if (types[i]==typ)
				return names[i];
		}
		
		return "Undefined Type";
	}

	public int getNum() {
		return j;
	}
	
	public int getType(int i) {
		return types[i];
	}
	
	int getRate(int i) {
		return rates[i];
	}
	
	//////////////////////////////////////////////////////////////////

	public void writeToParcel(Parcel out, int flag) {
		out.writeInt(nsen);
		out.writeStringArray((String[]) names);
		out.writeIntArray(types);
		out.writeIntArray(rates);
	}
	
	public static final Parcelable.Creator<BikeSensors> CREATOR
	= new Parcelable.Creator<BikeSensors>() {
		public BikeSensors createFromParcel(Parcel source) {
			return new BikeSensors(source);
			}

		public BikeSensors[] newArray(int size) {
			return new BikeSensors[size];
		}
	};
	
	private BikeSensors(Parcel source) {
		nsen=source.readInt();
		
		if (nsen>0) {
			names = new String[nsen];
			types = new int[nsen];
			rates = new int[nsen];
			source.readStringArray((String[]) names);
			source.readIntArray(types);
			source.readIntArray(rates);
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}
}
