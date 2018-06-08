package com.kapil.tictactoe;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kapil.tictactoe.databinding.FragmentResultBinding;


public class ResultFragment extends Fragment {

    private static final String RESULT = "result";

    private String result;
    private FragmentResultBinding fragmentResultBinding;


    public ResultFragment() {
    }

    public static ResultFragment newInstance(String result) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(RESULT, result);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            result = getArguments().getString(RESULT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        fragmentResultBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_result, container, false);
        return fragmentResultBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentResultBinding.gmeRsltTv.setText(result);
        fragmentResultBinding.restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().recreate();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
