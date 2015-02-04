import motej.Mote;
import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;
import java.util.ArrayList;
import java.util.List;
import motej.Mote;
import motej.MoteFinder;
import motej.MoteFinderListener;
import org.slf4j.*;
import org.slf4j.spi.*;

public class Attempt {

private static List<Mote> motes = new ArrayList<Mote>();
	
	public static void main(String[] args) throws InterruptedException {
		MoteFinderListener listener = new MoteFinderListener() {
		
			public void moteFound(Mote mote) {
				System.out.println("Found mote: " + mote.getBluetoothAddress());
				mote.rumble(2000l);
				motes.add(mote);
			}
		
		};
		
		MoteFinder finder = MoteFinder.getMoteFinder();
		finder.addMoteFinderListener(listener);
		
		System.out.println("Starting discovery..");
		finder.startDiscovery();
		
		System.out.println("Putting thread to sleep..");
		Thread.sleep(1000000l);
		
		System.out.println("Stopping discovery..");
		finder.stopDiscovery();
		
		for (Mote m : motes) {
			m.disconnect();
		}
	}
}
