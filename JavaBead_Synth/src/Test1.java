import java.io.File;
import java.util.Scanner;

import beads.AudioContext;
import beads.AudioFileType;
import beads.AudioFileWriter;
import beads.BiquadFilter;
import beads.Buffer;
import beads.Envelope;
import beads.Function;
import beads.Gain;
import beads.Glide;
import beads.Panner;
import beads.Phasor;
import beads.RecordToSample;
import beads.Reverb;
import beads.Sample;
import beads.SampleAudioFormat;
import beads.WavePlayer;
import beads.WaveShaper;


public class Test1 {

	public static void main(String[] args) {
	Test1 lt= new Test1();
	lt.play_file();
	
	}
    
	AudioContext ac= new AudioContext(512);
	AudioContext ac2= new AudioContext(512);
	
	private void play_file(){
		
	
		
// TODO Auto-generated method stub
	
		int ckey2=60;
		
		WavePlayer modulator_freq = new WavePlayer(ac,pitchToFrequency(ckey2), Buffer.SINE);
		
		Function frequencyModulation = new Function(modulator_freq){
			@Override
			public float calculate()
			{
				// x[0] = modulator
				// 20.0f = modulation width
				// 440.0f = modulation center (carrier frequency)
				return (float) ((x[0] *30f+Math.exp(((Math.cos((double)(x[0]/24)))))*2f) +(436.0f+pitchToFrequency(ckey2)));
			}
		};
		
		// create a sine generator
//		WavePlayer carrier = new WavePlayer(ac, frequencyModulation, Buffer.SINE);

		// create an AudioContext

		
		// create a sine generator
		WavePlayer carrier = new WavePlayer(ac,frequencyModulation, Buffer.SINE);
		NewBuffer ltr1= new NewBuffer(9);
		WaveShaper waveshape= new WaveShaper(ac,ltr1.mr1);
		
		NewBuffer ltr2= new NewBuffer(8);
		WaveShaper waveshape2= new WaveShaper(ac,ltr1.mr1);
		
		waveshape.addInput(carrier);
		// create a WavePlayer to control the gain
		WavePlayer modulator = new WavePlayer(ac,1f, Buffer.SINE);
		WavePlayer modulator2 = new WavePlayer(ac,1f, Buffer.SINE);
		WavePlayer sinewave = new WavePlayer(ac,1f, Buffer.SINE);
		waveshape2.addInput(sinewave);;
		WavePlayer panwave = new WavePlayer(ac,1f, Buffer.SINE);
		//WavePlayer panwave2 = new WavePlayer(ac,f, Buffer.SINE);
		// create a Gain to control the sine volume
		Gain sineGain = new Gain(ac, 1, modulator);
		Gain sineGain2= new Gain(ac,1, modulator2);
		Gain lfo = new Gain(ac,1, waveshape2);
		Envelope pang_env= new Envelope(ac,0);
		Gain pang= new Gain(ac,1,panwave);
		//pang.addInput(panwave);
		// add the sine generator as an input to the Gain
		sineGain.addInput(waveshape);
		sineGain2.addInput(waveshape2);
		lfo.addInput(sineGain2);
	     Reverb reverb1= new Reverb(ac);
	        reverb1.setSize((float).82f);
	        reverb1.setDamping((float)0.63);
	        
	        //reverb1.setLateReverbLevel(1.5f);
		// add the Gain as an input to the master output, ac.out
		reverb1.addInput(sineGain);
		reverb1.addInput(sineGain2);
		BiquadFilter reverb_filter = new BiquadFilter(ac, BiquadFilter.LP,pitchToFrequency(ckey2), 300f);
		Panner pan_lfo1= new Panner(ac,panwave);
	    reverb_filter.addInput(reverb1); 
		pan_lfo1.addInput(reverb_filter);
	   
		Gain upvolume= new Gain(ac, 2, 5f);
		upvolume.addInput(pan_lfo1);
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		ac.out.addInput(upvolume);
		//ac.out.addInput(upvolume_A);
		// begin audio processing
		ac.start();
		//ac.start();
	
		try{SampleAudioFormat af = new SampleAudioFormat( 44100.0f, 16, 1, true, true); 

		AudioFileWriter write_audio_rght = null ;
		String filename;
		filename="C:\\Users\\deepj\\workspace\\HashMap_utilityAudio\\src\\lat.wav";
		File filename1 = new File(filename);
		float[][] aud1= new float[2][];
		aud1[0]=ac.out.getOutBuffer(0);
		aud1[1]=ac.out.getOutBuffer(1);
		Sample outputSample = new Sample(2000, 2, 44100);



		RecordToSample rts = new RecordToSample(ac, outputSample, RecordToSample.Mode.INFINITE); rts.addInput(ac.out); 
		ac.out.addDependent( rts); 


		Scanner in = new Scanner( System.in); System.out.println(" Press enter to save sound and end the program.");
		in.nextLine();

		rts.pause( true); rts.clip(); outputSample.write("scripture_wave_high5_pan2.wav", AudioFileType.WAV); 
		rts.kill();
		}catch (Exception e) { e.printStackTrace(); System.exit( 1);
		 System.exit(0);
		 
	
		}
		
	}
	
	
	private float pitchToFrequency(float wav1_pitch) {
		/*
		 *  MIDI pitch number to frequency conversion equation from
		 *  http://newt.phys.unsw.edu.au/jw/notes.html
		 */
        double exponent = (wav1_pitch - 69.0) / 12.0;
        return (float) (Math.pow(2, exponent) * 436.0f);
    }
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	

