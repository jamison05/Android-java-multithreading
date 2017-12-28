package drjpreludes.preluded_1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Counting_activity extends AppCompatActivity {
    ImageButton key1;
    ImageButton key2;
    ImageButton key3;
    ImageButton go_but;
    ImageButton stop_but;
    ImageView num1but;
    ImageView num2but;
    ImageView count_num;


    public void onDestroy() {



            if (soundPool != null) {

                soundPool.release();
                soundPool2.release();
            }
            System.gc();

        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting_activity);



       //Thread kaudio = new Thread(start_testsynth);
        //kaudio.start();
        soundp1_init(this);
        initiate_sound_keys();
        building_animationset(4);
        initiate_stop_button();


    }
int iter_st=0;

public void initiate_stop_button(){



}
    int [] counting = {R.color.colorTransparent,R.drawable.count_numbers1,R.drawable.count_numbers2,R.drawable.count_numbers3,R.drawable.count_numbers4,R.drawable.count_numbers5,R.drawable.count_numbers6,R.drawable.count_numbers7,R.drawable.count_numbers8,R.drawable.count_numbers9,R.drawable.count_numbers10, R.drawable.count_numbers11, R.drawable.count_numbers12,R.drawable.count_numbers13,R.drawable.count_numbers14,R.drawable.count_numbers15, R.drawable.count_numbers16,R.drawable.count_numbers17,R.drawable.count_numbers18,R.drawable.count_numbers19,R.drawable.count_numbers20,R.drawable.count_numbers_high_number3};
    int iter_fn=512;
       //public float[][] k1 = new float[400][];//Audio wave multiple waves.
    //public float[][] k2 = new float[400][];
    //public boolean key1_down=false;
  boolean key1_on = true;

public int correct_key=0;
public void determine_correct(int key_num){

    if (key_pos==1 &&key_num==1){
        correct_key++;

    }
  if (key_pos==2 &&key_num==2){
        correct_key++;
    }
   if (key_pos==3 &&key_num==3){
        correct_key++;
    }

}

int[] ps_array= new int[]{1,3,1,4,1};//
    int iter_ps=0;
public void initiate_sound_keys() {

        key1 = (ImageButton) findViewById(R.id.key1);
        key2= (ImageButton) findViewById(R.id.key2);
        key3 = (ImageButton) findViewById(R.id.key3);
        stop_but= (ImageButton) findViewById(R.id.stop_button);
        go_but= (ImageButton) findViewById(R.id.go_button);

        num1but = (ImageView) findViewById(R.id.p1);
        num2but = (ImageView) findViewById(R.id.p2_number);
        count_num=(ImageView) findViewById(R.id.count_1);
        stop_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (animation_core_tempo.isRunning()) {
            animation_core_tempo.pause();

            }
            }
        });

        go_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             master_time_regulator();

                iter_ps++;
                modify__sound(iter_ps);
                if (iter_ps>key_progression.length){iter_ps=1;}
                //progress=0;

                learn_generator_progress++;
                run_number_generator();


             if (rand_numb1>=10){
                 num1but.setAlpha(1f);
             }else{
                 num1but.setAlpha(0f);
             }
            if (!animation_core_tempo.isStarted()){
               ini_sound_1(ps_array[0], 3);
                activate_animation(true);
            }else {
            }
            count_num.setBackgroundResource(counting[rand_numb1]);
                key_pos_iter=0;
                if (key1_animation.isRunning()){

                    key1_animation.stop();
                }
                if (key2_animation.isRunning()){

                    key2_animation.stop();
                }
                if (key3_animation.isRunning()){

                    key3_animation.stop();
                }
                if (animation_core_tempo.isStarted()&&!animation_core_tempo.isPaused()) {
                    animation_core_tempo.pause();
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    determine_sound_change(3);
                    func_feed_notes_1();
                    animation_core_tempo.resume();
                    change_tempo_position(current_queue_time);
                }else{
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                  //  determine_sound_change(3);  //This function will evaluate whether to change the rhythm based on keyprogression.
                    func_feed_notes_1();
                    animation_core_tempo.resume();
                }

            }
        });

        key1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //if (key_press_iter<4){key_press_iter++;}
                    key1_on=true;
                   //play_sound_pool();
                  //  key1a(36);
                    soundPool2.play(chord_amin, .9f, .6f, 1, -1, 1);

                    determine_correct(1);
                    determine_correct_anim();
                    //return true;
                }


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    soundPool2.autoPause();
                    soundPool2.stop(chord_amin);

                    key1_on=false;
                    System.gc();
                //    return true;

                }
              return true;
            }
        });


        key2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    key1_on=true;
                    soundPool2.play(chord_cmaj, .6f, .9f, 1, -1, 1);

                    determine_correct(2);
                    determine_correct_anim();

                }


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    soundPool2.autoPause();
                    soundPool2.stop(chord_cmaj);

                    key1_on=false;
                    System.gc();
                }
                return false;
            }
        });


        key3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    key1_on=true;
                    soundPool2.play(chord_gmaj, 1f, 1f, 1, -1, 1);
                    determine_correct(3);
                    determine_correct_anim();

                }


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    soundPool2.autoPause();
                    soundPool2.stop(chord_gmaj);

                    key1_on=false;
                    System.gc();

                }
                return false;
            }
        });
    }

    int[] key_pressed = new int[5];
    int key_press_iter = 0;

