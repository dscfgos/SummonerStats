package com.dscfgos.summonerstats.classes.renderer;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.classes.utils.LeagueUtils;
import com.dscfgos.summonerstats.dtos.GameDTO;
import com.dscfgos.summonerstats.dtos.League;
import com.dscfgos.summonerstats.dtos.LeagueEntry;

import java.util.List;

/**
 * Created by David on 27-03-2017.
 */

public class RecentGamesCardAdapter extends RecyclerView.Adapter<RecentGamesCardAdapter.DataHolder>
{
    private List<GameDTO> gameList ;
    private Context context;

    public class DataHolder extends RecyclerView.ViewHolder
    {
        public TextView txtResult, txtChampionName,
                txtMapType, txtKills, txtDeaths, txtAssistes,txtWinRatio;
        public ImageView imgChampion, imgSpell1, imgSpell2,
                imgItem0, imgItem1, imgItem2, imgItem3,
                imgItem4,imgItem5,imgItem6;
        LinearLayout pnlRecentCardBorder, pnlRecentCardHeader;
        public DataHolder(View itemView)
        {
            super(itemView);
            pnlRecentCardBorder       = (LinearLayout) itemView.findViewById(R.id.pnlRecentCardBorder);
            pnlRecentCardHeader       = (LinearLayout) itemView.findViewById(R.id.pnlRecentCardHeader);

            txtResult       = (TextView) itemView.findViewById(R.id.txtResult);
            txtChampionName = (TextView) itemView.findViewById(R.id.txtChampionName);
            txtMapType = (TextView) itemView.findViewById(R.id.txtMapType);
            txtKills = (TextView) itemView.findViewById(R.id.txtKills);
            txtDeaths = (TextView) itemView.findViewById(R.id.txtDeaths);
            txtAssistes = (TextView) itemView.findViewById(R.id.txtAssistes);

            imgChampion = (ImageView) itemView.findViewById(R.id.imgChampion);
            imgSpell1 = (ImageView) itemView.findViewById(R.id.imgSpell1);
            imgSpell2 = (ImageView) itemView.findViewById(R.id.imgSpell2);
            imgItem0 = (ImageView) itemView.findViewById(R.id.imgItem0);
            imgItem1 = (ImageView) itemView.findViewById(R.id.imgItem1);
            imgItem2 = (ImageView) itemView.findViewById(R.id.imgItem2);
            imgItem3 = (ImageView) itemView.findViewById(R.id.imgItem3);
            imgItem4 = (ImageView) itemView.findViewById(R.id.imgItem4);
            imgItem5 = (ImageView) itemView.findViewById(R.id.imgItem5);
            imgItem6 = (ImageView) itemView.findViewById(R.id.imgItem6);
        }
    }

    public RecentGamesCardAdapter(Context context, List<GameDTO> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_game_card, parent, false);

        return new DataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position)
    {
        GameDTO game = gameList.get(position);
        if(game != null)
        {
            holder.txtMapType.setText(LeagueUtils.getMapNameById(context,game.getMapId()));
            if(game.getStats().isWin())
            {
                holder.txtResult.setText(context.getResources().getString(R.string.str_win));
                holder.pnlRecentCardBorder.setBackgroundColor(ContextCompat.getColor(context, R.color.color_win));
                holder.pnlRecentCardHeader.setBackgroundColor(ContextCompat.getColor(context, R.color.color_win));
            }
            else
            {
                holder.txtResult.setText(context.getResources().getString(R.string.str_losses));
                holder.pnlRecentCardBorder.setBackgroundColor(ContextCompat.getColor(context, R.color.color_defeat));
                holder.pnlRecentCardHeader.setBackgroundColor(ContextCompat.getColor(context, R.color.color_defeat));
            }


            holder.txtChampionName.setText(game.getChampion().getName());
            holder.txtKills.setText(String.valueOf(game.getStats().getChampionsKilled()));
            holder.txtDeaths.setText(String.valueOf(game.getStats().getNumDeaths()));
            holder.txtAssistes.setText(String.valueOf(game.getStats().getAssists()));

            holder.imgChampion.setImageResource(LeagueUtils.getDrawableResourceByName(context,game.getChampion().getImage().getFull()));

            holder.imgSpell1.setImageResource(LeagueUtils.getDrawableResourceByName(context,game.getSpell1Key()));
            holder.imgSpell2.setImageResource(LeagueUtils.getDrawableResourceByName(context,game.getSpell2Key()));

            holder.imgItem0.setImageResource(LeagueUtils.getItemResourceByName(context,game.getStats().getItem0()));
            holder.imgItem1.setImageResource(LeagueUtils.getItemResourceByName(context,game.getStats().getItem1()));
            holder.imgItem2.setImageResource(LeagueUtils.getItemResourceByName(context,game.getStats().getItem2()));
            holder.imgItem3.setImageResource(LeagueUtils.getItemResourceByName(context,game.getStats().getItem3()));
            holder.imgItem4.setImageResource(LeagueUtils.getItemResourceByName(context,game.getStats().getItem4()));
            holder.imgItem5.setImageResource(LeagueUtils.getItemResourceByName(context,game.getStats().getItem5()));
            holder.imgItem6.setImageResource(LeagueUtils.getItemResourceByName(context,game.getStats().getItem6()));

        }
}

    @Override
    public int getItemCount()
    {
        int result = -1;
        if(gameList != null)
            result = gameList.size();
        return result;
    }

}
