
package com.ghada.ass1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ghada.ass1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnFragment2Listener,
        Fragment3.OnAgreementListener {

    private ActivityMainBinding binding;

    private Fragment1 fragment1;

    private String userName;
    private String userEmail;
    private String userGender;

    private boolean isAgreed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragment1 = new Fragment1();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment1)
                .commit();

        binding.buttonContinue.setOnClickListener(view -> {

            userName = fragment1.getName();

            if (userName.isEmpty()) {
                Toast.makeText(
                        this,
                        "Please enter your name",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            Fragment2 fragment2 = Fragment2.newInstance(userName);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment2)
                    .commit();

            binding.buttonContinue.setVisibility(View.GONE);
        });
    }

    @Override
    public void onContinueClicked(
            String name,
            String email,
            String gender) {

        userName = name;
        userEmail = email;
        userGender = gender;

        Fragment3 fragment3 = Fragment3.newInstance(userName);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .commit();
    }

    @Override
    public void onAgreementChanged(boolean isChecked) {

        isAgreed = isChecked;

        // The checkbox state is received here.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