int rhythm_set=1;
void determine_correct_anim(){

    if (correct_key>=10){

    }




}

    AnimationDrawable key1_animation;
    AnimationDrawable key2_animation;
    AnimationDrawable key3_animation;
    public void building_animationset(int number){

      //  AnimationSet add_animation= new AnimationSet(true);//I think that the true inside the animatation set means it shares the interpolator
        key1.setBackgroundResource(R.drawable.white_key_high_small);
        key1_animation = (AnimationDrawable) key1.getBackground();
        key2.setBackgroundResource(R.drawable.white_key_high_small);
        key2_animation = (AnimationDrawable) key2.getBackground();
        key3.setBackgroundResource(R.drawable.white_key_high_small);
        key3_animation = (AnimationDrawable) key3.getBackground();
        //So thhe problej the animation_core_tempo is if you keep the the animation drawable xml file the same
        //the the animation will not run.  That was what is happening. With numbers a lot of times
        //You are changing the xml file.  More than likely for all  three numbers to 6 numbers in the other forms
        //So what you will want to do is create the the animation.



    }
    public int key_pos_iter=0;
   public int key_pos=0;
    void play_animation (int number){


            key_pos_iter++;
            key_pos++;

        if (key_pos >3){
            key_pos=1;
        }
           switch (key_pos){
               case 1:

                   if (key1_animation.isRunning()){

                       key1_animation.stop();
                       //key1.setBackgroundResource(R.drawable.sm_wh_high_01);
                   }
                   if (key2_animation.isRunning()){

                       key2_animation.stop();
                       //key2.setBackgroundResource(R.drawable.sm_wh_high_01);
                   }
                   if (key3_animation.isRunning()){

                       key3_animation.stop();
                       //key3.setBackgroundResource(R.drawable.sm_wh_high_01);
                   }


                   key1.setBackgroundResource(R.drawable.white_key_high_small);
                   key1_animation = (AnimationDrawable) key1.getBackground();
                   //key1_animation.setOneShot(true);
                   key1_animation.start();
                   break;


               case 2:

                   if (key1_animation.isRunning()){

                       key1_animation.stop();
                       //key1.setBackgroundResource(R.drawable.sm_wh_high_01);
                   }
                   if (key2_animation.isRunning()){

                       key2_animation.stop();
                       //key2.setBackgroundResource(R.drawable.sm_wh_high_01);
                   }
                   if (key3_animation.isRunning()){

                       key3_animation.stop();
                       //key3.setBackgroundResource(R.drawable.sm_wh_high_01);
                   }


                   key2.setBackgroundResource(R.drawable.white_key_high_small);
                   key2_animation = (AnimationDrawable) key2.getBackground();
                   //key2_animation.setOneShot(true);
                   key2_animation.start();
                   break;
               case 3:

                   if (key1_animation.isRunning()){

                       key1_animation.stop();
                    //   key1.setBackgroundResource(R.drawable.sm_wh_high_01);
                   }
                   if (key2_animation.isRunning()){

                       key2_animation.stop();
                      // key2.setBackgroundResource(R.drawable.sm_wh_high_01);
                   }
                   if (key3_animation.isRunning()){

                       key3_animation.stop();
                       //key3.setBackgroundResource(R.drawable.sm_wh_high_01);
                   }


                   key3.setBackgroundResource(R.drawable.white_key_high_small);
                   key3_animation = (AnimationDrawable) key3.getBackground();
                   //key3_animation.setOneShot(true);
                   key3_animation.start();
                   break;
           }






    }
