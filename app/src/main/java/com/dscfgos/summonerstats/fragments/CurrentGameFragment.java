package com.dscfgos.summonerstats.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dscfgos.summonerstats.R;
import com.dscfgos.summonerstats.classes.utils.LeagueUtils;
import com.dscfgos.summonerstats.constants.AppConstants;
import com.dscfgos.summonerstats.constants.ErrorsCode;
import com.dscfgos.summonerstats.dtos.BannedChampion;
import com.dscfgos.summonerstats.dtos.Champion;
import com.dscfgos.summonerstats.dtos.CurrentGameInfo;
import com.dscfgos.summonerstats.dtos.CurrentGameResult;
import com.dscfgos.summonerstats.dtos.Summoner;
import com.dscfgos.summonerstats.interfaces.CurrentGameResponseListener;
import com.dscfgos.summonerstats.rest_client.CurrentGameManager;
import com.dscfgos.utils.SharedPreferenceManager;

import java.util.List;

public class CurrentGameFragment extends Fragment implements CurrentGameResponseListener{

    private OnFragmentInteractionListener mListener;
    private View            view            = null;
    private RelativeLayout waiting = null;
    private Summoner summoner = null;
    public ImageView imgFirstPick, imgSecondPick, imgThirthPick,
            imgFourthPick, imgFifthPick, imgSixthPick;
    public CurrentGameFragment() {
        // Required empty public constructor
    }

    public static CurrentGameFragment newInstance(String param1, String param2) {
        CurrentGameFragment fragment = new CurrentGameFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_current_game, container, false);

        waiting         = (RelativeLayout) view.findViewById(R.id.waiting);
        imgFirstPick    = (ImageView) view.findViewById(R.id.imgFirstPick);
        imgSecondPick   = (ImageView) view.findViewById(R.id.imgSecondPick);
        imgThirthPick   = (ImageView) view.findViewById(R.id.imgThirdPick);
        imgFourthPick   = (ImageView) view.findViewById(R.id.imgFourthPick);
        imgFifthPick    = (ImageView) view.findViewById(R.id.imgFifthPick);
        imgSixthPick    = (ImageView) view.findViewById(R.id.imgSixthPick);

        updateCurrentGame();

        return view ;
    }

    private void updateCurrentGame()
    {
        waiting.setVisibility(View.VISIBLE);

        summoner = (Summoner) SharedPreferenceManager.getInstance().loadFromShared(this.getContext(), AppConstants.SUMMONER_SELECTED_SHARED_NAME, Summoner.class);

        if(summoner != null)
        {
            String  shard       = String.valueOf(summoner.getShardid());
            String  summonerid  = String.valueOf(summoner.getId());
            String  locale      = view.getResources().getString(R.string.locale);

            CurrentGameManager leagueTask = new CurrentGameManager(this);
            leagueTask.execute(CurrentGameManager.GET_CURRENT_GAME,shard,summonerid,locale);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
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
    public void getCurrentGame(CurrentGameResult currentGameResult)
    {
        CurrentGameResult result = currentGameResult;
        if(result != null)
        {
            if(result.getResultCode().equalsIgnoreCase(ErrorsCode.NO_ERRORS))
            {
                CurrentGameInfo game = result.getCurrentGame();
                if(game != null)
                {
                    List<BannedChampion> champList = game.getBannedChampions();
                    imgFirstPick.setImageResource(LeagueUtils.getDrawableResourceByName(view.getContext(),getChampionByTurn(champList,1).getImage().getFull()));
                    imgSecondPick.setImageResource(LeagueUtils.getDrawableResourceByName(view.getContext(),getChampionByTurn(champList,2).getImage().getFull()));
                    imgThirthPick.setImageResource(LeagueUtils.getDrawableResourceByName(view.getContext(),getChampionByTurn(champList,3).getImage().getFull()));
                    imgFourthPick.setImageResource(LeagueUtils.getDrawableResourceByName(view.getContext(),getChampionByTurn(champList,4).getImage().getFull()));
                    imgFifthPick.setImageResource(LeagueUtils.getDrawableResourceByName(view.getContext(),getChampionByTurn(champList,5).getImage().getFull()));
                    imgSixthPick.setImageResource(LeagueUtils.getDrawableResourceByName(view.getContext(),getChampionByTurn(champList,6).getImage().getFull()));
                }
            }
            else if(result.getResultCode().equalsIgnoreCase(ErrorsCode.CG_001))
            {
                Toast.makeText(view.getContext(),"NO GAME", Toast.LENGTH_SHORT).show();
            }
        }

        waiting.setVisibility(View.GONE);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private Champion getChampionByTurn(List<BannedChampion> champs, int turn)
    {
        Champion result = null;
        for (BannedChampion banned:champs)
        {
            if(banned.getPickTurn()==turn)
            {
                result = banned.getChampion();
                break;
            }
        }
        return result;
    }
}
