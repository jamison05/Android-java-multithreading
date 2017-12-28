package drjpreludes.preluded_1;

/**
 * Created by Joshua on 9/1/2016.
 */

import android.animation.ValueAnimator;
import android.media.SoundPool;
import android.os.SystemClock;
import android.view.animation.LinearInterpolator;

import net.beadsproject.beads.core.AudioContext;

import java.util.LinkedList;
import java.util.Queue;

public class Master_rhythm_melody_arithmetic implements Runnable{
    public int[] drum1= new int[15];
    public int[] melody1= new int[15];
    int current = 0;
    //Thread runner;
    private Object mPauseLock;
    public boolean mPaused;
    private boolean mFinished;
    public int change_queue_value;
boolean audio_on= true;
    //This thread will run the soundPool,
    //What I will do is just have thread function off of the system clock. Pausing every 100 ms, for 10 ms  or however it will do
    //to get the  system operational without animation confliclts all three activities will play on this thread.

    //Only thing that will be sent to this thread or class is speed and rhythm changes.
double current_time, start_time, end_time;
    double current_value;

    public void set_audio_on(boolean audio_on1){

     this.audio_on= audio_on1;
  }

    boolean change_audio_signal;
  public void set_change_audio_signal(boolean change){

      this.change_audio_signal=change;
  }
  public void set_total_dur(int duration){
      this.total_dur= duration;


  }
  public void setChange_queue_value(int change_queue_limit){


      this.change_queue_value=change_queue_limit;
  }
    public void run() {
        //initialize all audio
        //initialize all receivers
        //runner = new Thread(this);
        master_time_regulator();

        while (audio_on) {



            determine_sound_change(this.change_queue_value);
            func_feed_notes_1();
            while(!rhythmtime_queue.isEmpty()) {
                start_time = SystemClock.elapsedRealtime();
                end_time = this.total_dur;
                current_time = 0;
                current_value = 0;

                while (current_value < 767) {
                    current_time = SystemClock.elapsedRealtime() - start_time;
                    current_value = ((SystemClock.elapsedRealtime() - start_time) / end_time) * 767;
                    rhythm_1();
                    get_melody_note();





                }
                synchronized (mPauseLock) {
                    while (mPaused) {
                        try {
                            mPauseLock.wait();
                        } catch (InterruptedException e) {
                        }
                    }

                }

            }
        }


    }


    public void onPause() {
        synchronized (mPauseLock) {
            mPaused = true;
        }
    }

    //This will be called by the activity class to resume the synth.

    public void onResume() {
        synchronized (mPauseLock) {
            mPaused = false;
            mPauseLock.notifyAll();
        }
    }






    public Master_rhythm_melody_arithmetic(SoundPool soundPool_t, SoundPool soundPool_t2, SoundPool soundPool_t3) {

        this.soundPool = soundPool_t;
        this.soundPool2 = soundPool_t2;
        this.soundPool3 = soundPool_t3;

        mPauseLock = new Object();
        mPaused = false;
        mFinished = false;
    }

    int raise_pitch=0;
    double[] load_timearray;
    int[] load_pitch;
    int[] load_velocity;
    double[] melody_array_t;
    int[]  melody_pitch;
    int[]  melody_velocity;
    double[] melody_duration;
    private int iter_anim=0,miter_anim=0;
    private SoundPool soundPool;
    private SoundPool soundPool2;
    private SoundPool soundPool3;

    double  [][] rhythm_set_time= new double[15][];
    int [][] rhythm_set_pitch= new int[15][];
    int [][] rhythm_set_velocity=new int[15][];;

    double [][] melody_set_time= new double[15][];;
    double [][] melody_set_duration= new double[15][];;
    int [][] melody_set_pitch= new int[15][];
    int [][] melody_set_velocity= new int[15][];
    public Queue melodytime_queue = new LinkedList();;
    public Queue melodytime_queue_sub = new LinkedList();;
    public Queue rhythmtime_queue = new LinkedList();
    public Queue rhythmtime_queue_sub = new LinkedList();
    public Queue rhythmtime_queue2 = new LinkedList();
    public Queue rhythmtime_queue2_sub = new LinkedList();

