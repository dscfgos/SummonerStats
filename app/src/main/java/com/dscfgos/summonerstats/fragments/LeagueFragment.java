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

import com.dscfgos.messaging.DSMessage;
import com.dscfgos.messaging.MessageProcessor;
import com.dscfgos.messaging.MessagingManager;
import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.classes.renderer.LeagueCardAdapter;
import com.dscfgos.summonerstats.constants.AppConstants;
import com.dscfgos.summonerstats.constants.DSMessageTypes;
import com.dscfgos.summonerstats.constants.ErrorsCode;
import com.dscfgos.summonerstats.dtos.League;
import com.dscfgos.summonerstats.dtos.LeagueResult;
import com.dscfgos.summonerstats.dtos.Summoner;
import com.dscfgos.summonerstats.interfaces.LeagueEntryResponseListener;
import com.dscfgos.summonerstats.rest_client.LeagueManager;
import com.dscfgos.utils.SharedPreferenceManager;

import java.util.List;

public class LeagueFragment extends Fragment implements LeagueEntryResponseListener, MessageProcessor
{
    private RecyclerView    rcyLeague   = null;
    private View            view            = null;

    private RelativeLayout waiting = null;

    private Summoner summoner = null;

    private OnFragmentInteractionListener mListener;

    public LeagueFragment() {
    }

    public static LeagueFragment newInstance()
    {
        LeagueFragment fragment = new LeagueFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessagingManager.addDSMessageListener(DSMessageTypes.SUMMONER_SELECTED,this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_league, container, false);

        rcyLeague       = (RecyclerView) view.findViewById(R.id.rcyLeague);
        waiting         = (RelativeLayout) view.findViewById(R.id.waiting);

        updateLeagueEntry();

        return view ;
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
    public void getLeagueEntry(LeagueResult leagueEntryResult)
    {
        String resultCode = leagueEntryResult.getResultCode();
        if(resultCode.equals(ErrorsCode.NO_ERRORS))
        {
            List<League> leagueList = leagueEntryResult.getLeagues().get(summoner.getId());
            LeagueCardAdapter adapter = new LeagueCardAdapter(this.getContext(),leagueList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
            rcyLeague.setLayoutManager(mLayoutManager);

            rcyLeague.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(resultCode.equals(ErrorsCode.SM_001))
        {
            /*String unranked = getResources().getString(R.string.str_unranked);
            txtLeagueUser.setVisibility(View.INVISIBLE);
            txtTierRank.setText(unranked);
            imgTierRank.setImageResource(LeagueUtils.getTierResourceId(null));*/
        }

        waiting.setVisibility(View.GONE);
    }

    @Override
    public void processMessage(DSMessage message) {
        if(message != null && message.getMessageType() != null && message.getContent() != null)
        {
            if(message.getMessageType().equals(DSMessageTypes.SUMMONER_SELECTED))
            {
                updateLeagueEntry();
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void updateLeagueEntry()
    {
        Context context = this.getContext();
        if(context != null)
        {
            waiting.setVisibility(View.VISIBLE);


            summoner = (Summoner) SharedPreferenceManager.getInstance().loadFromShared(this.getContext(), AppConstants.SUMMONER_SELECTED_SHARED_NAME, Summoner.class);

            if(summoner != null)
            {
                LeagueManager leagueTask = new LeagueManager(this);
                leagueTask.execute(LeagueManager.GET_LEAGUE_ENTRY,String.valueOf(summoner.getShardid()),String.valueOf(summoner.getId()));
            }
        }
    }
}
