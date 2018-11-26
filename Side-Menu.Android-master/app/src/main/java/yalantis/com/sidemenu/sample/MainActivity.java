package yalantis.com.sidemenu.sample;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.sample.fragment.ContentFragment;
import yalantis.com.sidemenu.util.ViewAnimator;
import android.widget.Toast;

import yalantis.com.sidemenu.sample.FancyButton;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;

import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    private int res = R.drawable.main11;
    private LinearLayout linearLayout;

    private final String APPID = "=5ad1cbcb";
    private TextView mTextView = null;
    private String instructionA = "开心";
    private String instructionB = "难过";
    private String instructionL = "生气";
    private String instructionR = "纠结";
    private String instructionS = "困惑";
    private FloatingActionButton startRecord;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5ad1cbcb");
        mTextView = (TextView)findViewById(R.id.voice_tv);


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
        startRecord= (FloatingActionButton) findViewById(R.id.speaking);
        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSpeech(MainActivity.this);
            }
/*
            public void onclick_speech_btn(View view)
            {
                initSpeech(this);
            }
*/
        });


        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);

    }


    public void initSpeech(final Context context) {
        RecognizerDialog mDialog = new RecognizerDialog(context, null);
        mDialog.setParameter(SpeechConstant.DOMAIN, "iat");
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        mDialog.setParameter(SpeechConstant.VAD_BOS, "5000");
        mDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                if (!isLast) {
                    String result = parseVoice(recognizerResult.getResultString());
                    System.out.println("说话内容:"+result);
                    //mTextView.setText(result);

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("User Guide");
                    builder.setMessage(result);
                    builder.setIcon(R.drawable.ic_launcher);
                    builder.setCancelable(true);
                    AlertDialog dialog = builder.create();
                    //显示对话框
                    dialog.show();

                    /*
                    if (result.contains(instructionA))

                        //MainActivity.instance.bluetooth_send("A");
                    else if (result.contains(instructionB))

                        //MainActivity.instance.bluetooth_send("B");
                    else if (result.contains(instructionL))

                        //MainActivity.instance.bluetooth_send("L");
                    else if (result.sontains(instructionR))

                        //MainActivity.instance.bluetooth_send("R");
                    else

                        //MainActivity.instance.bluetooth_send("S");
                    */
                }
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        mDialog.show();
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

    // main view: user guide button 触发产生 dailog
    public void onclickguide(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("User Guide");
        builder.setMessage("guide information: here is the giude information ~~~~~ how are you today?");
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
        builder.setTitle("What do you want to order?");
        String[] drinks = {"Margarita", "Singapore Sling", "Long Island Iced Tea", "Gin Fizz", "Manhattan", "Sex on the beach"};

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
                dialog.dismiss();
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
        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.SHOP, R.drawable.icn_5);
        list.add(menuItem5);
        SlideMenuItem menuItem6 = new SlideMenuItem(ContentFragment.PARTY, R.drawable.icn_6);
        list.add(menuItem6);

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
            case R.id.action_settings:
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

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        View include_1 = (findViewById(R.id.include_guide));
        View include_2 = (findViewById(R.id.include_drink));

        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            case ContentFragment.BUILDING:
                this.res = R.drawable.main11;
                ScreenShotable myscreen1 = replaceFragment(screenShotable, position);
                include_2.setVisibility(View.GONE);
                include_1.setVisibility(View.VISIBLE);
                return myscreen1;
            case ContentFragment.BOOK:
                this.res = R.drawable.main22;
                ScreenShotable myscreen2 = replaceFragment(screenShotable, position);
                include_1.setVisibility(View.GONE);
                include_2.setVisibility(View.VISIBLE);
                return myscreen2;
            default:
                ScreenShotable myscreen3 = replaceFragment(screenShotable, position);
                Intent intent3=new Intent("com.example.Speaking_main.ACTION_START");
                startActivity(intent3);
                return myscreen3;
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
}