    Aud_struct_timer rhythm_collection= new Aud_struct_timer();
    Aud_struct_timer melody_collection= new Aud_struct_timer();
    Audiophase_structure audio_pace;
    double mt[]= new double[2];
    float[] audioval;
    public boolean rhythm_on;

    int highlighted_note=0;  //T
    int[] key_progression = new int[]{1,1,1,1,3,3,1,1,4,4,1,1,5,5,1,1,4,3,1,4,3,1};
    int []key_progression2 =new int[]{1,3,10,10,10,10,9,9,9,9,8,8,8,8,12,13};
    //int[] key_progression2 =new int[]{1,3,1,10,10,10,10,9,9,9,9,8,8,8,8,12,13,10,10,10,10,9,9,9,9,1,1,1,1}; //This is the key sequence that will be repeated.
    int[] mkey_progression = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1};
    float[] drum_amplitude = new float[8];
    float[] drum_y_offset = new float[8];
    float[] drum_frequency = new float[8];

    float current1 = 0;
    double total_dur =4000;
    int calc_difference;
    ValueAnimator animation_core_tempo;

    float endpoint_animation;
    int section=2;  //Iterate  this as the numbers go.

    public void modify__sound(float time_current){

        drum_amplitude[0]= .3f;
        drum_y_offset[0]=.5f;
        drum_frequency[0]=6f;

        drum_amplitude[1]= .5f;
        drum_y_offset[1]=.5f;
        drum_frequency[1]=12f;
        // Float []obtain_1=new Float[2];
        //Float []obtain_2=new Float[2];
        double time_duration;
        //audio1.setup_function2(7,15,3,.3f,0,.7f);
        //audio2.setup_function2(8,15,3,.3f,0,.7f);

    }

boolean audiotrack_waveon;
    public void master_time_regulator(){

        if (!audiotrack_waveon && !rhythm_on){  //Here is the importance of restarting the program
            //I will have to create some more melodies.
            int list1[] = new int[]{1,1,3,4};  //This array gets all of the rhythms since they are little disorganized.
            //There are 9 alternative rhythms.
            for (int i =1;i<14;i++) {
                ini_sound_1(i, 2);  //This is changing the rhythm  for now.
                set_rhythm(i);
            }
            set_melody(1);
            //ini_sound_1(key_progression[master_iter],2) ;
            // get_time_value();

            audiotrack_waveon=true;
            rhythm_on= true;

            audio_pace= new Audiophase_structure();
            func_feed_notes_1();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            load_anim_time(true,(long)total_dur);
            rhythm_1(); //Initialiazes  animation 1.


        }
    }
