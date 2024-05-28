package apccloner;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;

import org.jfugue.ChannelPressure;
import org.jfugue.Controller;
import org.jfugue.DeviceThatWillReceiveMidi;
import org.jfugue.DeviceThatWillTransmitMidi;
import org.jfugue.Instrument;
import org.jfugue.KeySignature;
import org.jfugue.Layer;
import org.jfugue.Measure;
import org.jfugue.Note;
import org.jfugue.ParserListener;
import org.jfugue.PitchBend;
import org.jfugue.PolyphonicPressure;
import org.jfugue.SystemExclusiveEvent;
import org.jfugue.Tempo;
import org.jfugue.Time;
import org.jfugue.Voice;


public class main {
	public static void main(String[] args) {
		int[] channel = new int[1];
		boolean[] ledStates = new boolean[120];
		int[] pallette = new int[9];
		pallette[0] = 5;
		pallette[1] = 8;
		pallette[2] = 87;
		pallette[3] = 49;
		pallette[4] = 124;
		pallette[5] = 9;
		pallette[6] = 42;
		pallette[7] = 77;
		pallette[8] = 53;
		int[] colors = new int[120];
		colors[32] = pallette[0];
		colors[24] = pallette[0];
		colors[16] = pallette[0];
		colors[8] = pallette[0];
		
		colors[33] = pallette[1];
		colors[25] = pallette[1];
		colors[17] = pallette[1];
		colors[9] = pallette[1];
		
		colors[34] = pallette[2];
		colors[26] = pallette[2];
		colors[18] = pallette[2];
		colors[10] = pallette[2];
		
		colors[35] = pallette[3];
		colors[27] = pallette[3];
		colors[19] = pallette[3];
		colors[11] = pallette[3];
		
		colors[36] = pallette[4];
		colors[28] = pallette[4];
		colors[20] = pallette[4];
		colors[12] = pallette[4];
		
		colors[37] = pallette[5];
		colors[29] = pallette[5];
		colors[21] = pallette[5];
		colors[13] = pallette[5];
		
		colors[38] = pallette[6];
		colors[30] = pallette[6];
		colors[22] = pallette[6];
		colors[14] = pallette[6];
		
		colors[39] = pallette[7];
		colors[31] = pallette[7];
		colors[23] = pallette[7];
		colors[15] = pallette[7];
		
		colors[82] = pallette[8];
		colors[83] = pallette[8];
		colors[84] = pallette[8];
		colors[85] = pallette[8];
		
		Image image = Toolkit.getDefaultToolkit().getImage("icon.PNG");
		PopupMenu popup = new PopupMenu(); 
    	MenuItem item = new MenuItem( "Exit" ); 
    	item.addActionListener( new ActionListener() { 
    		@Override public void actionPerformed( ActionEvent e ) { 
    			
    			System.exit( 0 ); 
    		} 
    	} ); 
    	popup.add( item ); 
 
    	TrayIcon trayIcon = new TrayIcon( image, "APC Cloner", popup ); 
    	trayIcon.setImageAutoSize( true );
    	SystemTray tray = SystemTray.getSystemTray(); 
    	
    	try {
			tray.add( trayIcon );
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
    	
    	try {
    		MidiDevice.Info[] mdi = MidiSystem.getMidiDeviceInfo();
    		int apcReceiveNo = 0;
    		int apcTransmitNo = 0;
    		int vMidi1No = 0;
    		for(int i = 0; i < mdi.length; i++) { 
    			if(mdi[i].toString().contains("VMidi 1")) { 
    				vMidi1No = i;
    				break;
    			}
    		}
    		for(int i = 0; i < mdi.length; i++) { 
    			if(mdi[i].toString().contains("APC")) { 
    				apcReceiveNo = i;
    				break;
    			}
    		}
    		for(int i = 0; i < mdi.length; i++) { 
    			if(mdi[i].toString().contains("APC")) { 
    				apcTransmitNo = i;
    			}
    		}
    		
    		DeviceThatWillReceiveMidi vMidi1 = new DeviceThatWillReceiveMidi(mdi[vMidi1No]);
    		//DeviceThatWillReceiveMidi vMidi2 = new DeviceThatWillReceiveMidi(mdi[vMidi1No+1]);
    		//DeviceThatWillReceiveMidi vMidi3 = new DeviceThatWillReceiveMidi(mdi[vMidi1No+2]);
    		//DeviceThatWillReceiveMidi vMidi4 = new DeviceThatWillReceiveMidi(mdi[vMidi1No+3]);
			ShortMessage shortMessage = new ShortMessage();
			ShortMessage shortMessageAPC = new ShortMessage();
    		DeviceThatWillReceiveMidi apcReceive = new DeviceThatWillReceiveMidi(mdi[apcReceiveNo]);
    		
    		DeviceThatWillTransmitMidi apcTransmit = new DeviceThatWillTransmitMidi(mdi[apcTransmitNo]);
		
			apcTransmit.startListening();
			apcTransmit.addParserListener(new ParserListener() {
				@Override
				public void voiceEvent(Voice arg0) {}
				@Override
				public void timeEvent(Time arg0) {}
				@Override
				public void tempoEvent(Tempo arg0) {}
				@Override
				public void systemExclusiveEvent(SystemExclusiveEvent arg0) {}
				@Override
				public void sequentialNoteEvent(Note arg0) {}
				@Override
				public void polyphonicPressureEvent(PolyphonicPressure arg0) {}
				@Override
				public void pitchBendEvent(PitchBend arg0) {}
				@Override
				public void parallelNoteEvent(Note arg0) {}
				@Override
				public void noteEvent(Note arg0) {
					try {
						if ((int)arg0.getDecayVelocity() == 64) {
							
							if(arg0.getValue()==101) {
								//channel[0] = 
							}
							//System.out.println(arg0.getVerifyString());
						    System.out.println(arg0.getValue());
							shortMessage.setMessage(ShortMessage.CONTROL_CHANGE, 1, (int)arg0.getValue(), 127);
							if(!ledStates[(int)arg0.getValue()]) {
								shortMessageAPC.setMessage(ShortMessage.NOTE_ON, 1, (int)arg0.getValue(), colors[(int)arg0.getValue()]);
								ledStates[(int)arg0.getValue()] = true;
							} else {
								//shortMessage.setMessage(ShortMessage.CONTROL_CHANGE, 1, (int)arg0.getValue(), 127);
								shortMessageAPC.setMessage(ShortMessage.NOTE_OFF, 1, (int)arg0.getValue(), colors[(int)arg0.getValue()]);
								ledStates[(int)arg0.getValue()] = false;
							}
							vMidi1.getReceiver().send(shortMessage, -1);
				    		//vMidi2.getReceiver().send(shortMessage, -1);
				    		//vMidi3.getReceiver().send(shortMessage, -1);
				    		//vMidi4.getReceiver().send(shortMessage, -1);
						}
						apcReceive.getReceiver().send(shortMessageAPC, -11);
					} catch (InvalidMidiDataException e) {
						e.printStackTrace();
					}
				}
				@Override
				public void measureEvent(Measure arg0) {}
				@Override
				public void layerEvent(Layer arg0) {}
				@Override
				public void keySignatureEvent(KeySignature arg0) {}
				@Override
				public void instrumentEvent(Instrument arg0) {}
				@Override
				public void controllerEvent(Controller arg0) {
					try {
						//ShortMessage shortMessage = new ShortMessage();
						int index = (int)arg0.getIndex();
						if((int)arg0.getIndex() == 13) {
							index = 113;
						} else if((int)arg0.getIndex() == 7) {
							index = 107;
						} else if((int)arg0.getIndex() == 14) {
							index = 114;
						}
						//shortMessage.setMessage(ShortMessage.CONTROL_CHANGE, 1, index, (int)arg0.getValue());
						//System.out.println(index);
						//System.out.println((int)arg0.getValue());
						vMidi1.getReceiver().send(new ShortMessage(ShortMessage.CONTROL_CHANGE, 1, index, (int)arg0.getValue()), -1);
			    		//vMidi2.getReceiver().send(shortMessage, -1);
			    		//vMidi3.getReceiver().send(shortMessage, -1);
			    		//vMidi4.getReceiver().send(shortMessage, -1);
			    		//shortMessage.setMessage(0);
					} catch (InvalidMidiDataException e) {
						e.printStackTrace();
					}
				}
				@Override
				public void channelPressureEvent(ChannelPressure arg0) {}
			});
		} catch (MidiUnavailableException e1) {
			e1.printStackTrace();
		} 
	}

}
