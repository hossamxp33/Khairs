package com.alkhair.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alkhair.R;
import com.alkhair.databinding.ActivityMainBinding;
import com.alkhair.helper.BroadcastHelper;
import com.alkhair.helper.PreferenceHelper;
import com.alkhair.helper.Utility;
import com.alkhair.ui.MyDonations.DonationsFragment;
import com.alkhair.ui.campaign.CampaignFragment;
import com.alkhair.ui.contactus.ContactUsFragment;
import com.alkhair.ui.kawuitElkhir.KawuitElkhairFragment;
import com.alkhair.ui.language.LanguageActivity;
import com.alkhair.ui.login.ForgetPasswordFragment;
import com.alkhair.ui.login.LoginFragment;
import com.alkhair.ui.news.NewsFragment;
import com.alkhair.ui.partners.PartnersFragment;
import com.alkhair.ui.payment.PaymentActivity;
import com.alkhair.ui.profile.MyProfileFragment;
import com.alkhair.ui.projects.ProjectDetailsFragment;
import com.alkhair.ui.projects.ProjectsFragment;
import com.alkhair.ui.registration.RegistrationFragment;
import com.alkhair.ui.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private long mLastClickTime = 0;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private ActivityMainBinding binding;
    private Receiver receiver;
    private boolean isReciverRegistered = false;
    private PreferenceHelper helper;
    private static boolean active = false;
    private NavigationView navigationView;
    ActionBar actionBar;
    private AppBarConfiguration mAppBarConfiguration;
    boolean backFromPay = false;
    public static boolean backFromProjectDetails = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        helper = new PreferenceHelper(this);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initViews();
        final View headerview = navigationView.getHeaderView(0);
        headerview.findViewById(R.id.profile).setOnClickListener(this);



      //  Log.i("getuser_id",helper.getuser_id());

        if (helper.getFirst_Time() == null ) {
    if (helper.getuser_id() == null) {
        showFragment(new LoginFragment());
        helper.setFirst_Time("1");

    }
}
    }

    private void initViews() {
        actionBar.setDisplayHomeAsUpEnabled(true);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home,
                R.id.nav_kwait_elkhir, R.id.nav_projects, R.id.nav_partners,
                R.id.nav_lang, R.id.nav_campaign, R.id.nav_news, R.id.nav_donation, R.id.nav_contact_us, R.id.nav_myProfile, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        setTittle(getResources().getString(R.string.menu_home));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if (helper.getLang().equals("ar")) {
                    actionBar.setHomeAsUpIndicator(R.drawable.menu_button);//change menu icon

                } else {
                    actionBar.setHomeAsUpIndicator(R.drawable.menu_button_icon);//change menu icon

                }
             }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                for (int i = 0; i < navigationView.getMenu().size(); i++) {
                    navigationView.getMenu().getItem(i).setChecked(false);
                }
                if (helper.getLang().equals("ar")) {
                    actionBar.setHomeAsUpIndicator(R.drawable.menu_button);//change menu icon

                } else {
                    actionBar.setHomeAsUpIndicator(R.drawable.menu_button_icon);//change menu icon

                }

            }
        };

        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (helper.getIsLogin() != null && helper.getIsLogin().equals("true")) {
                    navigationView.getMenu().getItem(9).setVisible(true);
                    navigationView.getMenu().getItem(10).setVisible(false);


                } else {
                    navigationView.getMenu().getItem(9).setVisible(false);
                    navigationView.getMenu().getItem(10).setVisible(true);

                }
                drawer.openDrawer(GravityCompat.START);
            }
        });
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        //set fragments
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                removeColor(navigationView);
                menuItem.setChecked(true);

                if (id == R.id.nav_home) {

                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    }
                    // clear all fragments
                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                    for (int j = 0; j < fm.getBackStackEntryCount(); ++j) {
                        fm.popBackStack();
                    }
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    setTittle(getResources().getString(R.string.menu_home));
                    HomeFragment homeFragment = new HomeFragment();
                    showFragment(homeFragment);
              //      drawer.closeDrawer(GravityCompat.START);


                }
                else if (id == R.id.nav_kwait_elkhir) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    }
                    // clear all fragments
                    KawuitElkhairFragment kawuitElkhairFragment = new KawuitElkhairFragment();
                    showFragment(kawuitElkhairFragment);
                    drawer.closeDrawer(GravityCompat.START);
                }
                else if (id == R.id.nav_projects) {

                    backFromProjectDetails = false;

                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {


                    }

                    toolbar.findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
                    ProjectsFragment projectsFragment = new ProjectsFragment();
                    showFragment(projectsFragment);
                    drawer.closeDrawer(GravityCompat.START);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    setTittle(getResources().getString(R.string.projects));
                }
                else if (id == R.id.nav_partners) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                    }
                    // clear all fragments
                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                    for (int j = 0; j < fm.getBackStackEntryCount(); ++j) {
                        fm.popBackStack();
                    }
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    toolbar.findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
                    PartnersFragment partnersFragment = new PartnersFragment();
                    showFragment(partnersFragment);
                    drawer.closeDrawer(GravityCompat.START);
                    setTittle(getResources().getString(R.string.partners));
                    actionBar.setDisplayHomeAsUpEnabled(false);

                }
                else if (id == R.id.nav_campaign) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                    }
                    // clear all fragments
                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                    for (int j = 0; j < fm.getBackStackEntryCount(); ++j) {
                        fm.popBackStack();
                    }
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    toolbar.findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
                    CampaignFragment campaignFragment = new CampaignFragment();
                    showFragment(campaignFragment);
                    drawer.closeDrawer(GravityCompat.START);
                    setTittle(getResources().getString(R.string.campaign));
                    actionBar.setDisplayHomeAsUpEnabled(false);

                }
                else if (id == R.id.nav_news) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                    }
                    // clear all fragments
                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                    for (int j = 0; j < fm.getBackStackEntryCount(); ++j) {
                        fm.popBackStack();
                    }
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    toolbar.findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
                    NewsFragment newsFragment = new NewsFragment();
                    showFragment(newsFragment);
                    drawer.closeDrawer(GravityCompat.START);
                    setTittle(getResources().getString(R.string.news));
                    actionBar.setDisplayHomeAsUpEnabled(false);
                }
                else if (id == R.id.nav_donation) {
                    backFromProjectDetails = false;
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    }

                    toolbar.findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
                    DonationsFragment donationsFragment = new DonationsFragment();
                    showFragment(donationsFragment);
                    drawer.closeDrawer(GravityCompat.START);
                    setTittle(getResources().getString(R.string.my_donations));
                    actionBar.setDisplayHomeAsUpEnabled(false);

                    if(helper.getuser_id() != null){
                        Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.my_donations), Toast.LENGTH_LONG).show();
                    }
                   else {
                        Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.should_login), Toast.LENGTH_LONG).show();

                    }

                }
                else if (id == R.id.nav_contact_us) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                    }
                    // clear all fragments
                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                    for (int j = 0; j < fm.getBackStackEntryCount(); ++j) {
                        fm.popBackStack();
                    }
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    toolbar.findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
                    ContactUsFragment contactUsFragment = new ContactUsFragment();
                    showFragment(contactUsFragment);
                    drawer.closeDrawer(GravityCompat.START);
                    setTittle(getResources().getString(R.string.contact_us));
                    actionBar.setDisplayHomeAsUpEnabled(false);

                }