double ratio_ofqueue(int current_location){
double get_queue_ratio=0;
//Obtains the ratio of the queue and its positioning it will be called by the animation tempo.
    if  (!rhythmtime_queue_sub.isEmpty()){
    get_queue_ratio=current_location/rhythmtime_queue_sub.size();
return get_queue_ratio;
}
return get_queue_ratio;
}
double [][] tempo_change = new double[][]{{5000,.8},{6000,.90}};//The first value is the tempo// the second value is the time in ratio to completion.
public int tempo_iter=0;
    void change_tempo_position(int current_location){
//The animation tempo will investiate the tempo then stop it if the ratio is greater.
if (!animation_core_tempo.isRunning()&&correct_key>50){
    animation_core_tempo.end();
    if (tempo_change[tempo_iter][0]>.1) {
        load_anim_time(true, (long) tempo_change[tempo_iter][0]);  //The first value is the tempo// the second value is the time.
        rhythm_1();
    }
    animation_core_tempo.start();
    tempo_iter++;
}
if(tempo_iter>tempo_change.length){tempo_iter=0;}

}

    void key_up_function(int keyinp){





    }
    int kick1,hatc, ride2,stick,snare,lowtom1, midtom2;
    int loop_wave, awesomestring1, chord_amin, chord_cmaj, chord_fmaj, chord_gmaj;