public void end_session(){

    audio_on=false;

}
    void play_snd (AudioContext ac_in, float[] ar_1, double sound_time){
    }
    public void unloadSound(int[] soundID, int[]soundID2) {
    for (int i=0; i<soundID.length;i++){
        this.drum1[i]= soundID[i];  //This will take all of the drum arrays and put it into this class so that it can run by thread.
        this.melody1[i]=soundID2[i];
    }
    }

    public void set_rhythm(int set_number){
        rhythm_set_time[set_number]=load_timearray;
        rhythm_set_pitch[set_number]=load_pitch;
        rhythm_set_velocity[set_number]=load_velocity;
    }
    public void set_melody(int set_number){
        melody_set_time[set_number]=melody_array_t;
        melody_set_duration[set_number]=melody_duration;

        melody_set_pitch[set_number]=melody_pitch;
        melody_set_velocity[set_number]=melody_velocity;
    }


    void ini_sound_1(int rhymnum, int mel_num){
        //The number that it selects is the pattern. For each activity
        //there needs to be a different song or sound.
        //Timing class is intialized and then  it will be referenced for pitch, velocity, timing
        //and duration.  These values will now be referenced into the HashMap
        //These are initial values that will be referenced by the whole class.

        Timing_reference fig1 = new Timing_reference();
        Timing_reference fig2;
        fig1.modify_notes(rhymnum);//The number that it selects is the song. For each activity
        load_timearray= fig1.note_timing; //Here is where you can load every thing.
        load_pitch = fig1.pitch;
        load_velocity=fig1.velocity;


        fig2 = new Timing_reference();
        fig2.modify_notes(mel_num);
        melody_array_t= fig2.note_timing;
        melody_pitch=fig2.pitch;
        melody_velocity=fig2.velocity;
        melody_duration=fig2.duration;


        // initialiaze_beastmode_melodies();

    }
    public void activate_animation(boolean first_play){


        if (first_play){
            animation_core_tempo.start();}else{animation_core_tempo.pause();
            animation_core_tempo.resume();

        }
    }
    Aud_struct_timer obtain_rhythm1;
    Aud_struct_timer obtain_melody1;

    public void load_anim_time(boolean first_play,long tempo) {
        calc_difference= (load_timearray.length - 2);//The last value is 0 for some reason.
        animation_core_tempo= ValueAnimator.ofFloat(0f, 767); //Depending on the timing this may change, but if it is in continous loop it
        //animation_fast_tempo = ValueAnimator.ofFloat(0f, 25f, 0, -25, 0);
        Object d = new Object();


        animation_core_tempo.setDuration(tempo);  //This is the last and largest number of the time.
        animation_core_tempo.setInterpolator(new LinearInterpolator());
        animation_core_tempo.setRepeatCount(-1);  //Will see if this work.

    }
    Float[] funct_7(double x_value, float func_freq, float amplitude, float y_shift){
        Float[] xy_value1 = new Float[2];
        xy_value1[0]=(float) x_value;
        xy_value1[1]= (float) (y_shift+(amplitude*Math.cos((func_freq*x_value)+(3.14159f/2f))));
        return xy_value1;
    }
    Float[] funct_8(double x_value, float func_freq, float amplitude, float y_shift){
        Float[] xy_value1 = new Float[2];
        xy_value1[0]=(float) x_value;
        xy_value1[1]= (float) (y_shift+(amplitude*Math.sin(x_value*func_freq)));

        return xy_value1;

    }

    int current_queue_time=0;
    public boolean run_core_tempo=false;



    public void rhythm_1(){

        // anim_play();//The annimation will be limited only by a boolean function.
        current1 = (float) (current_value);
        obtain_rhythm1 = new Aud_struct_timer();


        Object get_test1 = new Object();


        int es = 0;
        try {
            if (!change_queue) {
                obtain_rhythm1 = (Aud_struct_timer) rhythmtime_queue.peek();
            }else{
                obtain_rhythm1=(Aud_struct_timer) rhythmtime_queue2.peek();
            }
            current_queue_time = rhythmtime_queue.size()-rhythmtime_queue_sub.size();
            //This gets the current position of the animation.
            if (current1 >= obtain_rhythm1.timing) {


                if (current1 < obtain_rhythm1.timing + 12 || current1 == 0) {
                    if(!change_queue) {
                        rhythmtime_queue.poll();//This takes the head off of the queue
                    }else{
                       rhythmtime_queue2.poll();
                    }
                    snd_play(obtain_rhythm1.pitch, obtain_rhythm1.velocity);

                    iter_anim++;

                    }
                    if (iter_anim > obtain_rhythm1.last_position || rhythmtime_queue.isEmpty()) {
                        iter_anim = 0;


                        if (rhythmtime_queue.isEmpty()||rhythmtime_queue2.isEmpty()) {
                            onPause();
                        }
                    }

                    //}
                } //else {
                //if (iter_anim > 5) {
                //  iter_anim = 0;
                // }
                //}

            //  get_melody_note();
        } catch (Exception ex) {

        }
    }
    public void get_melody_note(){

        try{
            obtain_melody1 = new Aud_struct_timer();
            obtain_melody1= (Aud_struct_timer) melodytime_queue.peek();
            if (current1 >= obtain_melody1.timing) {


                if (current1 <obtain_melody1.timing + 11 || current1 == 0) {
                    melodytime_queue.poll();

                    snd_play_m(obtain_melody1.pitch, obtain_melody1.velocity);
                    //1Will add play sound with the given array
                    //Will play the animation100s, whatever the animation_core_tempo start_and set time will be a slave to
                    //the music. This will just initialize the the play of the music.
                    //This is will also initialize the play of the animation_core_tempo.
                    //animation_fast_tempo.setDuration((long)((load_timearray[iter_anim+1]*1000)-(load_timearray[iter_anim+1]*1000))*5);
                    miter_anim++;

                    if (miter_anim >obtain_melody1.last_position||melodytime_queue.isEmpty()) {
                        miter_anim = 0;


                        if ( melodytime_queue.isEmpty()){animation_core_tempo.pause();}
                    }

                    //}
                } //else {
                //if (miter_anim > 5) {
                //  miter_anim = 0;
                // }
                //}
            }
        }catch(Exception ex){

        }


    }

