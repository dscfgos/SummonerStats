package com.dscfgos.summonerstats;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.dscfgos.messaging.DSMessage;
import com.dscfgos.messaging.MessageProcessor;
import com.dscfgos.messaging.MessagingManager;
import com.dscfgos.summonerstats.activities.ListAccountsActivity;
import com.dscfgos.summonerstats.constants.AppConstants;
import com.dscfgos.summonerstats.constants.DSMessageTypes;
import com.dscfgos.summonerstats.constants.ImagesUtils;
import com.dscfgos.summonerstats.dtos.Summoner;
import com.dscfgos.summonerstats.fragments.CurrentGameFragment;
import com.dscfgos.summonerstats.fragments.FragmentHistory;
import com.dscfgos.summonerstats.fragments.LeagueFragment;
import com.dscfgos.utils.ItemsManager;
import com.dscfgos.utils.SharedPreferenceManager;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    LeagueFragment.OnFragmentInteractionListener,
                    FragmentHistory.OnFragmentInteractionListener,
                    CurrentGameFragment.OnFragmentInteractionListener,
                    MessageProcessor {

    private NavigationView navigationView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = LeagueFragment.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frgContent, fragment).commit();
        }

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        MessagingManager.addDSMessageListener(DSMessageTypes.SUMMONER_SELECTED,MainActivity.this);
        MessagingManager.addDSMessageListener(DSMessageTypes.SUMMONER_UPDATED,MainActivity.this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        ItemsManager.getInstance().updateItems();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        this.updateUserUI();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mnu_account_manager)
        {
            Intent intent = new Intent(MainActivity.this, ListAccountsActivity.class);
            startActivity(intent);
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        displayView(item.getItemId());
        return true;
    }


    @Override
    public void processMessage(DSMessage message)
    {
        if(message != null && message.getMessageType() != null && message.getContent() != null)
        {
            if(message.getMessageType().equals(DSMessageTypes.SUMMONER_SELECTED))
            {
                Summoner summoner = (Summoner)message.getContent();
                if(summoner != null)
                {
                    SharedPreferenceManager.getInstance().saveToShared(this.getApplicationContext(), AppConstants.SUMMONER_SELECTED_SHARED_NAME,summoner,Summoner.class);

                    updateUserUI();
                }
            }
            if(message.getMessageType().equals(DSMessageTypes.SUMMONER_UPDATED))
            {
                Summoner summoner = (Summoner)message.getContent();
                Summoner selectedSummoner = ItemsManager.getInstance().getSelectedSummoner();
                if(summoner != null && selectedSummoner != null && selectedSummoner.getId()==summoner.getId())
                {
                    SharedPreferenceManager.getInstance().saveToShared(this.getApplicationContext(), AppConstants.SUMMONER_SELECTED_SHARED_NAME,summoner,Summoner.class);

                    updateUserUI();
                }
            }
        }
    }

    private void updateUserUI()
    {
        Summoner summoner = (Summoner) SharedPreferenceManager.getInstance().loadFromShared(this.getApplicationContext(),AppConstants.SUMMONER_SELECTED_SHARED_NAME, Summoner.class);

        ItemsManager.getInstance().setSelectedSummoner(summoner);
        //drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if(summoner != null)
        {
            TextView    txtSummoner     = (TextView) navigationView.findViewById(R.id.txtSummonerName);
            TextView    txtLevel        = (TextView) navigationView.findViewById(R.id.txtSummonerLevel);
            TextView    txtDate         = (TextView) navigationView.findViewById(R.id.txtSummonerRevisionDate);
            ImageView   imgSummonerIcon = (ImageView) navigationView.findViewById(R.id.imgSummonerIcon);

            String image = summoner.getProfileIconId()+".png";
            Date date = new Date(summoner.getRevisionDate());

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(date);

            txtSummoner.setText(summoner.getName());
            txtLevel.setText(String.valueOf(summoner.getSummonerLevel()));
            txtDate.setText(strDate);

            Picasso.with(this.getApplicationContext())
                    .load(ImagesUtils.getProfileImageMedium(image, ImagesUtils.IMAGE_PROFILE_TYPE, ImagesUtils.IMAGE_PROFILE_MEDIUM))
                    .into(imgSummonerIcon);
        }
     }

    public void displayView(int id) {

        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.mn_league)
        {
            fragmentClass = LeagueFragment.class;
        }
        else if (id == R.id.mn_history)
        {
            fragmentClass = FragmentHistory.class;
        }
        else if (id == R.id.mn_current_game)
        {
            fragmentClass = CurrentGameFragment.class;
        }
        else
        {
            fragmentClass = LeagueFragment.class;
        }
        try
        {
            fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frgContent, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
