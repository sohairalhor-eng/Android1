
package com.ghada.ass1;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ghada.ass1.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    private Fragment2Binding binding;
    private String name;

    public interface OnFragment2Listener {
        void onContinueClicked(String name, String email, String gender);
    }

    private OnFragment2Listener listener;

    public Fragment2() {
        // Required empty constructor
    }

    public static Fragment2 newInstance(String name) {
        Fragment2 fragment = new Fragment2();

        Bundle args = new Bundle();
        args.putString("name", name);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragment2Listener) {
            listener = (OnFragment2Listener) context;
        } else {
            throw new RuntimeException(
                    context.toString() + " must implement OnFragment2Listener"
            );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = Fragment2Binding.inflate(
                inflater, container, false);

        if (getArguments() != null) {
            name = getArguments().getString("name");
        }

        binding.textName.setText(name);

        binding.buttonContinue2.setOnClickListener(v -> {

            String email = binding.editTextEmail.getText()
                    .toString().trim();

            int selectedId =
                    binding.radioGroupGender.getCheckedRadioButtonId();

            if (TextUtils.isEmpty(email)) {
                binding.editTextEmail.setError("Enter your email");
                return;
            }

            if (selectedId == -1) {
                Toast.makeText(getContext(),
                        "Select your gender",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String gender;

            if (selectedId == binding.radioMale.getId()) {
                gender = "Male";
            } else {
                gender = "Female";
            }

            listener.onContinueClicked(name, email, gender);
        });

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
