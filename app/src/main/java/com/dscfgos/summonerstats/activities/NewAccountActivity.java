package com.dscfgos.summonerstats.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dscfgos.summonerstats.MainActivity;
import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.dtos.Shard;
import com.dscfgos.summonerstats.interfaces.ShardsResponseListener;
import com.dscfgos.summonerstats.rest_client.ShardsManager;

import java.util.List;

public class NewAccountActivity extends AppCompatActivity implements ShardsResponseListener
{
    private Spinner cbxShards = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        cbxShards = (Spinner) findViewById(R.id.cbxShards);

        ShardsManager task = new ShardsManager(NewAccountActivity.this);
        task.execute();
    }

    @Override
    public void getAllShards(List<Shard> shards)
    {
        if(shards != null && shards.size() > 0)
        {
            updateShardsSpinner(shards);
        }
    }

    private void updateShardsSpinner(List<Shard> shards)
    {
        ArrayAdapter<Shard> shardsAdapter = new ArrayAdapter<Shard>(this,android.R.layout.simple_spinner_item,shards);
        cbxShards.setAdapter(shardsAdapter);
    }
}
