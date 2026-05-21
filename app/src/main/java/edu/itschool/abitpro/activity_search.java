package edu.itschool.abitpro;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import edu.itschool.abitpro.databinding.ActivitySearchBinding;

public class activity_search extends AppCompatActivity {

    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}