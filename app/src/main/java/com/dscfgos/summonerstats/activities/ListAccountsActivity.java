package com.dscfgos.summonerstats.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dscfgos.messaging.DSMessage;
import com.dscfgos.messaging.MessageProcessor;
import com.dscfgos.messaging.MessagingManager;
import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.classes.renderer.SummonerAdapter;
import com.dscfgos.summonerstats.classes.utils.SummonerLocalData;
import com.dscfgos.summonerstats.constants.AppConstants;
import com.dscfgos.summonerstats.constants.DSMessageTypes;
import com.dscfgos.summonerstats.constants.ErrorsCode;
import com.dscfgos.summonerstats.dtos.Summoner;
import com.dscfgos.summonerstats.dtos.SummonerResult;
import com.dscfgos.summonerstats.interfaces.SummonerResponseListener;
import com.dscfgos.summonerstats.rest_client.SummonerManager;
import com.dscfgos.utils.ItemsManager;
import com.dscfgos.utils.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListAccountsActivity extends AppCompatActivity implements MessageProcessor, SummonerResponseListener
{

    private ListView lstAccounts ;
    private List<Summoner> summonerList ;

    private RelativeLayout  waiting = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        MessagingManager.addDSMessageListener(DSMessageTypes.SUMMONER_ADDED,ListAccountsActivity.this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListAccountsActivity.this, NewAccountActivity.class);
                startActivity(intent);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstAccounts = (ListView) findViewById(R.id.lstAccounts);
        lstAccounts.setOnItemClickListener(clickItem_handler);
        SummonerLocalData localData = (SummonerLocalData) SharedPreferenceManager.getInstance().loadFromShared(this.getApplicationContext(), AppConstants.SUMMONER_SHARED_NAME,SummonerLocalData.class);
        if(localData != null && localData.getSummonerList() != null)
        {
            summonerList = localData.getSummonerList();
        }
        waiting         = (RelativeLayout) findViewById(R.id.waiting);

        this.updateList();

        registerForContextMenu(lstAccounts);

    }

    @Override
    public void processMessage(DSMessage message)
    {
        if(message != null && message.getMessageType() != null && message.getContent() != null)
        {
            if(message.getMessageType().equals(DSMessageTypes.SUMMONER_ADDED))
            {
                if(summonerList == null)
                {
                    summonerList = new ArrayList<>();
                }

                Summoner summoner = (Summoner)message.getContent();
                if(!existsInList(summoner))
                {
                    summonerList.add(summoner);
                    SharedPreferenceManager.getInstance().saveToShared(this.getApplicationContext(),AppConstants.SUMMONER_SHARED_NAME,new SummonerLocalData(summonerList,new Date()),SummonerLocalData.class);
                }

                updateList();
            }
        }
    }

    private void updateList()
    {
        if(summonerList != null && summonerList.size() > 0)
        {
            lstAccounts.setAdapter(new SummonerAdapter(this,summonerList));
        }
        else
        {
            lstAccounts.setAdapter(new SummonerAdapter(this,null));
        }
    }

    private boolean existsInList(Summoner summoner)
    {
        boolean result = false ;
        if(summonerList != null && summonerList.size() > 0 && summoner != null)
        {
            for (Summoner item:summonerList)
            {
                if(item.getId()==summoner.getId() && item.getShardid()==summoner.getShardid())
                {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    AdapterView.OnItemClickListener clickItem_handler = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Summoner summoner = (Summoner) lstAccounts.getItemAtPosition(position);
            if(summoner != null)
            {
                DSMessage message = new DSMessage(DSMessageTypes.SUMMONER_SELECTED, summoner);
                MessagingManager.sendMessage(message);
                ListAccountsActivity.this.finish();
            }
        }
    };

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        if (v.getId()==R.id.lstAccounts)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(getResources().getString(R.string.str_summoner_field) + " " +  summonerList.get(info.position).getName());
            String[] menuItems = getResources().getStringArray(R.array.accounts_operations);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        Summoner summoner = summonerList.get(info.position);

        doSummonerOperation(menuItemIndex, summoner);

        return true;
    }

    private void doSummonerOperation(int operation, Summoner summoner)
    {
        if(operation == 0)
        {
            //Update
            waiting.setVisibility(View.VISIBLE);

            this.updateSummoner(summoner);
        }
        else if(operation == 1)
        {
            //Delete
            this.deleteSummoner(summoner);
        }
    }

    private void deleteSummoner(Summoner summoner)
    {
        if(!ItemsManager.getInstance().isSelectedSummoner(summoner.getId()))
        {
            summonerList.remove(summoner);
            updateList();
            SharedPreferenceManager.getInstance().saveToShared(
                    this.getApplicationContext(),
                    AppConstants.SUMMONER_SHARED_NAME,
                    new SummonerLocalData(summonerList,new Date()),SummonerLocalData.class);
            Toast.makeText(this, getResources().getString(R.string.str_summoner_deleted), Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.str_is_summoner_selected), Toast.LENGTH_LONG).show();
        }

    }

    private void updateSummoner(Summoner summoner)
    {
        SummonerManager summonerTask = new SummonerManager(this);
        summonerTask.execute(SummonerManager.UPDATE_SUMMONER,String.valueOf(summoner.getShardid()),summoner.getName());
    }

    private void updateSummonerList(Summoner summoner)
    {
        if(summoner != null && summonerList != null)
        {
            for (Summoner item:summonerList)
            {
                if(summoner.getId()==item.getId())
                {
                    item.setProfileIconId(summoner.getProfileIconId());
                    item.setRevisionDate(summoner.getRevisionDate());
                    item.setSummonerLevel(summoner.getSummonerLevel());
                    break;
                }
            }
        }
    }

    @Override
    public void getSummoner(SummonerResult summonerResult)
    {
        if(summonerResult.getResultCode().equals(ErrorsCode.NO_ERRORS))
        {
            if(summonerResult.getSummoner() != null)
            {
                DSMessage message = new DSMessage(DSMessageTypes.SUMMONER_UPDATED, summonerResult.getSummoner());
                MessagingManager.sendMessage(message);

                this.updateSummonerList(summonerResult.getSummoner());
                this.updateList();
                SharedPreferenceManager.getInstance().saveToShared(this.getApplicationContext(),AppConstants.SUMMONER_SHARED_NAME,new SummonerLocalData(summonerList,new Date()),SummonerLocalData.class);

                Toast.makeText(this, getResources().getString(R.string.str_summoner_updated), Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.err_updating_account), Toast.LENGTH_SHORT).show();
        }

        waiting.setVisibility(View.GONE);
    }
}
