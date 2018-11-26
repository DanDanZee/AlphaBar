package yalantis.com.sidemenu.sample;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MotionEvent;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.sample.fragment.ContentFragment;
import yalantis.com.sidemenu.util.ViewAnimator;

import android.widget.TextView;
import android.widget.Toast;

import yalantis.com.sidemenu.sample.FancyButton;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;

import android.widget.ArrayAdapter;
import android.widget.ListView;

//语音识别
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.iflytek.cloud.SpeechSynthesizer;
import com.google.gson.Gson;

//login
import android.widget.EditText;
import android.text.TextUtils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

//database
import yalantis.com.sidemenu.sample.userDatabase;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    private int res = R.drawable.main11;
    private LinearLayout linearLayout;

    private List<MenuList> itemList = new ArrayList<MenuList>();
    private List<repertoryList> itemList2 = new ArrayList<repertoryList>();
    private List<repertoryList> itemList3 = new ArrayList<repertoryList>();
    /*
        private int mExpandedMenuPos = -1;
        private ListViewAdapter mAdapter;
        private LayoutInflater mLayoutInflater;
        private ArrayList<Integer> mListData;
        private OnMenuClickListenser mOnMenuClickListenser = new OnMenuClickListenser();
    */
    private final String APPID = "=5ad1cbcb";
    private TextView mTextView = null;
    private String instructionhappy = "开心";
    private String instructionexcited = "兴奋";
    private String instructionsad = "难过";
    private String instructionangry = "生气";
    private String instructiontangle = "纠结";
    private String instructionconfused = "困惑";
    private FloatingActionButton startRecord;
    private Titanic titanic;
    //进度条
    private SeekBar seekBar,seekBar2,seekBar3,seekBar4;
    private TextView textView1,textView2,textView3,textView4;
    private TextView mtvstate1,mtvstate2,mtvstate3,mtvstate4;



    public static String geth;
    public static String geth_avg;
    public static boolean delayflag=false;
    public static boolean heartflag1s=false;
    public static boolean heartflag2s=false;
    public static boolean heartflag3s=false;
    public static boolean heartflag4s=false;
    //计数器
    private int item_num1=150;
    private int item_num2=150;
    private int item_num3=150;
    private int item_num4=150;
    private int sale_num1=0;
    private int sale_num2=0;
    private int sale_num3=0;
    private int sale_num4=0;
    private int sale_num5=0;
    private int sale_num6=0;
    private int[]choice={0,0,0,0};
    private int choice_1=0;
    private int choice_2=0;
    private int choice_3=0;
    private int choice_4=0;

    private String item1=Integer.toString(item_num1);
    private String item2=Integer.toString(item_num2);
    private String item3=Integer.toString(item_num3);
    private String item4=Integer.toString(item_num4);
    private String sale1=Integer.toString(sale_num1);
    private String sale2=Integer.toString(sale_num2);
    private String sale3=Integer.toString(sale_num3);
    private String sale4=Integer.toString(sale_num4);
    private String sale5=Integer.toString(sale_num5);
    private String sale6=Integer.toString(sale_num6);

    /**
     * 规定开始音乐、暂停音乐、结束音乐的标志
     */
    public  static final int PLAT_MUSIC1=1;
    public  static final int PLAT_MUSIC2=4;
    public  static final int PLAT_MUSIC3=5;
    public  static final int PLAT_MUSIC4=6;
    public  static final int PLAT_MUSIC5=7;
    public  static final int PLAT_MUSIC6=8;

    public  static final int PAUSE_MUSIC=2;
    public  static final int STOP_MUSIC=3;

    private MyBroadCastReceiver receiver;

    public int heart_rate=0;
    private String drink_message;
    private char[] make_message={'A','A','A','A'};

    public boolean isLogin = false;
    public boolean firsttime = false;
    public boolean loading=false;


    private BluetoothDevice mBluetoothDevice = null;
    private BluetoothSocket mBluetoothSocket = null;

    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private OutputStream mOutputStream = null;
    private InputStream mInputStream = null;
    public static MainActivity instance = null;

    private ConnectedThread mConnectedThread;
    private StringBuilder sb = new StringBuilder();
    final int RECIEVE_MESSAGE = 1;
    public static boolean allowRec=false;
    public static boolean allowRec2=false;
    MenuAdapter adapter;
    RepertoryAdapter adapter2;
    RepertoryAdapter2 adapter3;

    Handler mHandler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            //do something
            //每隔1s循环执行run方法
            mHandler.postDelayed(this, 1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //speaking set
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5ad1cbcb");

        setContentView(R.layout.activity_main);
        contentFragment = ContentFragment.newInstance(R.drawable.main11);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, contentFragment)
                .commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList();

        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);

        //语音识别按钮
        startRecord= (FloatingActionButton) findViewById(R.id.speaking);
        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSpeech(MainActivity.this);
            }
        });

        //开启蓝牙
        start_bluetooth();
        //bluetooth_read();



        //drink menu init
        initMenu(); // 初始化menu数据
        repertory_menu1();
        repertory_menu2();

        adapter = new MenuAdapter(MainActivity.this, R.layout.menu_item, itemList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);



        adapter2 = new RepertoryAdapter(MainActivity.this, R.layout.repertory_item, itemList2);
        ListView listView2 = (ListView) findViewById(R.id.list_view2);
        listView2.setAdapter(adapter2);


        adapter3 = new RepertoryAdapter2(MainActivity.this, R.layout.repertory_item2, itemList3);
        ListView listView3 = (ListView) findViewById(R.id.list_view3);
        listView3.setAdapter(adapter3);


        Button btnLogin = (Button) findViewById(R.id.login_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        FancyButton choice = (FancyButton) findViewById(R.id.myorder2);
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_choice();
            }
        });

        Button choice_en = (Button) findViewById(R.id.choice_ensure);
        choice_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice_ensure();
            }
        });

        Button choice_can = (Button) findViewById(R.id.choice_cancel);
        choice_can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice_cancel();
            }
        });

        //启动读写按钮

        Button read_data = (Button) findViewById(R.id.read);
        read_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allowRec =!allowRec;
                if(allowRec)show_toast("start");
            }
        });




