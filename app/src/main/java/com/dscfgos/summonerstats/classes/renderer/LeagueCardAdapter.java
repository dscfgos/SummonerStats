package com.dscfgos.summonerstats.classes.renderer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.classes.utils.LeagueUtils;
import com.dscfgos.summonerstats.dtos.League;
import com.dscfgos.summonerstats.dtos.LeaguePosition;

import java.util.List;

/**
 * Created by David on 27-03-2017.
 */

public class LeagueCardAdapter extends RecyclerView.Adapter<LeagueCardAdapter.DataHolder>
{
    private List<LeaguePosition> leagueList ;
    private Context context;

    public class DataHolder extends RecyclerView.ViewHolder
    {
        public TextView txtQueueType, txtLeagueUser,txtTierRank,txtLeagueWins,txtLeagueLosses,txtLeaguePoints,txtWinRatio;
        public ImageView imgTierRank;

        public DataHolder(View itemView)
        {
            super(itemView);
            txtQueueType = (TextView) itemView.findViewById(R.id.txtQueueType);
            txtLeagueUser = (TextView) itemView.findViewById(R.id.txtLeagueUser);
            txtTierRank = (TextView) itemView.findViewById(R.id.txtTierRank);
            txtLeagueWins = (TextView) itemView.findViewById(R.id.txtLeagueWins);
            txtLeagueLosses = (TextView) itemView.findViewById(R.id.txtLeagueLosses);
            txtLeaguePoints = (TextView) itemView.findViewById(R.id.txtLeaguePoints);
            txtWinRatio = (TextView) itemView.findViewById(R.id.txtWinRatio);

            imgTierRank = (ImageView) itemView.findViewById(R.id.imgTierRank);

        }
    }

    public LeagueCardAdapter(Context context, List<LeaguePosition> leagueList) {
        this.context = context;
        this.leagueList = leagueList;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.league_card, parent, false);

        return new DataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position)
    {

        LeaguePosition league = leagueList.get(position);

        String tier = context.getResources().getString(LeagueUtils.getTierNameId(league.getTier())) + " " + league.getRank();
        int winratio = league.getWins()*100/(league.getWins()+ league.getLosses());
        holder.txtQueueType.setText(league.getQueueType());
        holder.txtLeagueUser.setText(league.getPlayerOrTeamName());
        holder.txtTierRank.setText(tier);
        holder.txtLeagueWins.setText(String.valueOf(league.getWins()));
        holder.txtLeagueLosses.setText(String.valueOf(league.getLosses()));
        holder.txtLeaguePoints.setText(String.valueOf(league.getLeaguePoints()));
        holder.txtWinRatio.setText(String.valueOf(winratio) + "%");
        holder.imgTierRank.setImageResource(LeagueUtils.getTierResourceId(league.getTier(), league.getRank()));
}

    @Override
    public int getItemCount()
    {
        int result = -1;
        if(leagueList != null)
            result = leagueList.size();
        return result;
    }

}