//drum1 nototations
    //kick1-0, snare-1, lowtome1-2, midtom3, stick 4, ride2-5, hatc6
    public void snd_play(int b_play, float velocity) {
        int pitch;
        //I will use a hash map. That is what I probably should have used for the synthesizer.

        //Create hh=ashmap for melody.
        float divin,divor;

        //Velocity seems not to pass o well through the variables.
        if (b_play == 36) {
            float vlr=velocity/100;

            //This code modifies the sounds directly with function that have been intialized at go.

            soundPool.play(drum1[0],vlr-.3f,vlr, 1, 0, 1);
            // mod_anim = true;
        }

        if (b_play == 38 ||b_play==40) {
            float vlr=velocity/100;
            soundPool.play(drum1[1], vlr-.2f,vlr-.2f, 1, 0, 1);
            //mod_anim = false;
        }

        if (b_play == 45) {
            float vlr=velocity/100;
            Float add_func1[]= new Float[2];
            Float add_func2[]= new Float[2];;
            //This code modifies the sounds directly with function that have been intialized at go.
            // if (current_queue_time>25) {
            //   if (vlr>.5){vlr=.3f;}
            // add_func1 = funct_7((double) current_queue_time, drum_frequency[1], drum_amplitude[1], drum_y_offset[1]);
            // add_func2 = funct_8((double) current_queue_time, drum_frequency[1], drum_amplitude[1], drum_y_offset[1]);
            // }else{add_func1[1]=0f; add_func2[1]=0f;}



            soundPool.play(drum1[2], 0+vlr, 0+vlr, 1, 0, 1);
            //mod_anim = false;
        }
        if (b_play == 47) {
            float vlr=velocity/100;
            soundPool.play(drum1[3], vlr,vlr, 1, 0, 1);
            //mod_anim = false;
        }
        if (b_play == 37) {
            float vlr=velocity/100;
            soundPool.play(drum1[4],vlr-.7f, vlr-.5f, 1, 0, 1);
            //mod_anim = false;
        }
        if (b_play == 51) {
            float vlr=velocity/100;
            soundPool.play(drum1[5], vlr, vlr-.2f, 1, 0, 1);
            //gc_inter++;

            // if (gc_inter >9) {}
        }
        if (b_play == 42) {
            float vlr=velocity/100;
            soundPool.play(drum1[6], vlr-.92f,0+ vlr-.91f, 1, 0, 1);}
    }



    public void snd_play_m(int b_play, float velocity) {
        int pitch;
        //I will use a hash map. That is what I probably should have used for the synthesizer.

        //Create hh=ashmap for melody.
        float divin,divor;


        if (b_play == 36||b_play == 37) {
            float vlr=velocity/100;
            Float add_func1[]= new Float[2];
            Float add_func2[]= new Float[2];;
            //This code modifies the sounds directly with function that have been intialized at go.
            //if (current_queue_time>25) {
              //  if (vlr>.5){vlr=.3f;}
                //add_func1 = funct_7((double) current_queue_time, drum_frequency[0], drum_amplitude[0], drum_y_offset[0]);
                //add_func2 = funct_8((double) current_queue_time, drum_frequency[0], drum_amplitude[0], drum_y_offset[0]);
            //}else{add_func1[1]=0f; add_func2[1]=0f;}
            soundPool2.play(melody1[0],vlr ,vlr, 1, 0, 1);
            // mod_anim = true;
        }

        if (b_play == 38 ||b_play==40||b_play==42) {

            float vlr=velocity/100;
            soundPool2.play(melody1[1],vlr,vlr, 1, 0, 1);
            //mod_anim = false;
        }

        if (b_play == 39) {
            float vlr=velocity/100;
            Float add_func1[]= new Float[2];
            Float add_func2[]= new Float[2];;
            //This code modifies the sounds directly with function that have been intialized at go.
            if (current_queue_time>25) {
                if (vlr>.5){vlr=.3f;}
                add_func1 = funct_7((double) current_queue_time, drum_frequency[1], drum_amplitude[1], drum_y_offset[1]);
                add_func2 = funct_8((double) current_queue_time, drum_frequency[1], drum_amplitude[1], drum_y_offset[1]);
            }else{add_func1[1]=0f; add_func2[1]=0f;}



            soundPool2.play(melody1[2], vlr-.4f,vlr, 1, 0, 1);
            //mod_anim = false;
        }
        if (b_play == 41||b_play==43) {
            float vlr=velocity/100;
            soundPool2.play(melody1[3], vlr-.5f, vlr, 1, 0, 1);
            //mod_anim = false;
        }

    }
    int master_iter=0;
    //alternate_keyprogress= new int[]{1,3,1,10,10,10,10,9,9,9,9,8,8,8,8,12,13,10,10,10,10,9,9,9,9,1,1,1,1};
    public int[] alternate_keyprogress;
    public int iter_sound_change=0;
    int correct_key;
    //With the arimatic one you will have to do shorter beats in order for it to make a difference.
    //You also may want to randomize some of the numbers so that it won't be too monotonous or have an equation.

    public void determine_sound_change(int que1){
        iter_sound_change++;
        if  (que1> 5&& iter_sound_change>1){
            iter_sound_change=0;
            change_queue=true;

            //alternate_keyprogress= new int[]{10,10,10,10,9,9,9,9,8,8,8,8};
            //alternate_keyprogress= new int[]{1,3,1,10,10,10,10,9,9,9,9,8,8,8,8,12,13,10,10,10,10,9,9,9,9,1,1,1,1};
              //key_progression=alternate_keyprogress;
        }

        // if  (correct_key >10){
        //   change_queue=true;
        //  alternate_keyprogress= new int[]{7,8,8,8,8,10,10,7,9,9,7,8,8,8,8,7,7,12,12,12,12,1,1,1,3,3,3,3};
        // key_progression=alternate_keyprogress;
        // }
    }
    public void func_feed_notes_1(){


        Thread run_next_notes = new Thread(){
            public void run(){
                //This will be activated everytime go is pressed for now
                //will give the progression

                //ini_sound_1(1,2) ;
                get_time_value();
                master_iter++;
                if (master_iter<key_progression.length){
                    master_iter=0;
                }
            }



        };


        run_next_notes.start();

    }
    public  boolean isQueue_filled;
    public boolean change_queue;

    public void get_time_value() {

        // melodytime_queue2 = new PriorityQueue();

        //You are going to add all of the velocities, the pitch, the durations into the queuues.
        //Its key will be associated similar to the HashMap where it will be assessed when its time point comes up.
        //You can then assign this class to a HashMap in order to receive the queues and then you can run the queues by polling them
        int iterate_poll = 0;

        //  rhythm1_Hash = new HashMap< >();
        // melody1_Hash = new HashMap < >();
        //This code saves the rhtymqueue in a backup just in case it is 0 and it ineeds to be filled.
        if (!change_queue && (rhythmtime_queue.size() == 0)) {
            rhythmtime_queue = new LinkedList( rhythmtime_queue_sub);

        }

        if (!change_queue && (rhythmtime_queue2.size() == 0)) {
            rhythmtime_queue2 = new LinkedList(rhythmtime_queue2_sub);

        }
        //This is the same thing just in case the changes don't correspond with the rhythm in more complex versions of this software.
        if (!change_queue && (melodytime_queue.size() == 0)) {
            melodytime_queue = new LinkedList(rhythmtime_queue_sub);

        }

        //Remember key_progression is the progression through the sound.
        if (rhythmtime_queue.size() == 0) {
            for (int j = 0; j < key_progression.length - 1; j++) {//key_progression.length just to try.
                for (int i = 0; i < rhythm_set_time[key_progression[j]].length - 2; i++) {
                    rhythm_collection = new Aud_struct_timer();
                    rhythm_collection.set_values(rhythm_set_time[key_progression[j]].length - 2, rhythm_set_pitch[key_progression[j]][i], rhythm_set_velocity[key_progression[j]][i], rhythm_set_time[key_progression[j]][i], 0);
                    //melody_1_collection= new Aud_struct_timer();
                    // melody_1_collection.set_values((int)melody_array_t[melody_pitch.length-2], melody_pitch[i], melody_velocity[i],  melody_array_t[i],melody_duration[i]);


                    rhythmtime_queue.add(rhythm_collection);

                }


                change_queue = false;
            }
            isQueue_filled = true;
            if (rhythmtime_queue_sub == null && rhythmtime_queue.size() == load_timearray.length) {
                rhythmtime_queue_sub =new LinkedList (rhythmtime_queue);
            }
        }


        if (rhythmtime_queue2.size() == 0) {
            for (int j = 0; j < key_progression2.length - 1; j++) {//key_progression.length just to try.
                for (int i = 0; i < rhythm_set_time[key_progression2[j]].length - 2; i++) {
                    rhythm_collection = new Aud_struct_timer();
                    rhythm_collection.set_values(rhythm_set_time[key_progression2[j]].length - 2, rhythm_set_pitch[key_progression2[j]][i], rhythm_set_velocity[key_progression2[j]][i], rhythm_set_time[key_progression2[j]][i], 0);
                    //melody_1_collection= new Aud_struct_timer();
                    // melody_1_collection.set_values((int)melody_array_t[melody_pitch.length-2], melody_pitch[i], melody_velocity[i],  melody_array_t[i],melody_duration[i]);


                    rhythmtime_queue2.add(rhythm_collection);

                }


                change_queue = false;
            }
            isQueue_filled = true;
            if (rhythmtime_queue2_sub == null && rhythmtime_queue2.size() == load_timearray.length) {
                rhythmtime_queue2_sub = new LinkedList(rhythmtime_queue2);
            }
        }

        //Here is where you sub in the rhythtime queue.  The advantage is that you always have
        //have the original clone. in the substitute variable.
        if (change_queue && (rhythmtime_queue2.size() != 0)&& (rhythmtime_queue.size() != 0)){

            rhythmtime_queue=new LinkedList(rhythmtime_queue2);
            change_queue=false;
        }

        if (melodytime_queue.size() == 0) {
            for (int j = 0; j < mkey_progression.length - 1; j++) {
                for (int i = 0; i < melody_set_time[mkey_progression[j]].length - 2; i++) {

                    melody_collection = new Aud_struct_timer();
                    melody_collection.set_values(melody_set_time[mkey_progression[j]].length - 2, melody_set_pitch[mkey_progression[j]][i], rhythm_set_velocity[mkey_progression[j]][i], melody_set_time[mkey_progression[j]][i], melody_set_duration[mkey_progression[j]][i]);
                    melodytime_queue.add(melody_collection);
                }
            }
            if (melodytime_queue_sub == null && melodytime_queue.size() == load_timearray.length) {
                melodytime_queue_sub =new LinkedList( melodytime_queue);
            }

        }
    }



    public void setnofication(){


}




    }
