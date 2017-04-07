package com.dscfgos.summonerstats.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.dscfgos.messaging.DSMessage;
import com.dscfgos.messaging.MessagingManager;
import com.dscfgos.summonerstats.MainActivity;
import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.constants.DSMessageTypes;
import com.dscfgos.summonerstats.constants.ErrorsCode;
import com.dscfgos.summonerstats.dtos.Shard;
import com.dscfgos.summonerstats.dtos.Summoner;
import com.dscfgos.summonerstats.dtos.SummonerResult;
import com.dscfgos.summonerstats.interfaces.ShardsResponseListener;
import com.dscfgos.summonerstats.interfaces.SummonerResponseListener;
import com.dscfgos.summonerstats.rest_client.ShardsManager;
import com.dscfgos.summonerstats.rest_client.SummonerManager;
import com.dscfgos.utils.ItemsManager;

import java.util.List;

public class NewAccountActivity extends AppCompatActivity implements  SummonerResponseListener, View.OnClickListener
{
    private Spinner     cbxShards       = null;

    private EditText    txtSummoner     = null;
    private Button      btnAddAccount   = null;

    private RelativeLayout  waiting = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        cbxShards       = (Spinner) findViewById(R.id.cbxShards);

        txtSummoner     = (EditText) findViewById(R.id.txtSummoner);
        btnAddAccount   = (Button) findViewById(R.id.btnAddAccount);
        waiting         = (RelativeLayout) findViewById(R.id.waiting);

        updateShardsSpinner(ItemsManager.getInstance().getAllShards());

        btnAddAccount.setOnClickListener(this);
    }


    private void updateShardsSpinner(List<Shard> shards)
    {
        ArrayAdapter<Shard> shardsAdapter = new ArrayAdapter<Shard>(this,R.layout.custom_spinner_item,shards);
        cbxShards.setAdapter(shardsAdapter);
    }

    @Override
    public void getSummoner(SummonerResult summonerResult)
    {
        waiting.setVisibility(View.GONE);
        btnAddAccount.setEnabled(true);

        String resultCode = summonerResult.getResultCode();
        if(resultCode.equals(ErrorsCode.NO_ERRORS))
        {
            if(summonerResult.getSummoner() != null)
            {
                DSMessage message = new DSMessage(DSMessageTypes.SUMMONER_ADDED, summonerResult.getSummoner());
                MessagingManager.sendMessage(message);
                this.finish();
            }
        }
        else if(resultCode.equals(ErrorsCode.SM_001))
        {
            //Summoner not exist
            Toast.makeText(this, getResources().getString(R.string.SM_001), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v)
    {
        String summoner = txtSummoner.getText().toString();
        if(summoner != null && summoner.length() > 0)
        {
            btnAddAccount.setEnabled(false);
            waiting.setVisibility(View.VISIBLE);

            Shard selected = (Shard) cbxShards.getSelectedItem();
            SummonerManager summonerTask = new SummonerManager(NewAccountActivity.this);
            summonerTask.execute(SummonerManager.GET_SUMMONER,String.valueOf(selected.getId()),summoner);
        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.err_account_needed), Toast.LENGTH_SHORT).show();
        }


    }
}

