
package com.ghada.ass1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.ghada.ass1.databinding.Fragment1Binding;

public class Fragment1 extends Fragment {

    private Fragment1Binding binding;

    public Fragment1() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = Fragment1Binding.inflate(
                inflater, container, false);

        return binding.getRoot();
    }

    public String getName() {
        return binding.editTextName.getText()
                .toString().trim();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
