package com.dscfgos.summonerstats.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dscfgos.messaging.DSMessage;
import com.dscfgos.messaging.MessageProcessor;
import com.dscfgos.messaging.MessagingManager;
import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.classes.renderer.LeagueCardAdapter;
import com.dscfgos.summonerstats.classes.renderer.RecentGamesCardAdapter;
import com.dscfgos.summonerstats.constants.AppConstants;
import com.dscfgos.summonerstats.constants.DSMessageTypes;
import com.dscfgos.summonerstats.constants.ErrorsCode;
import com.dscfgos.summonerstats.dtos.GameDTO;
import com.dscfgos.summonerstats.dtos.League;
import com.dscfgos.summonerstats.dtos.RecentGamesResult;
import com.dscfgos.summonerstats.dtos.Summoner;
import com.dscfgos.summonerstats.interfaces.RecentGamesResponseListener;
import com.dscfgos.summonerstats.rest_client.CurrentGameManager;
import com.dscfgos.summonerstats.rest_client.RecentGamesManager;
import com.dscfgos.utils.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HistoryFragment extends Fragment implements RecentGamesResponseListener, MessageProcessor
{
    private OnFragmentInteractionListener mListener;
    private View            view            = null;
    private RecyclerView    rcyRecentGames   = null;
    private RelativeLayout waiting = null;
    private Summoner summoner = null;

    public HistoryFragment() {}

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessagingManager.addDSMessageListener(DSMessageTypes.SUMMONER_SELECTED,this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_history, container, false);
        rcyRecentGames       = (RecyclerView) view.findViewById(R.id.rcyHistory);

        waiting         = (RelativeLayout) view.findViewById(R.id.waiting);

        updateRecentGames();

        return view ;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void getRecentGames(RecentGamesResult recentGamesResult)
    {
        String resultCode = recentGamesResult.getResultCode();
        if(resultCode.equals(ErrorsCode.NO_ERRORS))
        {
            Set<GameDTO> gameSet = recentGamesResult.getRecentGames().getGames();
            if(gameSet != null && gameSet.size() > 0)
            {
                List<GameDTO> gameList = new ArrayList<>(gameSet);

                RecentGamesCardAdapter adapter = new RecentGamesCardAdapter(this.getContext(),gameList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
                rcyRecentGames.setLayoutManager(mLayoutManager);

                rcyRecentGames.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
        else if(resultCode.equals(ErrorsCode.RG_001))
        {
            /*String unranked = getResources().getString(R.string.str_unranked);
            txtLeagueUser.setVisibility(View.INVISIBLE);
            txtTierRank.setText(unranked);
            imgTierRank.setImageResource(LeagueUtils.getTierResourceId(null));*/
        }

        waiting.setVisibility(View.GONE);
    }

    @Override
    public void processMessage(DSMessage message)
    {
        if(message != null && message.getMessageType() != null && message.getContent() != null)
        {
            if(message.getMessageType().equals(DSMessageTypes.SUMMONER_SELECTED))
            {
                updateRecentGames();
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void updateRecentGames()
    {
        waiting.setVisibility(View.VISIBLE);

        summoner = (Summoner) SharedPreferenceManager.getInstance().loadFromShared(this.getContext(), AppConstants.SUMMONER_SELECTED_SHARED_NAME, Summoner.class);

        if(summoner != null)
        {
            String  shard       = String.valueOf(summoner.getShardid());
            String  summonerid  = String.valueOf(summoner.getId());
            String  locale      = view.getResources().getString(R.string.locale);

            RecentGamesManager recentGamesTask = new RecentGamesManager(this);
            recentGamesTask.execute(RecentGamesManager.GET_RECENT_GAMES,shard,summonerid,locale);
        }
    }
}