//进度条
        //初始化Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //隐藏Actionbar后将toolbar设置上去替换Actionbar
        setSupportActionBar(toolbar);

        init_seekbar();
        //心率


        //音乐播放
        receiver=new MyBroadCastReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.complete");
        registerReceiver(receiver,filter);



        //database
        if(firsttime == true) {
            userDatabase db = new userDatabase(MainActivity.this, "user_database", null, 1);
            db.getWritableDatabase();
            Toast.makeText(MainActivity.this, "db=" + ((db == null) ? false : true), Toast.LENGTH_SHORT).show();
        }

    }
    void init_seekbar(){
        //初始化seekbar，TextView
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        textView1 = (TextView) findViewById(R.id.tvdata);
        textView1.setText("柠檬水");
        mtvstate1=(TextView) findViewById(R.id.tvstate);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /*
            * seekbar改变时的事件监听处理
            * */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                choice[0]=progress*10;
                mtvstate1.setText("选择含量："+choice[0]+"mL");
                Log.d("debug",String.valueOf(seekBar.getId()));
            }
            /*
            * 按住seekbar时的事件监听处理
            * */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"正在选择",Toast.LENGTH_SHORT).show();
            }
            /*
            * 放开seekbar时的时间监听处理
            * */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"选择完成",Toast.LENGTH_SHORT).show();
            }
        });
        //
        seekBar2 = (SeekBar) findViewById(R.id.seekbar2);
        textView2 = (TextView) findViewById(R.id.tvdata2);
        textView2.setText("伏特加");
        mtvstate2=(TextView) findViewById(R.id.tvstate2);
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /*
            * seekbar改变时的事件监听处理
            * */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                choice[1]=progress*10;
                mtvstate2.setText("选择含量："+choice[1]+"mL");
                Log.d("debug",String.valueOf(seekBar.getId()));
            }
            /*
            * 按住seekbar时的事件监听处理
            * */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"正在选择",Toast.LENGTH_SHORT).show();
            }
            /*
            * 放开seekbar时的时间监听处理
            * */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"选择完成",Toast.LENGTH_SHORT).show();
            }
        });
        //
        seekBar3 = (SeekBar) findViewById(R.id.seekbar3);
        textView3 = (TextView) findViewById(R.id.tvdata3);
        textView3.setText("白兰地");
        mtvstate3=(TextView) findViewById(R.id.tvstate3);
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /*
            * seekbar改变时的事件监听处理
            * */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                choice[2]=progress*10;
                mtvstate3.setText("选择含量："+choice[2]+"mL");
                Log.d("debug",String.valueOf(seekBar.getId()));
            }
            /*
            * 按住seekbar时的事件监听处理
            * */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"正在选择",Toast.LENGTH_SHORT).show();
            }
            /*
            * 放开seekbar时的时间监听处理
            * */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"选择完成",Toast.LENGTH_SHORT).show();
            }
        });
        //
        seekBar4 = (SeekBar) findViewById(R.id.seekbar4);
        textView4 = (TextView) findViewById(R.id.tvdata4);
        textView4.setText("龙舌兰");
        mtvstate4=(TextView) findViewById(R.id.tvstate4);
        seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /*
            * seekbar改变时的事件监听处理
            * */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                choice[3]=progress*10;
                mtvstate4.setText("选择含量："+choice[3]+"mL");
                Log.d("debug",String.valueOf(seekBar.getId()));
            }
            /*
            * 按住seekbar时的事件监听处理
            * */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
               // Toast.makeText(MainActivity.this,"正在选择",Toast.LENGTH_SHORT).show();
            }
            /*
            * 放开seekbar时的时间监听处理
            * */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(MainActivity.this,"选择完成",Toast.LENGTH_SHORT).show();
            }
        });
    }


    //自定义进度条
    public void choice_ensure(){
        View menu= (findViewById(R.id.seekbar_menu));
        View menu2= (findViewById(R.id.include_main));
        menu2.setVisibility(View.VISIBLE);
        menu.setVisibility(View.GONE);
        playingmusic(PLAT_MUSIC1);
        int i;
        for (i=0;i<4;i++)
        {
            switch (choice[i]){
                case 0: make_message[i]='A';break;
                case 10:make_message[i]='B';break;
                case 20:make_message[i]='C';break;
                case 30:make_message[i]='D';break;
                case 40:make_message[i]='E';break;
                case 50:make_message[i]='F';break;
                case 60:make_message[i]='G';break;
                case 70:make_message[i]='H';break;
                case 80:make_message[i]='I';break;
                case 90:make_message[i]='J';break;
                default:make_message[i]='K';break;

            }

        }
        String message_p= new String(make_message);
        drink_message=message_p;

        show_heart();



    }

    public void judge_rest(){
        String alarm="原料";
        int flag=0;
        if (item_num1<=30){flag=1;alarm+=" [柠檬水]";}
        if (item_num2<=30){flag=1;alarm+=" [伏特加]";}
        if (item_num3<=30){flag=1;alarm+=" [白兰地]";}
        if (item_num4<=30){flag=1;alarm+=" [龙舌兰]";}
        if(flag==1)rest_alarm(alarm);
    }
    public void rest_alarm(String v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("余量警告");
        make_voice(v+" 余量不足100mL\n"+"请管理员注意添加！" );
        builder.setMessage(v+" 余量不足100mL\n"+"请管理员注意添加！" );
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("通知管理员", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }

    public void choice_cancel(){
        View menu= (findViewById(R.id.seekbar_menu));
        View menu2= (findViewById(R.id.include_main));
        menu2.setVisibility(View.VISIBLE);
        menu.setVisibility(View.GONE);
    }

    public void show_choice(){
        View menu= (findViewById(R.id.seekbar_menu));
        View menu2= (findViewById(R.id.include_main));
        menu.setVisibility(View.VISIBLE);
        menu2.setVisibility(View.GONE);

    }
//蓝牙


    void start_bluetooth()
    {
        //蓝牙
        instance = this;
        init_bluetooth();
    }

    //蓝牙模块
    void init_bluetooth()
    {
        show_toast("bluetooth is ready");
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null){
            show_toast("Bluetooth not supported");
            return;
        }

        if(! mBluetoothAdapter.isEnabled()){
            //请求开启设备蓝牙
            Intent openBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //REQUEST_OPEN_BT_CODE = 1
            startActivityForResult(openBT, 1);
        }


        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice i : pairedDevices)
        {
            if (i.getName().equals("HC-06")) {
                mBluetoothDevice = i;

                show_toast("find bluetooth!");
                break;
            }
        }

        try {
            mBluetoothSocket = mBluetoothDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mBluetoothSocket.connect();
        } catch (IOException e) {
            try {
                //show_toast("Bluetooth connection failed.");
                mBluetoothSocket.close();
                return;
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        }
        try {
            mOutputStream = mBluetoothSocket.getOutputStream();
            mInputStream = mBluetoothSocket.getInputStream();
            show_toast("ready1!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mConnectedThread = new ConnectedThread(mBluetoothSocket);
        mConnectedThread.start();


    }





    public class MyBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"音乐播放结束",Toast.LENGTH_SHORT).show();
        }
    }
    /*
    //蓝牙模块
    public void start_bluetooth2(){
    init_bluetooth2();
    }
    void init_bluetooth2()
    {
        show_toast("bluetooth is ready");
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null){
            show_toast("Bluetooth not supported");
            return;
        }

        if(! mBluetoothAdapter.isEnabled()){
            //请求开启设备蓝牙
            Intent openBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //REQUEST_OPEN_BT_CODE = 1
            startActivityForResult(openBT, 1);
        }


        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice i : pairedDevices)
        {
            if (i.getName().equals("小米小钢炮蓝牙音箱")) {
                mBluetoothDevice = i;

                show_toast("find bluetooth!");
                break;
            }
        }

        try {
            mBluetoothSocket = mBluetoothDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mBluetoothSocket.connect();
        } catch (IOException e) {
            try {
                //show_toast("Bluetooth connection failed.");
                mBluetoothSocket.close();
                return;
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        }
        try {
            mOutputStream = mBluetoothSocket.getOutputStream();
            mInputStream = mBluetoothSocket.getInputStream();
            show_toast("ready2!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    */

    void show_toast(final String msg)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void bluetooth_send(String msg)
    {

        try {
            byte[] buffer = msg.getBytes();
            mOutputStream.write(buffer);
            show_toast("Send: " + msg);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //蓝牙接收

    public void get_press(String press){
        //判断压感状态
        if(press.compareTo("b")<= 0){
            show_toast("press");
            make_drink();
        }
        else{
            stop_press();
        }
    }




    public void get_value(String heart_data){
        //判断心率信号
        geth=heart_data;
        show_toast(geth);
        allowRec=false;

        if (geth.length()<5  ){
            go_head_guide(geth);
        }
        else{
            stop_guide(geth);
        }
    }

    //蓝牙接收
    @SuppressLint("HandlerLeak")
    private final Handler bluetoothIn = new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                //
                case RECIEVE_MESSAGE:                                                   // if receive massage
                    byte[] readBuf = (byte[]) msg.obj;
                    String strIncom = new String(readBuf, 0, msg.arg1);                 // create string from bytes array
                    sb.append(strIncom);                                                // append string
                    int endOfLineIndex = sb.indexOf("\r\n");                            // determine the end-of-line
                    if (endOfLineIndex > 0) {                                            // if end-of-line,
                        TextView heart = (TextView) findViewById(R.id.heart_rate);
                        heart.setText(new String(sb));
                        String heart_data = sb.toString();

                        String sbprint = sb.substring(0, endOfLineIndex);               // extract string
                        sb.delete(0, sb.length());                                      // and clear
                        get_value(heart_data);
                    }
                    break;
            }
        }

    };

    private final Handler pressIn = new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case RECIEVE_MESSAGE:                                                   // if receive massage
                    byte[] readBuf = (byte[]) msg.obj;
                    String strIncom = new String(readBuf, 0, msg.arg1);                 // create string from bytes array
                    sb.append(strIncom);                                                // append string
                    int endOfLineIndex = sb.indexOf("\r\n");                            // determine the end-of-line
                    if (endOfLineIndex > 0) {                                            // if end-of-line,
                        String heart_data = sb.toString();
                        allowRec2=false;
                        String press = sb.substring(0, endOfLineIndex);               // extract string
                        show_toast("Press!"+press);
                        sb.delete(0, sb.length());                                      // and clear

                        get_press(press);
                    }
                    break;
            }
        }

    };



    private class ConnectedThread extends Thread {
        private final BluetoothSocket mBtSocket;
        private final InputStream mInputStream;
        private final OutputStream mOutputStream;

        public ConnectedThread(BluetoothSocket socket) {
            mBtSocket = socket;
            InputStream is = null;
            OutputStream os = null;

            // 获取输入输出流
            try {
                is = socket.getInputStream();
                os = socket.getOutputStream();
            } catch (IOException e) {
            }

            mInputStream = is;
            mOutputStream = os;
        }

        public void run() {
            byte[] buffer=new byte[1024];
            int bytes;
            int counter=0;
            // 监听输入流以备获取数据
            while (true) {
                try {
                    bytes = mInputStream.read(buffer);
                    // 将接受数据发回UI处理
                    //接收心率信号
                    if(bytes!=-1&&allowRec){
                        bluetoothIn.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();

                    }
                    //接收压感信号
                    if(bytes!=-1&&allowRec2) {
                        pressIn.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer).sendToTarget();
                    }
                } catch (IOException e) {
                    break;
                }
                try {
                    //线程睡眠20ms以避免过于频繁工作  50ms->20ms
                    //导致UI处理发回的数据不及时而阻塞
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }



    //语音模块
    public void make_voice(String content){
        SpeechSynthesizer speaker = SpeechSynthesizer.createSynthesizer(MainActivity.this, null);//创建语音合成对象
        speaker.setParameter(SpeechConstant.VOICE_NAME,"xiaoyan");
        //初始化语音合成相关设置
        speaker.setParameter(SpeechConstant.SPEED,"60");
        speaker.setParameter(SpeechConstant.PITCH,"50");
        speaker.setParameter(SpeechConstant.VOLUME,"100");
        speaker.setParameter(SpeechConstant.STREAM_TYPE,"3");
        speaker.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        speaker.startSpeaking(content, null);
    }


    public void initSpeech(final Context context) {

        /*
        TextView txt = (TextView)mDialog.getWindow().getDecorView().findViewWithTag("textlink");
        txt.setText("请描述你的想法或心情");
        */
        RecognizerDialog mDialog = new RecognizerDialog(context, null);
        mDialog.setParameter(SpeechConstant.DOMAIN, "iat");
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        mDialog.setParameter(SpeechConstant.VAD_BOS, "3000");
        mDialog.setListener(new RecognizerDialogListener() {
            @Override

            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                if (!isLast) {
                    String result = parseVoice(recognizerResult.getResultString());
                    //System.out.println("说话内容:"+result);
                    if (result.contains(instructionhappy))
                    { //voice_show(result);
                        final String recommend_happy="那真是太好啦！我觉得口感直接而强烈的[曼哈顿]会很适合你！";
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                             make_voice(recommend_happy);
                            }
                        }, 1500);    //延时1s执行
                        make_voice(recommend_happy);
                        recommend(result+"\n"+"\n"+recommend_happy);
                        drink_message="U";

                    }
                    else if (result.contains(instructionexcited))
                    { //voice_show(result);
                        String recommend_excited="high起来！来一杯微辣的[海岸]！";

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                make_voice("high起来！来一杯微辣的[海岸]！");
                            }
                        }, 1500);    //延时1s执行

                        recommend(result+"\n"+"\n"+recommend_excited);
                        drink_message="V";

                    }
                    else if (result.contains(instructionsad))
                    {  //voice_show(result);
                        String recommend_sad="抱抱你，我觉得你可以来一杯[长岛冰茶],或许能安慰你。";
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                make_voice("抱抱你，我觉得你可以来一杯[长岛冰茶],或许能安慰你。");
                            }
                        }, 1500);    //延时1s执行

                        recommend(result+"\n"+"\n"+recommend_sad);
                        drink_message="W";
                    }
                    else if (result.contains(instructionangry))
                    {   //voice_show(result);
                        String recommend_angry="冷静冷静，来一杯酸酸甜甜的[玛格丽特]镇静一下吧！";
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                make_voice("冷静冷静，来一杯酸酸甜甜的[玛格丽特]镇静一下吧！");
                            }
                        }, 1500);    //延时1s执行

                        recommend(result+"\n"+"\n"+recommend_angry);
                        drink_message="X";

                    }
                    else if (result.contains(instructionconfused))
                    { // voice_show(result);
                        String recommend_confused="据说酸酸甜甜，略带小辣的[海岸]能够给人灵感。";
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                make_voice("据说酸酸甜甜，略带小辣的[海岸]能够给人灵感。");
                            }
                        }, 1500);    //延时1s执行

                        recommend(result+"\n"+"\n"+recommend_confused);
                        drink_message="Y";

                    }
                    else if (result.contains(instructiontangle))
                    { // voice_show(result);
                        String recommend_angle="或许充满气泡的[新加坡司令]能够让你豁达。";
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                make_voice("或许充满气泡的[新加坡司令]能够让你豁达。");
                            }
                        }, 1500);    //延时1s执行

                        recommend(result+"\n"+"\n"+recommend_angle);
                        drink_message="Z";

                    }
                    else
                    {
                        voice_show(result);
                    }

                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        mDialog.show();

        //获取字体所在的控件，设置为"",隐藏字体，
        TextView txt = (TextView)mDialog.getWindow().getDecorView().findViewWithTag("textlink");
        txt.setText("您现在心情如何？");

    }
    //显示框
    public void voice_show(String result){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                make_voice("请试试看换一种表达方式！我正在努力寻找推荐！");
            }
        }, 1500);    //延时1s执行

        builder.setTitle("糟糕！我没有适合的推荐！");
        builder.setMessage(result+"\n"+"\n"+"\n"+"请试试看换一种表达方式！我正在努力寻找推荐！");
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("重新对话", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }

    public void recommend(String v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(v);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("满意推荐", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(drink_message)
                {
                    case "U":{
                        playingmusic(PLAT_MUSIC1);
                    break;}
                    case "V":{
                        playingmusic(PLAT_MUSIC2);
                        break;}
                    case "W":{
                        playingmusic(PLAT_MUSIC3);
                        break;}
                    case "X":{
                        playingmusic(PLAT_MUSIC4);
                        break;}
                    case "Y":{
                        playingmusic(PLAT_MUSIC5);
                        break;}
                    case "Z":{
                        playingmusic(PLAT_MUSIC6);
                        break;}
                        default:break;


                }
                show_heart();
            }
        });
        builder.setNegativeButton("不满意推荐", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                make_voice("请继续对话！我们将继续为您推荐");
                show_toast("请继续对话！我们将继续为您推荐");
            }
        });
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }
    // parse json
    public String parseVoice(String resultString) {
        Gson gson = new Gson();
        VoiceClass voiceBean = gson.fromJson(resultString, VoiceClass.class);

        StringBuffer sb = new StringBuffer();
        ArrayList<VoiceClass.WSBean> ws = voiceBean.ws;
        for (VoiceClass.WSBean wsBean : ws) {
            String word = wsBean.cw.get(0).w;
            sb.append(word);
        }
        return sb.toString();
    }

    public class VoiceClass {

        public ArrayList<WSBean> ws;

        public class WSBean {
            public ArrayList<CWBean> cw;
        }

        public class CWBean {
            public String w;
        }
    }



    //loading效果
    public void loading(){
        TitanicTextView tv = (TitanicTextView) findViewById(R.id.my_text_view);
        // set fancy typeface
        tv.setTypeface(Typefaces.get(MainActivity.this, "Satisfy-Regular.ttf"));
        titanic=new Titanic();
        titanic.start(tv);
    }

    // main view: user guide button 触发产生 dailog
    public void onclickguide(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("User Guide");
        builder.setMessage("Q1：如何点酒？\n" +
                "\n" +
                "A：我们提供三种点酒方式：\n" +
                "1、菜单点酒：我们为您精心搭配了六种推荐酒品，以供选择。\n" +
                "2、菜单点酒：您可自定义原料，进行酒品自助配置。\n" +
                "3、语音点酒：语音输入您的心情，我们将智能为您匹配最佳酒品。\n" +
                "\n" +
                "Q2：侧栏菜单有哪些内容？\n" +
                "\n" +
                "A：侧栏菜单包括以下栏目：\n" +
                "1、\t主页：在此您可以“语音点酒”或“菜单点酒”。\n" +
                "2、\t菜单：在此您可以查看酒品介绍。\n"+
                "3、\t管理员模式：管理员可查看酒品销量和原料余量。\n"+
                "4、\t心率检测：您可在此测试您的心率，作为饮酒身体状况的健康检测辅助。\n"

        );
        builder.setIcon(R.drawable.ic_launcher);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }

    //main view: drink order button -> 触发 单选框dialog
    private int checkedItem = 0; //默认选中的item
    public void orderClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("你想要点些什么?");
        String[] drinks = {"Margarita 玛格丽特", "Singapore Sling 新加坡司令", "Long Island  Tea 长岛冰茶", "Gin Fizz 金菲士", "Manhattan 曼哈顿", "Sex on the beach 海岸"};

        builder.setSingleChoiceItems(drinks, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem = which;
            }
        });
        //设置正面按钮
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //蓝牙数据

                //start_bluetooth();
                switch (checkedItem){
                    case 0:{
                        //MainActivity.instance.bluetooth_send("A");
                        //start_bluetooth2();
                        drink_message="U";
                        playingmusic(PLAT_MUSIC1);

                        break;
                    }
                    case 1:{
                        //MainActivity.instance.bluetooth_send("B");
                        //start_bluetooth2();
                        drink_message="V";
                        playingmusic(PLAT_MUSIC2);


                        break;
                    }
                    case 2:{
                        //MainActivity.instance.bluetooth_send("C");
                        //start_bluetooth2();
                        drink_message="W";
                        playingmusic(PLAT_MUSIC3);


                        break;
                    }
                    case 3:{
                        //MainActivity.instance.bluetooth_send("X");
                        //start_bluetooth2();
                        drink_message="X";
                        playingmusic(PLAT_MUSIC4);

                        break;
                    }
                    case 4:{
                        //MainActivity.instance.bluetooth_send("E");
                        //start_bluetooth2();
                        drink_message="Y";
                        playingmusic(PLAT_MUSIC5);

                        break;
                    }
                    case 5:{
                        //MainActivity.instance.bluetooth_send("F");
                        //start_bluetooth2();
                        drink_message="Z";
                        playingmusic(PLAT_MUSIC6);

                        break;
                    }
                    default:MainActivity.instance.bluetooth_send("WRONG");break;
                }
                show_heart();


            }
        });
        //设置反面按钮
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void success_make(){
        //loading界面
        final View load_1 = (findViewById(R.id.include_main));
        final View load_2 = (findViewById(R.id.include_menu));
        final View load_3 = (findViewById(R.id.loading_menu));

        load_1.setVisibility(View.GONE);
        load_2.setVisibility(View.GONE);
        load_3.setVisibility(View.VISIBLE);
        loading();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                load_1.setVisibility(View.VISIBLE);
                load_2.setVisibility(View.GONE);
                load_3.setVisibility(View.GONE);
                make_voice("制作成功！");
                show_toast("Your drink has been successfully made!");
            }
        }, 60000);    //延时1s执行


    }
    //侧栏菜单
    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.BUILDING, R.drawable.icn_1);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.BOOK, R.drawable.icn_2);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.PAINT, R.drawable.icn_3);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.CASE, R.drawable.icn_4);
        list.add(menuItem4);
        /*
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.SHOP, R.drawable.icn_5);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.PARTY, R.drawable.icn_6);
        list.add(menuItem6);
        */

    }
    //音乐播放
    private void playingmusic(int type) {
        //启动服务，播放音乐
        Intent intent=new Intent(this,PlayingMusicServices.class);
        intent.putExtra("type",type);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }



    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_login:
                //login dialog
                playingmusic(STOP_MUSIC);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private ScreenShotable replaceFragment(ScreenShotable screenShotable, int topPosition) {
        //this.res = this.res == R.drawable.content_music ? R.drawable.main11 : R.drawable.content_music; //背景切换
        View view = findViewById(R.id.content_frame);
        int finalRadius = Math.max(view.getWidth(), view.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(view, 0, topPosition, 0, finalRadius);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.setDuration(ViewAnimator.CIRCULAR_REVEAL_ANIMATION_DURATION);

        findViewById(R.id.content_overlay).setBackgroundDrawable(new BitmapDrawable(getResources(), screenShotable.getBitmap()));
        animator.start();
        ContentFragment contentFragment = ContentFragment.newInstance(this.res);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
        return contentFragment;
    }
    //侧栏切换
    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        View include_1 = (findViewById(R.id.include_main));
        View include_2 = (findViewById(R.id.include_menu));
        View include_4 = (findViewById(R.id.heart_menu));
        View include_3 = (findViewById(R.id.management_menu));
        View include_5 = (findViewById(R.id.repertory_menu));
        View include_6 = (findViewById(R.id.repertory_menu2));

        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            case ContentFragment.BUILDING:
                this.res = R.drawable.main11;
                ScreenShotable myscreen1 = replaceFragment(screenShotable, position);
                include_2.setVisibility(View.GONE);
                include_1.setVisibility(View.VISIBLE);
                include_3.setVisibility(View.GONE);
                include_4.setVisibility(View.GONE);
                include_5.setVisibility(View.GONE);
                include_6.setVisibility(View.GONE);
                return myscreen1;
            case ContentFragment.BOOK:
                //this.res = R.drawable.main222;
                ScreenShotable myscreen2 = replaceFragment(screenShotable, position);
                include_1.setVisibility(View.GONE);
                include_2.setVisibility(View.VISIBLE);
                include_3.setVisibility(View.GONE);
                include_4.setVisibility(View.GONE);
                include_5.setVisibility(View.GONE);
                include_6.setVisibility(View.GONE);
                return myscreen2;

            case ContentFragment.PAINT:
                this.res = R.drawable.main222;
                ScreenShotable myscreen3 = replaceFragment(screenShotable, position);
                include_1.setVisibility(View.GONE);
                include_2.setVisibility(View.GONE);
                include_3.setVisibility(View.VISIBLE);
                include_4.setVisibility(View.GONE);
                include_5.setVisibility(View.GONE);
                include_6.setVisibility(View.GONE);
                return myscreen3;

            case ContentFragment.CASE:
                this.res = R.drawable.main222;
                ScreenShotable myscreen4 = replaceFragment(screenShotable, position);
                include_1.setVisibility(View.GONE);
                include_2.setVisibility(View.GONE);
                include_4.setVisibility(View.VISIBLE);
                include_3.setVisibility(View.GONE);
                include_5.setVisibility(View.GONE);
                include_6.setVisibility(View.GONE);
                return myscreen4;

            default:
                ScreenShotable myscreen6 = replaceFragment(screenShotable, position);
                Intent intent3=new Intent("com.example.Speaking_main.ACTION_START");
                startActivity(intent3);
                return myscreen6;
            //return replaceFragment(screenShotable, position);
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }
    //菜单
    private void initMenu() {
        MenuList drink1 = new MenuList("Margarita", R.drawable.drink1, "玛格丽特","口感：入口酸酸甜甜，非常的清爽","配料：50毫升柠檬汁、50毫升伏特加、25毫升白兰地");
        itemList.add(drink1);
        MenuList drink2 = new MenuList("Singapore Sling", R.drawable.drink2, "新加坡司令","口感：酸甜，外加碳酸气体的跳动","配料：50毫升柠檬汁、50毫升伏特加、50毫升白兰地");
        itemList.add(drink2);
        MenuList drink3 = new MenuList("Long Island Iced Tea", R.drawable.drink3, "长岛冰茶","口感：烈酒，甜中带点苦涩","配料：80毫升柠檬汁、50毫升白兰地");
        itemList.add(drink3);
        MenuList drink4 = new MenuList("Gin Fizz", R.drawable.drink4, "金菲士","口感：口味清爽，口感刺激","配料：50毫升柠檬汁、60毫升伏特加、40毫升龙舌兰酒");
        itemList.add(drink4);
        MenuList drink5 = new MenuList("Manhattan", R.drawable.drink5, "曼哈顿","口感：口感强烈而直接","配料：50毫升柠檬汁、75毫升伏特加、25毫升龙舌兰酒");
        itemList.add(drink5);
        MenuList drink6 = new MenuList("Sex on the beach", R.drawable.drink6, "海岸","口感：酸酸甜甜，略带小辣","配料：25毫升柠檬汁、50毫升伏特加、50毫升龙舌兰酒");
        itemList.add(drink6);

    }
    //库存
    private void repertory_menu1() {
        repertoryList drink1 = new repertoryList("Margarita", R.drawable.drink1, "玛格丽特",sale1);
        itemList2.add(drink1);
        repertoryList drink2 = new repertoryList("Singapore Sling", R.drawable.drink2, "新加坡司令",sale2);
        itemList2.add(drink2);
        repertoryList drink3 = new repertoryList("Long Island Iced Tea", R.drawable.drink3, "长岛冰茶",sale3);
        itemList2.add(drink3);
        repertoryList drink4 = new repertoryList("Gin Fizz", R.drawable.drink4, "金菲士",sale4);
        itemList2.add(drink4);
        repertoryList drink5 = new repertoryList("Manhattan", R.drawable.drink5, "曼哈顿",sale5);
        itemList2.add(drink5);
        repertoryList drink6 = new repertoryList("Sex on the beach", R.drawable.drink6, "海岸",sale6);
        itemList2.add(drink6);

    }

    private void repertory_menu2() {
        repertoryList drink1 = new repertoryList("Lemon", R.drawable.item1, "柠檬水",item1);
        itemList3.add(drink1);
        repertoryList drink2 = new repertoryList("Vodka", R.drawable.item2, "伏特加",item2);
        itemList3.add(drink2);
        repertoryList drink3 = new repertoryList("Brandy", R.drawable.item3, "白兰地",item3);
        itemList3.add(drink3);
        repertoryList drink4 = new repertoryList("Tequila", R.drawable.item4, "龙舌兰",item4);
        itemList3.add(drink4);

    }
    public void change1(View view){
        View menu= (findViewById(R.id.repertory_menu));
        View menu2= (findViewById(R.id.repertory_menu2));
        menu.setVisibility(View.VISIBLE);
        menu2.setVisibility(View.GONE);
    }
    public void change2(View view){
        View menu= (findViewById(R.id.repertory_menu));
        View menu2= (findViewById(R.id.repertory_menu2));
        menu2.setVisibility(View.VISIBLE);
        menu.setVisibility(View.GONE);
    }

    //登录
    public void login()
    {   View dialogView = (findViewById(R.id.management_menu));
        // Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();

        final EditText etName = (EditText) dialogView.findViewById(R.id.login_id);
        final EditText etPwd = (EditText) dialogView.findViewById(R.id.login_password);

        etName.getPaint().setFlags(0);
        final String name = etName.getText().toString();
        etPwd.getPaint().setFlags(0);
        final String pwd = etPwd.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(MainActivity.this, "Input Your Account", Toast.LENGTH_SHORT).show();
            return;
        }
        if (name.equals("mentor")&&pwd.equals("123456")){
            isLogin = true;
            Toast.makeText(MainActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();

            View repertory_view = (findViewById(R.id.repertory_menu));
            View login_view=(findViewById(R.id.management_menu));
            repertory_view.setVisibility(View.VISIBLE);
            login_view.setVisibility(View.GONE);
            etName.getText().clear();
            etPwd.getText().clear();

        }
        else {
            Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
            etName.getText().clear();
            etPwd.getText().clear();

        }
    }


    public void showLoginDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog dialog_login = builder.create();
        View dialogView = View.inflate(MainActivity.this, R.layout.management, null);
        //设置对话框布局
        dialog_login.setView(dialogView);
        dialog_login.show();
        final EditText etName = (EditText) dialogView.findViewById(R.id.login_id);
        final EditText etPwd = (EditText) dialogView.findViewById(R.id.login_password);
        Button btnLogin = (Button) dialogView.findViewById(R.id.login_button);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.getPaint().setFlags(0);
                final String name = etName.getText().toString();
                etPwd.getPaint().setFlags(0);
                final String pwd = etPwd.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "Input Your Account", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.equals("Mentor1")&&pwd.equals("123456")){
                    isLogin = true;
                    Toast.makeText(MainActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                    dialog_login.dismiss();
                    View repertory_view = (findViewById(R.id.repertory_menu));
                    View login_view=(findViewById(R.id.management_menu));
                    repertory_view.setVisibility(View.VISIBLE);
                    login_view.setVisibility(View.GONE);



                }
                else {
                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    etName.clearComposingText();
                    etPwd.clearComposingText();
                }
            }
        });


    }
    public void show_heart(){
        //心率界面
        final View load_1 = (findViewById(R.id.heart_menu));
        final View load_2 = (findViewById(R.id.include_main));

        load_1.setVisibility(View.VISIBLE);
        load_2.setVisibility(View.GONE);
        heart_guide();



    }
    public void shut_heart(){
        //心率界面
        final View load_1 = (findViewById(R.id.heart_menu));
        final View load_2 = (findViewById(R.id.include_main));
        allowRec=false;
        load_2.setVisibility(View.VISIBLE);
        load_1.setVisibility(View.GONE);
    }
    /*
    public void heart_judgement(){
        // TextView heart = (TextView) findViewById(R.id.heart_rate);
        //final int heart_judge=Integer.parseInt(new String(sb));
        //show_toast("judge now is"+new String(sb));
/*
        int test=100;
        String heart_judge=Integer.toString(test);
        heart.setText(heart_judge);


        //heart.setText(getResources().getString(R.string.get_msg) + new String(sb));
        //String heart_judge=Integer.toString(test);
        //heart.setText(heart_judge);
        allowRec=true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String heart=Integer.toString(heart_rate);
                show_toast(heart);
                if (heart_rate<150)
                {
                    go_head_guide();
                }
                else
                {
                    stop_guide();
                }

            }
        }, 4000);    //延时1s执行


    }
    */
    public void make_drink()
    {
        success_make();
        switch(drink_message)
        {
            case "U":{
                sale_num1++;
                sale1=Integer.toString(sale_num1);
                use_record(50,50,25,0);
                repertoryList change_sale=new repertoryList("Margarita", R.drawable.drink1, "玛格丽特",sale1);
                itemList2.set(0,change_sale);
                repertoryList now_sale=itemList2.get(0);
                //show_toast("Now"+now_sale.getSale());
                adapter2.notifyDataSetChanged();
                break;}
            case "V":{
                sale_num2++;
                use_record(50,50,50,0);
                repertoryList change_sale=new repertoryList("Singapore Sling", R.drawable.drink2, "新加坡司令",sale2);
                itemList2.set(1,change_sale);
                repertoryList now_sale=itemList2.get(0);
                //show_toast("Now"+now_sale.getSale());
                adapter2.notifyDataSetChanged();
                break;}
            case "W":{
                sale_num3++;
                use_record(80,0,50,0);
                repertoryList change_sale=new repertoryList("Long Island Iced Tea", R.drawable.drink3, "长岛冰茶",sale3);
                itemList2.set(2,change_sale);
                repertoryList now_sale=itemList2.get(0);
                //show_toast("Now"+now_sale.getSale());
                adapter2.notifyDataSetChanged();
                break;}
            case "X":{
                sale_num4++;
                use_record(50,60,0,40);
                repertoryList change_sale=new repertoryList("Gin Fizz", R.drawable.drink4, "金菲士",sale4);
                itemList2.set(3,change_sale);
                repertoryList now_sale=itemList2.get(0);
                //show_toast("Now"+now_sale.getSale());
                adapter2.notifyDataSetChanged();
                break;}
            case "Y":{
                sale_num5++;
                use_record(50,75,0,25);
                repertoryList change_sale=new repertoryList("Manhattan", R.drawable.drink5, "曼哈顿",sale5);
                itemList2.set(4,change_sale);

                adapter2.notifyDataSetChanged();
                break;}
            case "Z":{
                sale_num6++;
                use_record(25,50,0,50);
                repertoryList change_sale=new repertoryList("Sex on the beach", R.drawable.drink6, "海岸",sale6);
                itemList2.set(5,change_sale);

                adapter2.notifyDataSetChanged();
                break;}
            default:{
                use_record(choice[0],choice[1],choice[2],choice[3]);break;}

        }
        item1=Integer.toString(item_num1);
        item2=Integer.toString(item_num2);
        item3=Integer.toString(item_num3);
        item4=Integer.toString(item_num4);
        repertoryList change_item1=new repertoryList("Lemon", R.drawable.item1, "柠檬水",item1);
        itemList3.set(0,change_item1);
        repertoryList change_item2=new repertoryList("Vodka", R.drawable.item2, "伏特加",item2);
        itemList3.set(1,change_item2);
        repertoryList change_item3=new repertoryList("Brandy", R.drawable.item3, "白兰地",item3);
        itemList3.set(2,change_item3);
        repertoryList change_item4=new repertoryList("Tequila", R.drawable.item4, "龙舌兰",item4);
        itemList3.set(3,change_item4);
        adapter3.notifyDataSetChanged();
        judge_rest();
    }

    public void send_drink(){
        MainActivity.instance.bluetooth_send(drink_message);
        show_toast("send"+drink_message);

        allowRec2=true;



    }
    public void add(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("重置原料");
        String[] drinks = {"柠檬水", "伏特加", "白兰地", "龙舌兰"};

        builder.setSingleChoiceItems(drinks, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkedItem = which;

            }
        });
        //设置正面按钮
        builder.setPositiveButton("Full", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                make_voice("重置成功！");
                switch (checkedItem){
                    case 0:{
                        item_num1=150;
                        item1=Integer.toString(item_num1);

                        repertoryList change_item1=new repertoryList("Lemon", R.drawable.item1, "柠檬水",item1);
                        itemList3.set(0,change_item1);
                        adapter3.notifyDataSetChanged();
                        break;
                    }
                    case 1:{
                        item_num2=150;
                        item2=Integer.toString(item_num2);

                        repertoryList change_item2=new repertoryList("Vodka", R.drawable.item2, "伏特加",item2);
                        itemList3.set(1,change_item2);
                        adapter3.notifyDataSetChanged();
                        break;
                    }
                    case 2:{
                        item_num3=150;
                        item3=Integer.toString(item_num3);
                        repertoryList change_item3=new repertoryList("Brandy", R.drawable.item3, "白兰地",item3);
                        itemList3.set(2,change_item3);
                        adapter3.notifyDataSetChanged();
                        break;
                    }
                    case 3:{
                        item_num4=150;
                        item4=Integer.toString(item_num4);
                        repertoryList change_item4=new repertoryList("Tequila", R.drawable.item4, "龙舌兰",item4);
                        itemList3.set(3,change_item4);
                        adapter3.notifyDataSetChanged();
                        break;
                    }
                    default:{dialog.dismiss();break;}
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item1=Integer.toString(item_num1);
                item2=Integer.toString(item_num2);
                item3=Integer.toString(item_num3);
                item4=Integer.toString(item_num4);
                //show_toast(item1+item2+item3+item4);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void use_record(int item1,int item2, int item3, int item4){
        item_num1-=item1;
        item_num2-=item2;
        item_num3-=item3;
        item_num4-=item4;
    }

    public void heart_guide() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("我们将为您测试心率");
        builder.setMessage("\t\t为保障您的饮酒体验和身体健康，我们将为您测试心率\n" +
                "\t\t测试时间10s左右，我们将根据结果提供饮酒建议\n" );
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("继续测试", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                press_guide();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }
    public void press_guide() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        make_voice("请将大拇指放置在press按钮上");
        builder.setMessage("现在请将大拇指放置在press按钮上，点击屏幕上的\"开始测试\"按钮进行测试");
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("继续测试", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //heart_judgement();
                make_voice("点击按钮查看结果");
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }
    public void go_head_guide(String b){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("您的心率测试为"+b+"\n" +
                "在正常范围内，请享用您的酒品！");
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("继续制作酒品", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                send_drink();
                MainActivity.instance.bluetooth_send(drink_message);
                dialog.dismiss();
                shut_heart();
                TextView heart = (TextView) findViewById(R.id.heart_rate);
                heart.setText("");
            }
        });
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }
    public void stop_guide(String b){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("您的心率测试为"+b+"\n" +
                "已超过正常范围，建议控制您的饮酒量！" );
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("继续制作酒品", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                send_drink();
                shut_heart();
                TextView heart = (TextView) findViewById(R.id.heart_rate);
                heart.setText("");

            }
        });
        builder.setNegativeButton("放弃制作酒品", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                shut_heart();
                TextView heart = (TextView) findViewById(R.id.heart_rate);
                heart.setText("");
            }
        });
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }

    public void stop_press() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("因为您没有正确放置杯子，调制失败，请正确摆放杯子，再重新调整");
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //heart_judgement();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        //显示对话框
        dialog.show();
    }
/*
    public void showRegisterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog dialog_register = builder.create();
        View dialogView = View.inflate(MainActivity.this, R.layout.dialog_register, null);
        //设置对话框布局
        dialog_register.setView(dialogView);
        dialog_register.show();
        final EditText etName = (EditText) dialogView.findViewById(R.id.login_id);
        final EditText etPwd = (EditText) dialogView.findViewById(R.id.login_password);
        //final EditText rePwd = (EditText) dialogView.findViewById(R.id.login_repeatpwd);
        Button btnLogin = (Button) dialogView.findViewById(R.id.login_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etName.getPaint().setFlags(0);
                final String name = etName.getText().toString();
                etPwd.getPaint().setFlags(0);
                final String pwd = etPwd.getText().toString();
                rePwd.getPaint().setFlags(0);
                final String repwd = rePwd.getText().toString();

                etName.getPaint().setFlags(0);
                final String name = etName.getText().toString();
                etPwd.getPaint().setFlags(0);
                final String pwd = etPwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "用户名和密码均不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.equals("1")&pwd.equals("123456")){
                    isLogin = true;
                    Toast.makeText(MainActivity.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                    dialog_register.dismiss();
                }
                else Toast.makeText(MainActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();

            }
        });


    }
*/


}
