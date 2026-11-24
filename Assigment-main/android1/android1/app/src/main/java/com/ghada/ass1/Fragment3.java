
package com.ghada.ass1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ghada.ass1.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    private Fragment3Binding binding;
    private String name;

    public interface OnAgreementListener {
        void onAgreementChanged(boolean isChecked);
    }

    private OnAgreementListener listener;

    public Fragment3() {
        // Required empty constructor
    }

    public static Fragment3 newInstance(String name) {

        Fragment3 fragment = new Fragment3();

        Bundle args = new Bundle();
        args.putString("name", name);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnAgreementListener) {
            listener = (OnAgreementListener) context;
        } else {
            throw new RuntimeException(
                    context.toString() +
                            " must implement OnAgreementListener"
            );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = Fragment3Binding.inflate(
                inflater, container, false);

        if (getArguments() != null) {
            name = getArguments().getString("name");
        }

        binding.textUserName.setText("Hello, " + name);

        binding.buttonFinish.setEnabled(false);

        binding.checkBoxConfirm.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    binding.buttonFinish.setEnabled(isChecked);

                    if (isChecked) {
                        binding.buttonFinish.setText("Finish");
                    } else {
                        binding.buttonFinish.setText("Continue");
                    }

                    listener.onAgreementChanged(isChecked);
                }
        );

        return binding.getRoot();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
