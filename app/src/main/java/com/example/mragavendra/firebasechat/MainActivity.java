package com.example.mragavendra.firebasechat;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*
        GETTING IMEI FROM USER
        */
        String IMEI = null;
        TelephonyManager tm = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null)
            IMEI = tm.getDeviceId();
        if (IMEI == null || IMEI .length() == 0)
            IMEI = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

         /*
        GETTING PRIMARY EMAIL ACC FROM USER
        */
        String  possibleEmail=null;
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(getApplicationContext()).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
               possibleEmail = account.name;
            }
        }

        /* Phone no,Sim Op */
//        String phnNO=null, SIMop=null, NwOp=null;
//        TelephonyManager tm = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
//
//        if (tm != null)
//        {    phnNO = tm.getLine1Number();
//             SIMop = tm.getSimOperatorName();
//             NwOp = tm.getNetworkOperator();
//        }


        //Toolbar
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //ViewPager
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        setupViewPager(viewPager);


        //tablayout
        TabLayout tabs=(TabLayout)findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //Navigation Drawer
        NavigationView navigationview=(NavigationView)findViewById(R.id.navigation_view);
        drawer=(DrawerLayout)findViewById(R.id.draw1);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator
                    = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
         }



        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawer.closeDrawers();
                return true;
            }

        });


            String identifier=possibleEmail;

                if(possibleEmail!=null && !possibleEmail.isEmpty()){
                        identifier=possibleEmail;
                        Toast.makeText(getApplicationContext(),"IDE"+identifier,Toast.LENGTH_SHORT).show();}
                else {
                    identifier=IMEI;
                    Toast.makeText(getApplicationContext(),"IDE"+identifier,Toast.LENGTH_SHORT).show();}


       

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello Snackbar!",
                        Snackbar.LENGTH_LONG).show();

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter=new Adapter(getSupportFragmentManager());
        adapter.add(new ListFragment(),"List");
        adapter.add(new TileFragment(),"Tile");
        adapter.add(new CardFragment(),"card");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter{
        private final List<Fragment> fragmentlist=new ArrayList<>();
        private final List<String> fragmentTitlelist=new ArrayList<>();


        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentlist.get(position);
        }

        @Override
        public int getCount() {
            return fragmentlist.size();
        }

        public void add(Fragment fragment, String name) {
            fragmentlist.add(fragment);
            fragmentTitlelist.add(name);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitlelist.get(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


}