//                else if (id == R.id.nav_myProfile) {
//                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
//
//                    }
//                    // clear all fragments
//                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
//                    for (int j = 0; j < fm.getBackStackEntryCount(); ++j) {
//                        fm.popBackStack();
//                    }
//                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    toolbar.findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
//                    MyProfileFragment myProfileFragment = new MyProfileFragment();
//                    showFragment(myProfileFragment);
//                    drawer.closeDrawer(GravityCompat.START);
//                    setTittle(getResources().getString(R.string.my_profile));
//                    actionBar.setDisplayHomeAsUpEnabled(false);
//
//                }
                else if (id == R.id.nav_lang) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {

                    }
                    // clear all fragments
                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                    for (int j = 0; j < fm.getBackStackEntryCount(); ++j) {
                        fm.popBackStack();
                    }
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    Intent i = new Intent(MainActivity.this, LanguageActivity.class);
                    startActivity(i);
                    drawer.closeDrawer(GravityCompat.START);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    toolbar.setBackgroundColor(getResources().getColor(R.color.white));


                }

                else if (id == R.id.nav_logout) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    }
                    updateUser();
                    // clear all fragments
                    FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                    for (int j = 0; j < fm.getBackStackEntryCount(); ++j) {
                        fm.popBackStack();
                    }
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                    drawer.closeDrawer(GravityCompat.START);

                }

                else if (id == R.id.nav_login){
                        if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                        }
                        FragmentManager fm = MainActivity.this.getSupportFragmentManager();
                        for (int j = 0; j < fm.getBackStackEntryCount(); ++j) {
                            fm.popBackStack();
                        }
                        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        toolbar.findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
                        LoginFragment loginFragment = new LoginFragment();
                        showFragment(loginFragment);
                        drawer.closeDrawer(GravityCompat.START);
                        setTittle(getResources().getString(R.string.login));
                        actionBar.setDisplayHomeAsUpEnabled(false);


                }
                return true;
            }

        });


    }

    private void removeColor(NavigationView view) {
        for (int i = 0; i < view.getMenu().size(); i++) {
            MenuItem item = view.getMenu().getItem(i);
            item.setChecked(false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateUser() {
        navigationView.getMenu().getItem(9).setVisible(false);
        helper.clearSpecificSharedPreferences("First_Name");
        helper.clearSpecificSharedPreferences("Last_Name");
        helper.clearSpecificSharedPreferences("user_name");
        helper.clearSpecificSharedPreferences("password");
        helper.clearSpecificSharedPreferences("user_id");
        helper.clearSpecificSharedPreferences("isLogin");
        helper.clearSpecificSharedPreferences("donation_amount");


        if (helper.getLang().equals("ar")) {
            actionBar.setHomeAsUpIndicator(R.drawable.menu_button);//change menu icon

        } else {
            actionBar.setHomeAsUpIndicator(R.drawable.menu_button_icon);//change menu icon

        }
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.findViewById(R.id.btnBack).setVisibility(View.GONE);
        MainActivity.this.getSupportActionBar().show();
        HomeFragment homeFragment = new HomeFragment();
        showFragment(homeFragment);
        // clear data
//        if (helper.getLang() != null) {
//            if (helper.getLang().equals("ar"))
//                Utility.setLocale(this, "ar");
//            else {
//                Utility.setLocale(this, "en");
//            }
//        }
    }



    public void showFragment(Fragment fragment) {
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.pop_out, R.anim.pop_in);
        mFragmentTransaction.replace(R.id.fragment, fragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }


    public void showIcon() {
        if (helper.getLang().equals("ar")) {
            actionBar.setHomeAsUpIndicator(R.drawable.menu_button);//change menu icon

        } else {
            actionBar.setHomeAsUpIndicator(R.drawable.menu_button_icon);//change menu icon

        }
    }


    public void setTittle(String tittle) {
        TextView tooltext = toolbar.findViewById(R.id.toolbarTittle);
        tooltext.setText(tittle);
    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.accelerate:
//                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
//                    return;
//                }
//                mLastClickTime = SystemClock.elapsedRealtime();
//                drawer.closeDrawer(GravityCompat.START);
//                if (helper.getuser_id() == null || helper.getuser_id().equals("")) {
//                    startActivity(new Intent(this, Splash_View.class));
//                } else {
//                    startActivity(new Intent(this, Splash_View.class));
//                }
//                break;
            case R.id.profile:
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                actionBar.setDisplayHomeAsUpEnabled(false);
                setTittle(getResources().getString(R.string.login));
                LoginFragment logInFragment = new LoginFragment();
                showFragment(logInFragment);
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();

        if (receiver == null) {
            receiver = new Receiver();
            IntentFilter filter = new IntentFilter(BroadcastHelper.ACTION_NAME);
            registerReceiver(receiver, filter);
            isReciverRegistered = true;
        }
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        if (helper.getLang() != null) {
            if (helper.getLang().equals("ar")) {
                actionBar.setHomeAsUpIndicator(R.drawable.menu_button);
            } else {
                actionBar.setHomeAsUpIndicator(R.drawable.menu_button_icon);
            }
        } else {
            actionBar.setHomeAsUpIndicator(R.drawable.menu_button_icon);
        }

    }


    @Override
    protected void onDestroy() {
        if (isReciverRegistered) {
            if (receiver != null)
                unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    public void showBack() {
        if (backFromPay) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    public void hideImageIcon(){
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    private class Receiver extends BroadcastReceiver {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            String methodName = arg1.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME);
            if (methodName != null && methodName.length() > 0) {
                switch (methodName) {
                    case "cartPressedBack":
                        if (active) {
                            FragmentManager fm = getSupportFragmentManager();
                            for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                                fm.popBackStack();
                            }
                            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        }
                        break;

                    case "menu_selected":
                        if (active) {
                            Intent menu = new Intent("load_action");
                            menu.putExtra("code", arg1.getStringExtra("code"));
                            menu.putExtra("name", arg1.getStringExtra("name"));
//                            BroadcastHelper.sendInform(MainActivityView.this, "Load_Products_Main", menu);
                        }
                        break;
                    case "reset_password":
                        setTittle(getResources().getString(R.string.changePassword));
                        ForgetPasswordFragment forgetPasswordFragment = new ForgetPasswordFragment();
                        showFragment(forgetPasswordFragment);
                        break;
                    case "project_details":

                        backFromProjectDetails = true;
                        if (helper.getLang().equals("ar")) {
                            setTittle(helper.getData("projectNameAr"));

                        } else {
                            setTittle(helper.getData("projectNameEn"));
                        }
                        ProjectDetailsFragment projectDetails = new ProjectDetailsFragment();
                        showFragment(projectDetails);
                        break;

                    case "go_to_home":
                        toolbar.findViewById(R.id.btnBack).setVisibility(View.GONE);
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        if (helper.getLang().equals("ar")) {
                            actionBar.setHomeAsUpIndicator(R.drawable.menu_button);//change menu icon

                        } else {
                            actionBar.setHomeAsUpIndicator(R.drawable.menu_button_icon);//change menu icon

                        }
                        setTittle(getResources().getString(R.string.menu_home));
                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        // clear all fragments

                        break;
                    case "registration":
                        actionBar.setDisplayHomeAsUpEnabled(false);
                        setTittle(getResources().getString(R.string.new_member));
                        FragmentManager fm = getSupportFragmentManager();
                        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                            fm.popBackStack();
                        }
                        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        RegistrationFragment registerFragment = new RegistrationFragment();
                        showFragment(registerFragment);
                        break;
                    case "go_to_projects":
                        toolbar.findViewById(R.id.btnBack).setVisibility(View.VISIBLE);
                        actionBar.setDisplayHomeAsUpEnabled(false);
                        setTittle(getResources().getString(R.string.projects));
                        break;
                    case "go_to_pay_way":
                        Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                        startActivity(intent);
                        break;
                    case "hide_main_icon":
                   //     backFromPay = true;
                        if (helper.getLang().equals("ar")) {
                            setTittle(helper.getData("projectNameAr"));
                        } else {
                            setTittle(helper.getData("projectNameEn"));
                        }
                        break;
                    case "back_from_Projects":
                        toolbar.findViewById(R.id.btnBack).setVisibility(View.GONE);
                        if (helper.getLang().equals("ar")) {
                            actionBar.setHomeAsUpIndicator(R.drawable.menu_button);//change menu icon

                        } else {
                            actionBar.setHomeAsUpIndicator(R.drawable.menu_button_icon);//change menu icon

                        }
                        setTittle(getResources().getString(R.string.menu_home));
                        break;
                    default:
                        break;
                }
            }
        }
    }

}