package com.dscfgos.summonerstats.classes.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.constants.ImagesUtils;
import com.dscfgos.summonerstats.dtos.Shard;
import com.dscfgos.summonerstats.dtos.Summoner;
import com.dscfgos.utils.ItemsManager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by David on 15-03-2017.
 */

public class SummonerAdapter extends BaseAdapter
{
    public SummonerAdapter(Context context, List<Summoner> items)
    {
        this.context = context ;
        this.items = items ;
    }
    private Context context;
    private List<Summoner> items;

    @Override
    public int getCount() {
        return (items != null)?items.size():-1;
    }

    @Override
    public Object getItem(int position)
    {
        return (items != null)?items.get(position):null;
    }

    @Override
    public long getItemId(int position)
    {
        long result = -1;
        if(items != null)
        {
            Summoner item = items.get(position);
            result = item.getId();
        }

        return result;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = convertView ;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item,parent,false);


            ImageView   imgIcon     = (ImageView) rowView.findViewById(R.id.imgIcon);
            TextView    txtName     = (TextView) rowView.findViewById(R.id.txtName);
            TextView    txtLevel    = (TextView) rowView.findViewById(R.id.txtLevel);
            TextView    txtShard    = (TextView) rowView.findViewById(R.id.txtShard);
            TextView    txtSelected = (TextView) rowView.findViewById(R.id.txtSelected);

            Summoner summoner = items.get(position);
            Shard shard = ItemsManager.getInstance().getShardById(summoner.getShardid());
            String strshard = (shard != null)?shard.getSlug().toUpperCase():"-";
            String summonerName = summoner.getName();

            txtSelected.setVisibility(ItemsManager.getInstance().isSelectedSummoner(summoner.getId())?View.VISIBLE:View.GONE);


            txtName.setText(summonerName);
            txtLevel.setText(String.valueOf(summoner.getSummonerLevel()));
            txtShard.setText(strshard);

            String image = summoner.getProfileIconId()+".png";
            Picasso.with(parent.getContext())
                    .load(ImagesUtils.getProfileImageMedium(image, ImagesUtils.IMAGE_PROFILE_TYPE, ImagesUtils.IMAGE_PROFILE_SMALL))
                    .into(imgIcon);
        }
        return rowView;
    }
}