int org_notec1, org_noted1, org_notee1, org_notef1,org_noteg1;
    private void soundp1_init(Context context){

        //I cannot extend this claass so this will be where all of the different types of drums will be
        //I will have at most 5 different types of drums playing.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(6).build();
        } else {

            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(6).build();
        } else {

            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool2 = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(6).build();
        } else {

            soundPool2 = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }


        kick1 = soundPool.load(this, R.raw.kick_real, 1);
        hatc = soundPool.load(this, R.raw.closedhat, 1);
        stick = soundPool.load(this, R.raw.stick, 1);
        snare=soundPool.load(this,R.raw.snare,1);
        ride2 = soundPool.load(this, R.raw.lightride, 1);
        lowtom1= soundPool.load(this, R.raw.tom_low,1);
        midtom2= soundPool.load(this, R.raw.tom_med,1);
        loop_wave = soundPool.load(this, R.raw.lp1_wave_continous, 1);



        //awesomestring1 = soundPool2.load(this, R.raw.cmaj, 1);
        chord_amin= soundPool2.load(this, R.raw.amin,1);
        chord_cmaj= soundPool2.load(this, R.raw.cmaj,1);
        chord_fmaj= soundPool2.load(this, R.raw.fmaj,1);
        chord_gmaj= soundPool2.load(this,R.raw.gmaj,1);


        org_notec1= soundPool2.load(this,R.raw.note_c_1,1);
        org_notee1= soundPool2.load(this,R.raw.note_e_1,1);
        org_notef1= soundPool2.load(this,R.raw.note_f_1,1);
        org_noteg1= soundPool2.load(this,R.raw.note_g_1,1);


        //MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.bassdrum_2);
        //mediaPlayer.start(); // no need to call prepare(); create() does that for you
        //mediaPlayer.reset();


     //   soundID	int: a soundID mareturned by the load() function
       // leftVolume	float: left volume value (range = 0.0 to 1.0)
        //rightVolume	float: right volume value (range = 0.0 to 1.0)
        //priority	int: stream priority (0 = lowest priority)
        //loop	int: loop mode (0 = no loop, -1 = loop forever)
        //rate	float: playback rate (1.0 = normal playback, range 0.5 to 2.0)



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
    public Queue melodytime_queue = new LinkedList();;
    public Queue melodytime_queue_sub = new LinkedList();;
    public Queue rhythmtime_queue = new LinkedList();
    public Queue rhythmtime_queue_sub = new LinkedList();
    public Queue rhythmtime_queue2 = new LinkedList();
    public Queue rhythmtime_queue2_sub = new LinkedList();
    public HashMap < Double,Aud_struct_timer> melody1_Hash;
    public HashMap < Double,Aud_struct_timer> rhythm1_Hash;
    Aud_struct_timer rhythm_collection= new Aud_struct_timer();
    Aud_struct_timer melody_collection= new Aud_struct_timer();
    Audiophase_structure audio_pace;
    int[] anim= new int[]{R.drawable.whole_anim0,R.drawable.whole_anim1,R.drawable.whole_anim2,R.drawable.whole_anim3,R.drawable.whole_anim4,R.drawable.whole_anim5,R.drawable.whole_anim6,R.drawable.whole_anim7,R.drawable.whole_anim8,R.drawable.whole_anim9};
    int ar_1=512;
    public int progress=0;
    float[] audioval;
    public boolean audiotrack_waveon;
    public boolean rhythm_on;
    public boolean is_complex_wave=false;


    int highlighted_note=0;  //T
    int[] key_progression = new int[]{1,1,1,1,1,3,1,1,4,1,1,3,1};
    int[] key_progression2 =new int[]{1,3,1,10,10,10,10,9,9,9,9,8,8,8,8,12,13,10,10,10,10,9,9,9,9,1,1,1,1}; //This is the key sequence that will be repeated.
    int[] mkey_progression = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1};

    double[] tempo_progression = new double[]{1,1.25,1,1.25,1.5,1.75,2,2,2};

    double [] melod_c1duration = new double [4];

    int actsound1=0;
    float time_increments=2;
    double cal_noteduration=0;
    int flux_part_rhythym;//This will feed into the animation the stages that are required.
    int flux_part_melody;//This will feed into the animation the stages that are required.
    public int obtain_current_rhythm(int current_pace, int time){
        int num=0;
        int correct_press=0;

        return num;
    }

    public int obtain_current_melody(int current_pace, int time){
        int num=0;
        return num;
    }


    AnimationDrawable number_3_animation;
    AnimationDrawable number_4_animation;
    public void apply_animation(AnimationDrawable gen1animdraw, ImageView a1, ImageView a2, int num) {
        if (num < 10) {

            if (a2 == num2but) {
                a2.setBackgroundResource(anim[num]);
                number_4_animation = (AnimationDrawable) a2.getBackground();
                number_4_animation.start();
            }
        }

            if (num >= 10 && num < 20) {
                if (a1 == num1but) {
                    a1.setBackgroundResource(anim[1]);
                    number_3_animation = (AnimationDrawable) a1.getBackground();
                    number_3_animation.start();
                }
                if (a2 == num2but) {
                    a2.setBackgroundResource(anim[num%10]);
                    number_4_animation = (AnimationDrawable) a2.getBackground();
                    number_4_animation.start();
                }

            }
            if (num >= 20 && num < 30) {

                if (a1 == num1but) {
                    a1.setBackgroundResource(anim[2]);
                    number_3_animation = (AnimationDrawable) a1.getBackground();
                    number_3_animation.start();
                }
                if (a2 == num2but) {
                    a2.setBackgroundResource(anim[num%20]);
                    number_4_animation = (AnimationDrawable) a2.getBackground();
                    number_4_animation.start();
                }


            }

        }


   //The program will have the following iI will create a function that will modify the panning
    //overall volume, and I will have have multiple animations running, however it is  better that if
    //the person is doing good then the sound will change.


    //The values will be manipuated and feed to the sound pool
    //for volume.

    public float sound_master_volume_left=.4f;
    public float sound_master_volume_right=.4f;



    public boolean second_take_faster;
    public double duration_div=2;  //This is the variable in which I will cut  the original duration by.
    //I also may just activate an animation set. With this it is guranteed sequence of speeds.
    // So the first thing that I can do is start to create the 2.  And have them oscilate between 3 rhythms
    //So you don't have to create.
    //Oscillating through 3 rhythms with complex responsiveness would have to be done with multiple animations.
    //So I will have 1 animation set go through the music and

    AudStruct_wave audio1= new AudStruct_wave();
    AudStruct_wave audio2= new AudStruct_wave();
    Float[][] panning_left; //There will be a bout 8 sound pool objects affected this will give the audio more richness
    Float[][] panning_right;

    //You will create a functions within the soundpool parameters,
    //I think it is better to hardwire it.  They would be values in which will be fed into the sound the function
    //modify sounds will establish parameters for ceterin functions based on the particular note.

    float[] drum_amplitude = new float[8];
    float[] drum_y_offset = new float[8];
    float[] drum_frequency = new float[8];

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



    private void set_Visibility_num1(){

        num1but.setAlpha(1f);
        num1but.setBackgroundResource(R.drawable.n1_3); }//D}



    public int rand_numb1, rand_numb2, calc_numb3;
    public int learn_generator_progress=0;
    Random rand = new Random();
    void run_number_generator(){


    if (learn_generator_progress<10) {
        rand_numb1 = rand.nextInt(5);
        apply_animation(number_3_animation, num1but,num2but, rand_numb1);
        //num_to_resource(rand_numb2,anim_state1,2);
        //Addition on the first try
    }



        if (learn_generator_progress<20&&learn_generator_progress>10) {
            rand_numb1 = rand.nextInt(10);

            apply_animation(number_3_animation, num1but,num2but, rand_numb1);
            //num_to_resource(rand_numb2,anim_state1,2);
            //Addition on the first try
        }


        if (learn_generator_progress<30&&learn_generator_progress>20){
            rand_numb1 = rand.nextInt(15);
            apply_animation(number_3_animation, num1but,num2but, rand_numb1);

        }

        if (learn_generator_progress>30) {
            rand_numb1 = rand.nextInt(20);

            apply_animation(number_3_animation, num1but,num2but, rand_numb1);

        }

    }

    //So this is  it. This is the last of the programming.



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
            animation_core_tempo.start();

        }
    }




    int m1=0;

    float current1 = 0;
    double total_dur =7500;
    int calc_difference;
   ValueAnimator animation_core_tempo;

    float endpoint_animation;
    int section=2;  //Iterate  this as the numbers go.

    public void activate_animation(boolean first_play){


        if (first_play){
            animation_core_tempo.start();}else{animation_core_tempo.pause();
            animation_core_tempo.resume();

        }
    }

    Aud_struct_timer obtain_rhythm1;
    Aud_struct_timer obtain_melody1;
float current_translate=0;
    public int increment_duration;
    public void load_anim_time(boolean first_play,long tempo) {
   calc_difference= (load_timearray.length - 2);//The last value is 0 for some reason.
       animation_core_tempo= ValueAnimator.ofFloat(0f, 767); //Depending on the timing this may change, but if it is in continous loop it
        //animation_fast_tempo = ValueAnimator.ofFloat(0f, 25f, 0, -25, 0);
        Object d = new Object();


        animation_core_tempo.setDuration(tempo);  //This is the last and largest number of the time.
        //animation_fast_tempo.setDuration((long) total_dur);
        animation_core_tempo.setInterpolator(new LinearInterpolator());
        //animation_fast_tempo.setInterpolator(new LinearInterpolator());
        animation_core_tempo.setRepeatCount(-1);  //Will see if this work.
        //animation_fast_tempo.setRepeatCount(-1);
    }

    Float[] funct_1(double x_value, float func_freq, float amplitude){
        Float[] xy_value1 = new Float[2];
        xy_value1[0]=(float) x_value;
        xy_value1[1]=(float) (amplitude*Math.sin(func_freq*x_value));
        return xy_value1;
    }

    Float[] funct_2(double x_value, float func_freq, float amplitude){
        Float[] xy_value1 = new Float[2];
        xy_value1[0]=(float) x_value;
        xy_value1[1]= (float) (amplitude*Math.cos(func_freq*x_value));
        return xy_value1;
    }

    Float[] funct_3(double x_value, float func_freq, float amplitude){
        Float[] xy_value1 = new Float[2];
        if (x_value <.5){


            xy_value1[0]=(float) x_value;
            xy_value1[1]= (float) ((4*x_value)/(amplitude)); //amplitude must be between 1 and .1  1 (lower the number the lower the slope
        }

        if (x_value >.5){
            xy_value1[0]=(float) x_value;
            xy_value1[1]= (float) ((-4*x_value)/(amplitude))+2;} //amplitude must be between 1 and .1
        return xy_value1;
    }

    Float[] funct_4(double x_value, float func_freq, float amplitude, float shift_1){
        Float[] xy_value1 = new Float[2];
        xy_value1[0]=(float) x_value;
        xy_value1[1]= (float) (amplitude*Math.sin(func_freq*Math.pow(x_value, (2+(x_value*shift_1))))); //shift_1 should run in between -3 and 10
        return xy_value1;
        //You maximize maximuma and minimum, so baxically parts pecome ignored.
    }

    Float[] funct_5(double x_value, float func_freq, float amplitude,float x_shift, float y_shift){
        Float[] xy_value1 = new Float[2];
        xy_value1[0]=(float) x_value;
        xy_value1[1]= (float) (y_shift+(amplitude*Math.sin(func_freq*x_value+x_shift)));
        return xy_value1;
    }
    Float[] funct_6(double x_value, float func_freq, float amplitude,float x_shift, float y_shift){
        Float[] xy_value1 = new Float[2];
        xy_value1[0]=(float) x_value;
        xy_value1[1]= (float) (y_shift+(amplitude*Math.cos(func_freq*x_value+x_shift)));
        return xy_value1;
    }
    //X_shift is a little confusing and so even in the other functions I am not going to use it.
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


        animation_core_tempo.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


            @Override
            public void onAnimationUpdate(ValueAnimator animation_core_tempo) {


                // anim_play();//The annimation will be limited only by a boolean function.
                current1 = (float) (animation_core_tempo.getAnimatedValue());
                obtain_rhythm1 = new Aud_struct_timer();


                Object get_test1 = new Object();


                int es = 0;
                try {
                    obtain_rhythm1 = (Aud_struct_timer) rhythmtime_queue.peek();
                    current_queue_time = rhythmtime_queue_sub.size() - rhythmtime_queue.size();
                   //This gets the current position of the animation.
                    if (current1 >= obtain_rhythm1.timing) {

                        //if (iter_anim >= 0 && current1 > load_timearray[iter_anim + es]&& load_timearray[iter_anim + es] !=0) {
                        //   current1 = 0;
                        //}

                        if (current1 < obtain_rhythm1.timing + 12 || current1 == 0) {
                            rhythmtime_queue.poll();//This takes the head off of the queue

                            snd_play(obtain_rhythm1.pitch, obtain_rhythm1.velocity);
                            //1Will add play sound with the given array
                            //Will play the animation100s, whatever the animation_core_tempo start_and set time will be a slave to
                            //the music. This will just initialize the the play of the music.
                            //This is will also initialize the play of the animation_core_tempo.
                            //animation_fast_tempo.setDuration((long)((load_timearray[iter_anim+1]*1000)-(load_timearray[iter_anim+1]*1000))*5);
                            iter_anim++;

                            endpoint_animation = 757;  endpoint_animation = 500/section;
                            if (current1 >= endpoint_animation) {

                                section--;
                                if (section == 0) {
                                    section = 2;
                                } //This is the orginal  number you can divide it as much as possible.
                                if (key_pos_iter < rand_numb1) {
                                    if (key1_animation.isRunning()) {
                                        key1_animation.stop();
                                        key1.setBackgroundResource(R.drawable.sm_wh_high_01);

                                    }
                                    if (key2_animation.isRunning()) {
                                        key2_animation.stop();
                                        key2.setBackgroundResource(R.drawable.sm_wh_high_01);

                                    }
                                    if (key3_animation.isRunning()) {
                                        key3_animation.stop();
                                        key3.setBackgroundResource(R.drawable.sm_wh_high_01);

                                    }



                                    play_animation(rand_numb1);
                                } else {

                                    if (key1_animation.isRunning()) {

                                        key1_animation.stop();
                                        key1.setBackgroundResource(R.drawable.sm_wh_high_01);
                                    }
                                    if (key2_animation.isRunning()) {
                                        key2_animation.stop();
                                        key2.setBackgroundResource(R.drawable.sm_wh_high_01);
                                    }
                                    if (key3_animation.isRunning()) {
                                        key3_animation.stop();
                                        key3.setBackgroundResource(R.drawable.sm_wh_high_01);
                                    }

                                }
                            } //if (iter_anim > 5) {/section;
                            if (current1 >= endpoint_animation) {

                                section--;
                                if (section == 0) {
                                    section = 2;
                                } //This is the orginal  number you can divide it as much as possible.
                                if (key_pos_iter < rand_numb1) {
                                    if (key1_animation.isRunning()) {
                                        key1_animation.stop();
                                        key1.setBackgroundResource(R.drawable.sm_wh_high_01);

                                    }
                                    if (key2_animation.isRunning()) {
                                        key2_animation.stop();
                                        key2.setBackgroundResource(R.drawable.sm_wh_high_01);

                                    }
                                    if (key3_animation.isRunning()) {
                                        key3_animation.stop();
                                        key3.setBackgroundResource(R.drawable.sm_wh_high_01);

                                    }



                                    play_animation(rand_numb1);
                                } else {

                                    if (key1_animation.isRunning()) {

                                        key1_animation.stop();
                                        key1.setBackgroundResource(R.drawable.sm_wh_high_01);
                                    }
                                    if (key2_animation.isRunning()) {
                                        key2_animation.stop();
                                        key2.setBackgroundResource(R.drawable.sm_wh_high_01);
                                    }
                                    if (key3_animation.isRunning()) {
                                        key3_animation.stop();
                                        key3.setBackgroundResource(R.drawable.sm_wh_high_01);
                                    }

                                }
                            } //if (iter_anim > 5) {
                            if (iter_anim > obtain_rhythm1.last_position || rhythmtime_queue.isEmpty()) {
                                iter_anim = 0;


                                if (rhythmtime_queue.isEmpty()) {
                                    animation_core_tempo.pause();
                                }

                            }

                            //}
                        } //else {


                    }

                    //  iter_anim = 0;
                    // }
                    //}
                    //  get_melody_note();
                } catch (Exception ex) {

                }
            }

            });
        }


    public void intentional_pause(double mileseconds){


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



    public void snd_play(int b_play, float velocity) {
        int pitch;
        //I will use a hash map. That is what I probably should have used for the synthesizer.

        //Create hh=ashmap for melody.
        float divin,divor;

       //Velocity seems not to pass o well through the variables.
        if (b_play == 36) {
            float vlr=velocity/100;
            Float add_func1[]= new Float[2];
            Float add_func2[]= new Float[2];;
            //This code modifies the sounds directly with function that have been intialized at go.
          if (current_queue_time>25) {
                if (vlr>.5){vlr=.5f;}
                add_func1 = funct_7((double) current_queue_time, drum_frequency[0], drum_amplitude[0], drum_y_offset[0]);
                add_func2 = funct_8((double) current_queue_time, drum_frequency[0], drum_amplitude[0], drum_y_offset[0]);
            }else{add_func1[1]=0f; add_func2[1]=0f;}
            soundPool.play(kick1,vlr + add_func1[1] ,vlr+add_func2[1], 1, 0, 1);
           // mod_anim = true;
        }

        if (b_play == 38 ||b_play==40) {
            float vlr=velocity/100;
            soundPool.play(snare, vlr-.2f,vlr-.2f, 1, 0, 1);
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



            soundPool.play(lowtom1, 0+vlr+add_func1[1], 0+vlr+add_func2[1], 1, 0, 1);
            //mod_anim = false;
        }
        if (b_play == 47) {
            float vlr=velocity/100;
            soundPool.play(midtom2, vlr,vlr, 1, 0, 1);
            //mod_anim = false;
        }
        if (b_play == 37) {
            float vlr=velocity/100;
            soundPool.play(stick,vlr-.7f, vlr-.5f, 1, 0, 1);
            //mod_anim = false;
        }
        if (b_play == 51) {
            float vlr=velocity/100;
            soundPool.play(ride2, vlr, vlr-.2f, 1, 0, 1);
            //gc_inter++;

           // if (gc_inter >9) {}
        }
        if (b_play == 42) {
            float vlr=velocity/100;
            soundPool.play(hatc, vlr-.7f,0+ vlr-.7f, 1, 0, 1);}
    }



    public void snd_play_m(int b_play, float velocity) {
        int pitch;
        //I will use a hash map. That is what I probably should have used for the synthesizer.

        //Create hh=ashmap for melody.
        float divin,divor;


        if (b_play == 36) {
            float vlr=velocity/100;
            Float add_func1[]= new Float[2];
            Float add_func2[]= new Float[2];;
            //This code modifies the sounds directly with function that have been intialized at go.
            if (current_queue_time>25) {
                if (vlr>.5){vlr=.3f;}
                add_func1 = funct_7((double) current_queue_time, drum_frequency[0], drum_amplitude[0], drum_y_offset[0]);
                add_func2 = funct_8((double) current_queue_time, drum_frequency[0], drum_amplitude[0], drum_y_offset[0]);
            }else{add_func1[1]=0f; add_func2[1]=0f;}
          soundPool2.play(org_notec1,(vlr) + add_func1[1] ,vlr+add_func2[1], 1, 0, 1);
            // mod_anim = true;
        }

        if (b_play == 38 ||b_play==40) {

            float vlr=velocity/100;
            soundPool2.play(org_notee1, vlr, vlr, 1, 0, 1);
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



            soundPool2.play(org_notef1, vlr+add_func1[1], vlr+add_func2[1], 1, 0, 1);
            //mod_anim = false;
        }
        if (b_play == 41) {
            float vlr=velocity/100;
            soundPool2.play(org_noteg1, vlr, vlr, 1, 0, 1);
            //mod_anim = false;
        }

    }
int master_iter=0;
    //alternate_keyprogress= new int[]{1,3,1,10,10,10,10,9,9,9,9,8,8,8,8,12,13,10,10,10,10,9,9,9,9,1,1,1,1};
public int[] alternate_keyprogress;
public int iter_sound_change=0;
    public void determine_sound_change(int note){
        iter_sound_change++;
    if  (correct_key >7&& iter_sound_change>7){
       iter_sound_change=0;
        //determine_sound_change(note);
        change_queue=true;
  //  alternate_keyprogress= new int[]{1,3,1,10,10,10,10,9,9,9,9,8,8,8,8,12,13,10,10,10,10,9,9,9,9,1,1,1,1};
  //   key_progression=alternate_keyprogress;
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

}
//The Hash